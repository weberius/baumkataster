package de.illilli.opendata.service.baumkataster.jdbc;

import org.geojson.Feature;
import org.geojson.Point;
import org.postgis.PGgeometry;

/**
 * <pre>
 * Feature{properties={
 *   PFLEGEOBJE=461, 
 *   Objekttyp=6, 
 *   Bezirk=8, 
 *   Baumbest_1=22P, 
 *   X_Koordina=359814, 
 *   Y_Koordina=5645658, 
 *   HausNr=null, 
 *   Lagebezeic=Ecke Falckensteinstr., 
 *   STAMMVON=0.0, 
 *   STAMMBIS=50.0, 
 *   KRONE=8.0, 
 *   H_HE=10.0, 
 *   AlterSchae=25.0, 
 *   Gattung=Robinia, 
 *   Art=null, 
 *   Sorte=null, 
 *   DeutscherN=Scheinakazie
    }, geometry=Point{
      coordinates=LngLatAlt{
        longitude=7.004440817536288, 
        latitude=50.945478479503, 
        altitude=NaN
        }
    } GeoJsonObject{}, id='null'
}
 * </pre>
 *
 */
public class Baum2DTO extends BaumDTO {

	public Baum2DTO(Feature feature) {

		System.out.println(feature.toString());

		int pflegeobjekt = feature.getProperty("PFLEGEOBJE");
		setPflegeobjekt(pflegeobjekt);

		int objekttyp = feature.getProperty("Objekttyp");
		setObjekttyp(objekttyp);

		int bezirk = feature.getProperty("Bezirk");
		setBezirk(bezirk);

		String baumbestand = feature.getProperty("Baumbest_1");
		setBaumbestand(baumbestand);

		double stammvon = feature.getProperty("STAMMVON");
		setStammvon(stammvon);

		double stammbis = feature.getProperty("STAMMBIS");
		setStammbis(stammbis);

		double krone = feature.getProperty("KRONE");
		setKrone(krone);

		double hoehe = feature.getProperty("H_HE");
		setHoehe(hoehe);

		double alter = feature.getProperty("AlterSchae");
		setAlter(alter);

		String gattung = feature.getProperty("Gattung");
		setGattung(gattung);

		String art = feature.getProperty("Art");
		setArt(art);

		String sorte = feature.getProperty("Sorte");
		setSorte(sorte);

		String deutsch = feature.getProperty("DeutscherN");
		setDeutsch(deutsch);

		double longitude = ((Point) feature.getGeometry()).getCoordinates().getLongitude();
		double latitude = ((Point) feature.getGeometry()).getCoordinates().getLatitude();
		org.postgis.Geometry pgPoint = new org.postgis.Point(longitude, latitude);
		pgPoint.setSrid(4326);

		PGgeometry geom = new PGgeometry(pgPoint);
		setGeom(geom);
	}
}
