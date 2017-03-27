package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.baumkataster.jdbc.ObjekttypDTO;
import de.illilli.opendata.service.baumkataster.jdbc.SelectObjekttypen;
import de.illilli.opendata.service.baumkataster.model.Objekttyp;

public class ObjekttypenFacade implements Facade {

	private List<Objekttyp> data = new ArrayList<Objekttyp>();
	private ResourceBundle messages;

	public ObjekttypenFacade() throws SQLException, NamingException, IOException {

		messages = ResourceBundle.getBundle("MessagesBundle");

		Connection conn = ConnectionFactory.getConnection();
		List<ObjekttypDTO> objekttypen = new SelectListDao<ObjekttypDTO>(new SelectObjekttypen(), conn).execute();

		for (ObjekttypDTO dto : objekttypen) {
			Objekttyp typ = new Objekttyp();
			typ.setId(dto.getId());
			typ.setText(messages.getString("objekttyp." + dto.getId()));
			data.add(typ);
		}
		conn.close();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new Gson().toJson(data);
	}

}
