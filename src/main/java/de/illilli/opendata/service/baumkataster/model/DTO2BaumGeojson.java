package de.illilli.opendata.service.baumkataster.model;

import de.illilli.opendata.service.baumkataster.jdbc.BaumDTO;

public class DTO2BaumGeojson extends BaumGeojson {

	public DTO2BaumGeojson(BaumDTO dto) {
		setId(dto.getId());
		setPflegeobjekt(dto.getPflegeobjekt());
		setObjekttyp(dto.getObjekttyp());
		setBezirk(dto.getBezirk());
		setBaumbestand(dto.getBaumbestand());
		setStammbis(dto.getStammbis());
		setStammvon(dto.getStammvon());
		setKrone(dto.getKrone());
		setHoehe(dto.getHoehe());
		setAlter(dto.getAlter());
		setGattung(dto.getGattung());
		setArt(dto.getArt());
		setSorte(dto.getSorte());
		setDeutsch(dto.getDeutsch());
		setGeojson(dto.getGeojson());
	}

}
