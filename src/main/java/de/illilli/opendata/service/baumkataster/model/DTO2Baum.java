package de.illilli.opendata.service.baumkataster.model;

import de.illilli.opendata.service.baumkataster.jdbc.BaumDTO;

public class DTO2Baum extends Baum {

	/**
	 * 
	 * 
	 * @param dto
	 */
	public DTO2Baum(BaumDTO dto) {
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
	}

}
