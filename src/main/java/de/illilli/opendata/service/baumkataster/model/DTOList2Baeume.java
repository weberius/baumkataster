package de.illilli.opendata.service.baumkataster.model;

import java.util.ArrayList;
import java.util.List;

import de.illilli.opendata.service.baumkataster.jdbc.BaumDTO;

public class DTOList2Baeume {

	private List<Baum> baeumeList = new ArrayList<Baum>();

	public DTOList2Baeume(List<BaumDTO> dtoList) {
		for (BaumDTO dto : dtoList) {
			baeumeList.add(new DTO2Baum(dto));
		}
	}

	public List<Baum> getData() {
		return baeumeList;
	}

}
