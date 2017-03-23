package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.geojson.Feature;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.InsertDao;
import de.illilli.jdbc.UpdateDao;
import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.baumkataster.askfor.AskForBaumkataster;
import de.illilli.opendata.service.baumkataster.jdbc.Baum2DTO;
import de.illilli.opendata.service.baumkataster.jdbc.DeleteBaeume;
import de.illilli.opendata.service.baumkataster.jdbc.InsertBaum;

/**
 * Diese Klasse
 */
public class LoadFacade implements Facade {

	private static final Logger logger = Logger.getLogger(LoadFacade.class);
	private String msg;

	public LoadFacade() throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {

		Connection conn = ConnectionFactory.getConnection();

		InputStream inputStream = LoadFacade.class.getResourceAsStream("/baumkataster.geojson");
		AskFor<List<Feature>> askfor = new AskForBaumkataster(inputStream);
		List<Feature> features = askfor.getData();

		// 1. bisherige Daten löschen
		new UpdateDao(new DeleteBaeume(), conn).execute();

		// 2. Daten neu einlesen
		for (Feature feature : features) {
			new InsertDao(new InsertBaum(new Baum2DTO(feature)), conn).execute();
		}
	}

	@Override
	public String getJson() {
		// TODO Auto-generated method stub
		return null;
	}

}
