package de.illilli.opendata.service.baumkataster.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectGattungen implements Select<GattungDTO> {

	static final String sqlFileName = "/sql/selectGattungen.sql";

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
	public Class<GattungDTO> getDtoClazz() {
		return GattungDTO.class;
	}

}
