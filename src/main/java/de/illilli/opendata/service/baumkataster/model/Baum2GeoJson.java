package de.illilli.opendata.service.baumkataster.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.GeoJsonObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.baumkataster.jdbc.BaumDTO;

public class Baum2GeoJson {

	private Feature feature = new Feature();

	public Baum2GeoJson(BaumDTO dto) throws JsonParseException, JsonMappingException, IOException {
		feature.setId(dto.getId() + "");

		Map<String, Object> properties = new HashMap<>();
		properties.put("pflegeobjekt", dto.getPflegeobjekt());
		properties.put("objekttyp", dto.getObjekttyp());
		properties.put("bezirk", dto.getBezirk());
		properties.put("baumbestand", dto.getBaumbestand());
		properties.put("stammvon", dto.getStammvon());
		properties.put("stammbis", dto.getStammbis());
		properties.put("krone", dto.getKrone());
		properties.put("hoehe", dto.getHoehe());
		properties.put("alter", dto.getAlter());
		properties.put("gattung", dto.getGattung());
		properties.put("art", dto.getArt());
		properties.put("sorte", dto.getSorte());
		properties.put("deutsch", dto.getDeutsch());
		feature.setProperties(properties);

		GeoJsonObject geomertry = new ObjectMapper().readValue(dto.getGeojson(), GeoJsonObject.class);
		feature.setGeometry(geomertry);

	}

	public Feature getFeature() {
		return feature;
	}

}
