package de.illilli.opendata.service.baumkataster.jdbc;

public class GattungDTO {

	private String gattung;

	public String getGattung() {
		return gattung;
	}

	public void setGattung(String gattung) {
		this.gattung = gattung;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gattung == null) ? 0 : gattung.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GattungDTO other = (GattungDTO) obj;
		if (gattung == null) {
			if (other.gattung != null)
				return false;
		} else if (!gattung.equals(other.gattung))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GattungDTO [gattung=" + gattung + "]";
	}

}
