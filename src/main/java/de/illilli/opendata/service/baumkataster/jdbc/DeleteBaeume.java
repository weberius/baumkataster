package de.illilli.opendata.service.baumkataster.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

/**
 * Diese Klasse definiert das delete-Statement, mit dem alle Baeume in der
 * Datenbank gelöscht werden.
 */
public class DeleteBaeume implements InsertOrUpdate {

	static final String sqlFileName = "/sql/deleteBaeume.sql";

	@Override
	public Object[] getParameter() {
		return new Object[0];
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = DeleteBaeume.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
