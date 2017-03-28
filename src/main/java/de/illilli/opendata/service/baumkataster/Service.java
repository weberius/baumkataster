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
import javax.ws.rs.PathParam;
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
	 * <li>/baumkataster/service/baeume?latlng=50.959582,6.971568</li>
	 * <li>/baumkataster/service/baeume?latlng=50.959582,6.971568&geojson</li>
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
	@Path("/baeume")
	public String getBaeume() throws SQLException, NamingException, IOException {

		Facade facade = null;

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		boolean geojson = request.getParameter("geojson") != null;
		String latlng = request.getParameter("latlng");
		String radius = request.getParameter("radius");

		if (latlng != null) {
			if (geojson) {
				logger.info("/baeume?" + latlng + "&geojson called");
				facade = new TreeByLocatioinGeojsonFacade(latlng);
			} else {
				logger.info("/baeume?" + latlng + " called");
				facade = new TreeByLocationFacade(latlng);
			}
		} else {
			facade = new ErrorFacade("error.treebylocation");
		}

		return facade.getJson();

	}

	/**
	 * <p>
	 * Schnittstelle, die es erlaubt, nur Bäume einer bestimmten Gattung
	 * aufzufinden.
	 * </p>
	 * <p>
	 * Beispiele:
	 * <ul>
	 * <li><a href="http://localhost:8080/baumkataster/service/baeume/zelkova">
	 * /baumkataster/service/baeume/zelkova</a></li>
	 * <li><a href=
	 * "http://localhost:8080/baumkataster/service/baeume/zelkova?geojson">
	 * /baumkataster/service/baeume/zelkova?geojson</a></li>
	 * </ul>
	 * </p>
	 * 
	 * @param gattung
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/baeume/{gattung}")
	public String getBaeume(@PathParam("gattung") String gattung) throws SQLException, NamingException, IOException {
		Facade facade = null;
		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		boolean geojson = request.getParameter("geojson") != null;
		if (gattung != null) {
			if (geojson) {
				logger.info("/baeume/" + gattung + "?geojson called");
				facade = new BaeumeByGattungGeojsonFacade(gattung);
			} else {
				logger.info("/baeume/" + gattung + " called");
				facade = new BaeumeByGattungFacade(gattung);
			}
		} else {
			if (geojson) {
				logger.info("/baeume?geojson called");
				facade = new BaeumeGeojsonFacade();
			} else {
				logger.info("/baeume called");
				facade = new BaeumeFacade();
			}
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
	 * Der Service liefert alle verzeichneten Objekttypen zurück.
	 * </p>
	 * <p>
	 * Beispiel:
	 * <ul>
	 * <li><a href="http://localhost:8080/baumkataster/service/objekttypen">
	 * /baumkataster/service/objekttypen</a></li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/objekttypen")
	public String getObjekttypen() throws SQLException, NamingException, IOException {

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		Facade facade = new ObjekttypenFacade();
		return facade.getJson();
	}

	/**
	 * <p>
	 * Die Schnittstelle gibt alle verzeichneten Gattungen zurück. Bäume ohne
	 * zugewiesener Gattung werden als 'null' zurückgegeben.
	 * </p>
	 * <p>
	 * Beispiel:
	 * <ul>
	 * <li><a href="http://localhost:8080/baumkataster/service/gattungen">
	 * /baumkataster/service/gattungen</a></li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/gattungen")
	public String getGattungen() throws SQLException, NamingException, IOException {

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		Facade facade = new GattungenFacade();
		return facade.getJson();

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
