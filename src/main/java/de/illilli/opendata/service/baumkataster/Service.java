package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);
	public static final String ENCODING = Config.getProperty("encoding");

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * <p>
	 * Diese Schnittstelle liefert die Bäume im Umkreis von 100 m zum
	 * übergebenen Punkt zurück. Es gibt die Möglichkeit die Werte als
	 * serialisiertes Objekt, als GeoJson oder als Datatable - Objekt
	 * zurückliefern zu lassen.
	 * </p>
	 * 
	 * <p>
	 * Beispiele:
	 * <ul>
	 * <li>/baumkataster/service/location?latlng=51.0,7.0</li>
	 * <li>/baumkataster/service/location?latlng=51.0,7.0&geojson</li>
	 * <li>/baumkataster/service/location?latlng=51.0,7.0&datatable</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @return json Struktur mit einem oder mehreren Bäumen.
	 * @throws IOException
	 * @throws NamingException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/location")
	public String getLocation() throws SQLException, NamingException, IOException {

		Facade facade = null;

		boolean geojson = request.getParameter("geojson") != null;
		String latlng = request.getParameter("latlng");
		String radius = request.getParameter("radius");

		if (latlng != null) {
			if (geojson) {
				facade = new TreeByLocatioinGeojsonFacade(latlng);
			} else {
				facade = new TreeByLocationFacade(latlng);
			}
		} else {
			facade = new ErrorFacade("error.treebylocation");
		}

		return facade.getJson();

	}

	/**
	 * <p>
	 * Method for checking the state of the service.
	 * </p>
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/ping")
	public String getPing() {
		return "{alive}";
	}

	/**
	 * <p>
	 * Der Service persistiert die Daten des Baumkataster in der Datenbank. Die
	 * Daten müssen im GeoJson-Format vor im Verzeichnis resources vorliegen.
	 * Wird der Service aufgerufen, werden die bisherigen Daten gelöscht und die
	 * ggf. aktualisierten Daten werden eingelesen.
	 * </p>
	 * <p>
	 * Von der Kommandozeile kann die Schnittstelle mit folgendem Kommando
	 * aufgerufen werden:
	 * </p>
	 * <code>curl -X PUT http://localhost:8080/baumkataster/service/load</code>
	 *
	 * @param wahlgebiet
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ParseException
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/load")
	public String load() throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {

		logger.info("/load called");
		Facade facade = new LoadFacade();
		return facade.getJson();
	}
}
