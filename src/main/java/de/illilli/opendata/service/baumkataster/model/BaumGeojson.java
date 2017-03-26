package de.illilli.opendata.service.baumkataster.model;

public class BaumGeojson extends Baum {

	private String geojson;

	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((geojson == null) ? 0 : geojson.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaumGeojson other = (BaumGeojson) obj;
		if (geojson == null) {
			if (other.geojson != null)
				return false;
		} else if (!geojson.equals(other.geojson))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaumGeojson [geojson=" + geojson + "]";
	}

}
