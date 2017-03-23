package de.illilli.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.postgresql.ds.PGSimpleDataSource;

/**
 * <a href=
 * "https://blogs.oracle.com/randystuph/entry/injecting_jndi_datasources_for_junit"
 * >Injecting JNDI datasources for JUnit Tests outside of a container</a>
 * 
 * <p>
 * for setup connection it is necessary to use tomcat-dependency in pom
 * </p>
 * 
 * <pre>
 * &lt;dependency&gt;
 *   &lt;groupId&gt;org.apache.tomcat&lt;/groupId&gt;
 *   &lt;artifactId&gt;tomcat-catalina&lt;/artifactId&gt;
 *   &lt;version&gt;8.0.15&lt;/version&gt;
 *   &lt;scope&gt;test&lt;/scope&gt;
 * &lt;/dependency&gt;
 * </pre>
 *
 */
public class ConnectionEnvironment {

	public static void setUpConnectionForJndi() throws IOException {

		InputStream inputStream = ConnectionEnvironment.class.getResourceAsStream("/jndi.properties");
		Properties properties = new Properties();
		properties.load(inputStream);

		try {
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

			InitialContext ic = new InitialContext();
			ic.createSubcontext("java:");
			ic.createSubcontext("java:/comp");
			ic.createSubcontext("java:/comp/env");
			ic.createSubcontext("java:/comp/env/jdbc");

			// Construct DataSource
			PGSimpleDataSource ds = new PGSimpleDataSource();
			ds.setServerName(properties.getProperty("servername"));
			ds.setPortNumber(Integer.parseInt(properties.getProperty("portnumber")));
			ds.setDatabaseName(properties.getProperty("databasename"));
			ds.setUser(properties.getProperty("user"));
			ds.setPassword(properties.getProperty("password"));

			ic.bind("java:/comp/env/jdbc/baumkataster", ds);

		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}
}
