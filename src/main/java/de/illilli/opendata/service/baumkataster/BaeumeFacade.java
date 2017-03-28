package de.illilli.opendata.service.baumkataster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.baumkataster.jdbc.BaumDTO;
import de.illilli.opendata.service.baumkataster.jdbc.SelectBaeume;
import de.illilli.opendata.service.baumkataster.model.Baum;
import de.illilli.opendata.service.baumkataster.model.DTOList2Baeume;

public class BaeumeFacade implements Facade {

	List<BaumDTO> dtoList;

	public BaeumeFacade() throws SQLException, NamingException, IOException {
		Connection conn = ConnectionFactory.getConnection();
		dtoList = new SelectListDao<BaumDTO>(new SelectBaeume(), conn).execute();
		conn.close();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		List<Baum> baeumeList = new DTOList2Baeume(this.dtoList).getData();
		return new Gson().toJson(baeumeList);
	}

}
