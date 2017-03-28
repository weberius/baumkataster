package de.illilli.opendata.service.baumkataster.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.Select;

public class SelectBaeumeByBoundingBox implements Select<BaumDTO> {

	private static final Logger logger = Logger.getLogger(SelectBaeumeByBoundingBox.class);

	static final String sqlFileName = "/sql/selectBaeume.sql";
	private double topx;
	private double topy;
	private double bottomx;
	private double bottomy;
	private int srid = 4326;

	/**
	 * Beispiel:
	 * 
	 * <pre>
	  -- left, top
	  -- 6.987348, 50.904238
	  -- right, bottom 
	  -- 6.994011, 50.901823
	 * </pre>
	 * 
	 * @param topx
	 * @param topy
	 * @param bottomx
	 * @param bottomy
	 */
	public SelectBaeumeByBoundingBox(double topx, double topy, double bottomx, double bottomy) {
		this.topx = topx;
		this.topy = topy;
		this.bottomx = bottomx;
		this.bottomy = bottomy;
		this.srid = 4326;
	}

	@Override
	public String getSql() throws IOException {

		InputStream inputStream = InsertBaum.class.getResourceAsStream(sqlFileName);

		StringBuffer sql = new StringBuffer();
		sql.append(IOUtils.toString(inputStream));
		sql.append("\n");
		sql.append("WHERE\n  geom && ST_MakeEnvelope(?, ?, ?, ?, ?);");
		logger.debug(sql.toString());
		return sql.toString();

	}

	@Override
	public Object[] getParameter() {
		// hier muessen x und y getauscht werden.
		return new Object[] { this.topy, this.topx, this.bottomy, this.bottomx, this.srid };
	}

	@Override
	public Class<BaumDTO> getDtoClazz() {
		return BaumDTO.class;
	}

}
