package de.illilli.opendata.service.baumkataster.askfor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.AskFor;

public class AskForBaumkataster implements AskFor<List<Feature>> {

	private List<Feature> features;

	public AskForBaumkataster(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException {
		FeatureCollection featureCollection = new ObjectMapper().readValue(inputStream, FeatureCollection.class);
		features = featureCollection.getFeatures();
	}

	@Override
	public List<Feature> getData() {
		return features;
	}

}
