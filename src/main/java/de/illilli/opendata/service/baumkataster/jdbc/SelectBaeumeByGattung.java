package de.illilli.opendata.service.baumkataster.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.Select;

public class SelectBaeumeByGattung implements Select<BaumDTO> {

	static final String sqlFileName = "/sql/selectBaeume.sql";
	private static final Logger logger = Logger.getLogger(SelectBaeumeByGattung.class);
	private String gattung;

	public SelectBaeumeByGattung(String gattung) {
		this.gattung = gattung;
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertBaum.class.getResourceAsStream(sqlFileName);
		StringBuffer sql = new StringBuffer();
		sql.append(IOUtils.toString(inputStream));
		sql.append("\n");
		sql.append("WHERE\n  lower(gattung) like ?");
		logger.debug(sql.toString());
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.gattung.toLowerCase() };
	}

	@Override
	public Class<BaumDTO> getDtoClazz() {
		return BaumDTO.class;
	}

}
