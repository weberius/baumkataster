package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class BaeumeByBoundingBoxGeojsonFacadeTest {

	private static final Logger logger = Logger.getLogger(BaeumeByBoundingBoxGeojsonFacadeTest.class);

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		String topLeftBottomRight = "50.940692,6.951216,50.931568,6.977266";
		Facade facade = new BaeumeByBoundingBoxGeojsonFacade(topLeftBottomRight);
		logger.info(facade.getJson());

	}

}
