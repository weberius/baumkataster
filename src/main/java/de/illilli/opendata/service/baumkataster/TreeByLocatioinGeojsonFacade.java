package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.baumkataster.jdbc.BaumDTO;
import de.illilli.opendata.service.baumkataster.model.Baum2GeoJson;

public class TreeByLocatioinGeojsonFacade extends TreeByLocationFacade implements Facade {

	private FeatureCollection featureCollection = new FeatureCollection();
	private List<Feature> featureList = new ArrayList<Feature>();

	public TreeByLocatioinGeojsonFacade(String latlng) throws SQLException, NamingException, IOException {
		// get Data in Superclass
		super(latlng);
		// collect features for featurecollection
		for (BaumDTO dto : dtoList) {
			featureList.add(new Baum2GeoJson(dto).getFeature());
		}
		featureCollection.addAll(featureList);
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
