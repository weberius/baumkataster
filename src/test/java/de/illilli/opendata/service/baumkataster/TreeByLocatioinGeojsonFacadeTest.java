package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class TreeByLocatioinGeojsonFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		String latlng = "50.959896,6.975451";
		Facade facade = new TreeByLocatioinGeojsonFacade(latlng);
		String json = facade.getJson();
		System.out.println(json);
	}

}
