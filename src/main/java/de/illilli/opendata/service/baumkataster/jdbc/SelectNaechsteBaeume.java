package de.illilli.opendata.service.baumkataster.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectNaechsteBaeume implements Select<BaumDTO> {

	static final String sqlFileName = "/sql/selectNaechsteBaeume.sql";

	double lat;
	double lng;
	double radius;

	public SelectNaechsteBaeume(double lat, double lng, double radius) {
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.lat, this.lng, this.radius };
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertBaum.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Class<BaumDTO> getDtoClazz() {
		return BaumDTO.class;
	}
}
