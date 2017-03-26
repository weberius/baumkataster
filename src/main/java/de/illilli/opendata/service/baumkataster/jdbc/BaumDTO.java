package de.illilli.opendata.service.baumkataster.jdbc;

import org.postgis.PGgeometry;

/**
 * <pre>
 *     CREATE TABLE BAUMKATASTER (
      id            SERIAL PRIMARY KEY, 
      pflegeobjekt  integer, 
      objekttyp     integer, 
      bezirk        integer, 
      baumbestand   varchar(256),
	  stammvon      double precision,
	  stammbis      double precision,
	  krone         double precision,
	  hoehe         double precision,
	  alter         double precision,
      gattung       varchar(256),
      art           varchar(256),
      sorte         varchar(256),
      deutsch       varchar(256),
      modtime       timestamp DEFAULT current_timestamp
    );
    SELECT AddGeometryColumn ('public','baumkataster','geom',4326,'POINT',2);
 * 
 * </pre>
 *
 */
public class BaumDTO {

	private int id;
	private int pflegeobjekt;
	private int objekttyp;
	private int bezirk;
	private String baumbestand;
	private double stammvon;
	private double stammbis;
	private double krone;
	private double hoehe;
	private double alter;
	private String gattung;
	private String art;
	private String sorte;
	private String deutsch;
	private PGgeometry geom;
	private String geojson;
	private double x;
	private double y;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPflegeobjekt() {
		return pflegeobjekt;
	}

	public void setPflegeobjekt(int pflegeobjekt) {
		this.pflegeobjekt = pflegeobjekt;
	}

	public int getObjekttyp() {
		return objekttyp;
	}

	public void setObjekttyp(int objekttyp) {
		this.objekttyp = objekttyp;
	}

	public int getBezirk() {
		return bezirk;
	}

	public void setBezirk(int bezirk) {
		this.bezirk = bezirk;
	}

	public String getBaumbestand() {
		return baumbestand;
	}

	public void setBaumbestand(String baumbestand) {
		this.baumbestand = baumbestand;
	}

	public double getStammvon() {
		return stammvon;
	}

	public void setStammvon(double stammvon) {
		this.stammvon = stammvon;
	}

	public double getStammbis() {
		return stammbis;
	}

	public void setStammbis(double stammbis) {
		this.stammbis = stammbis;
	}

	public double getKrone() {
		return krone;
	}

	public void setKrone(double krone) {
		this.krone = krone;
	}

	public double getHoehe() {
		return hoehe;
	}

	public void setHoehe(double hoehe) {
		this.hoehe = hoehe;
	}

	public double getAlter() {
		return alter;
	}

	public void setAlter(double alter) {
		this.alter = alter;
	}

	public String getGattung() {
		return gattung;
	}

	public void setGattung(String gattung) {
		this.gattung = gattung;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getSorte() {
		return sorte;
	}

	public void setSorte(String sorte) {
		this.sorte = sorte;
	}

	public String getDeutsch() {
		return deutsch;
	}

	public void setDeutsch(String deutsch) {
		this.deutsch = deutsch;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}

	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((art == null) ? 0 : art.hashCode());
		result = prime * result + ((baumbestand == null) ? 0 : baumbestand.hashCode());
		result = prime * result + bezirk;
		result = prime * result + ((deutsch == null) ? 0 : deutsch.hashCode());
		result = prime * result + ((gattung == null) ? 0 : gattung.hashCode());
		result = prime * result + ((geojson == null) ? 0 : geojson.hashCode());
		result = prime * result + ((geom == null) ? 0 : geom.hashCode());
		temp = Double.doubleToLongBits(hoehe);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(krone);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + objekttyp;
		result = prime * result + pflegeobjekt;
		result = prime * result + ((sorte == null) ? 0 : sorte.hashCode());
		temp = Double.doubleToLongBits(stammbis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(stammvon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		BaumDTO other = (BaumDTO) obj;
		if (Double.doubleToLongBits(alter) != Double.doubleToLongBits(other.alter))
			return false;
		if (art == null) {
			if (other.art != null)
				return false;
		} else if (!art.equals(other.art))
			return false;
		if (baumbestand == null) {
			if (other.baumbestand != null)
				return false;
		} else if (!baumbestand.equals(other.baumbestand))
			return false;
		if (bezirk != other.bezirk)
			return false;
		if (deutsch == null) {
			if (other.deutsch != null)
				return false;
		} else if (!deutsch.equals(other.deutsch))
			return false;
		if (gattung == null) {
			if (other.gattung != null)
				return false;
		} else if (!gattung.equals(other.gattung))
			return false;
		if (geojson == null) {
			if (other.geojson != null)
				return false;
		} else if (!geojson.equals(other.geojson))
			return false;
		if (geom == null) {
			if (other.geom != null)
				return false;
		} else if (!geom.equals(other.geom))
			return false;
		if (Double.doubleToLongBits(hoehe) != Double.doubleToLongBits(other.hoehe))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(krone) != Double.doubleToLongBits(other.krone))
			return false;
		if (objekttyp != other.objekttyp)
			return false;
		if (pflegeobjekt != other.pflegeobjekt)
			return false;
		if (sorte == null) {
			if (other.sorte != null)
				return false;
		} else if (!sorte.equals(other.sorte))
			return false;
		if (Double.doubleToLongBits(stammbis) != Double.doubleToLongBits(other.stammbis))
			return false;
		if (Double.doubleToLongBits(stammvon) != Double.doubleToLongBits(other.stammvon))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaumDTO [id=" + id + ", pflegeobjekt=" + pflegeobjekt + ", objekttyp=" + objekttyp + ", bezirk="
				+ bezirk + ", baumbestand=" + baumbestand + ", stammvon=" + stammvon + ", stammbis=" + stammbis
				+ ", krone=" + krone + ", hoehe=" + hoehe + ", alter=" + alter + ", gattung=" + gattung + ", art=" + art
				+ ", sorte=" + sorte + ", deutsch=" + deutsch + ", geom=" + geom + ", geojson=" + geojson + ", x=" + x
				+ ", y=" + y + "]";
	}

}
