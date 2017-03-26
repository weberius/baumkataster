package de.illilli.opendata.service.baumkataster.model;

import de.illilli.opendata.service.baumkataster.jdbc.BaumDTO;

public class DTO2BaumXY extends BaumXY {

	public DTO2BaumXY(BaumDTO dto) {
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
		setX(dto.getX());
		setY(dto.getY());
	}
}
