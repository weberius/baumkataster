package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class GattungenFacadeTest {

	private static final Logger logger = Logger.getLogger(GattungenFacadeTest.class);

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException, NamingException, IOException {
		ConnectionEnvironment.setUpConnectionForJndi();
		Facade facade = new GattungenFacade();
		logger.info(facade.getJson());
	}

}
