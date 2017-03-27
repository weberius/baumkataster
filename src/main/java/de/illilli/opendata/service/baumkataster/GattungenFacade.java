package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.baumkataster.jdbc.GattungDTO;
import de.illilli.opendata.service.baumkataster.jdbc.SelectGattungen;

public class GattungenFacade implements Facade {

	private List<GattungDTO> data = new ArrayList<GattungDTO>();

	public GattungenFacade() throws SQLException, NamingException, IOException {
		Connection conn = ConnectionFactory.getConnection();
		List<GattungDTO> gattungen = new SelectListDao<GattungDTO>(new SelectGattungen(), conn).execute();

		for (GattungDTO dto : gattungen) {
			if (dto.getGattung() == null) {
				dto.setGattung("null");
				data.add(dto);
			} else {
				data.add(dto);
			}
		}
		conn.close();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new Gson().toJson(data);
	}

}
