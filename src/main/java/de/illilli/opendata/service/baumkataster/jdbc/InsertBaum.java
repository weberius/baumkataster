package de.illilli.opendata.service.baumkataster.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

/**
 * Diese Klasse definiert das InsertStatement, mit dem ein Baum in der Datenbank
 * persistiert werden kann. Dabei wird folgende Datenbankstruktur vorausgesetzt:
 * 
 * <pre>
 * CREATE TABLE BAUMKATASTER (
     id            SERIAL PRIMARY KEY, 
     pflegeobjekt  integer, 
     objekttyp     integer, 
     bezirk        integer, 
     baumbestand   varchar(256),
     stammvon      double precision,
     stammbis      double precision,
     krone         double precision,
     hoehe         double precision,
     alter         double precision,
     gattung       varchar(256),
     art           varchar(256),
     sorte         varchar(256),
     deutsch       varchar(256),
     modtime       timestamp DEFAULT current_timestamp
   );
   SELECT AddGeometryColumn ('public','baumkataster','geom',4326,'POINT',2);
 * </pre>
 * 
 * @author wolfram
 *
 */
public class InsertBaum implements InsertOrUpdate {

	static final String sqlFileName = "/sql/insertBaum.sql";
	private BaumDTO dto;

	public InsertBaum(BaumDTO dto) {
		this.dto = dto;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { //
				dto.getPflegeobjekt(), //
				dto.getObjekttyp(), //
				dto.getBezirk(), //
				dto.getBaumbestand(), //
				dto.getStammvon(), //
				dto.getStammbis(), //
				dto.getKrone(), //
				dto.getHoehe(), //
				dto.getAlter(), //
				dto.getGattung(), //
				dto.getArt(), //
				dto.getSorte(), //
				dto.getDeutsch(), //
				dto.getGeom() //
		};
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertBaum.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
