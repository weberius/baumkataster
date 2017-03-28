package de.illilli.opendata.service.baumkataster.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectBaeume implements Select<BaumDTO> {

	static final String sqlFileName = "/sql/selectBaeume.sql";

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertBaum.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		return new Object[0];
	}

	@Override
	public Class<BaumDTO> getDtoClazz() {
		return BaumDTO.class;
	}

}