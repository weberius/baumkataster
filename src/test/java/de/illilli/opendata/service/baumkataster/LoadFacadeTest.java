package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class LoadFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args)
			throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		Facade facade = new LoadFacade();
		String json = facade.getJson();
		System.out.println(json);
	}

}
