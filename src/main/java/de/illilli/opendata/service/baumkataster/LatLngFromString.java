package de.illilli.opendata.service.baumkataster;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * <p>
 * Diese Klasse wandelt den übergebenen String in Double Werte und prüft darauf,
 * ob es sich um eine Zahl handelt, bzw. ob der Wert zwischen -180.0 und 180.0
 * ist.
 * </p>
 * <p>
 * Wenn keine verwertbaren Zahlen übergeben werden, werden die Koordinaten des
 * Kölner Dom angenommen.
 * <ul>
 * <li>lat: 50.941357</li>
 * <li>lgn: 6.958307</li>
 * </ul>
 * </p>
 */
public class LatLngFromString {

	// initialise with Dom of Cologne
	private double lat = 50.941357;
	private double lng = 6.958307;

	/**
	 * 
	 * @param latlng
	 */
	public LatLngFromString(String latlng) {
		if (latlng != null && latlng.contains(",")) {
			String str = latlng.substring(0, latlng.indexOf(','));
			if (NumberUtils.isNumber(str)) {
				lat = NumberUtils.createDouble(str);
				if (lat > 180.0) {
					lat = 50.941357;
				} else if (lat < -180.0) {
					lat = 50.941357;
				}
			}
			str = latlng.substring(latlng.indexOf(',') + 1, latlng.length());
			if (NumberUtils.isNumber(str)) {
				lng = NumberUtils.createDouble(str);
				if (lng > 180.0) {
					lng = 6.958307;
				} else if (lng < -180.0) {
					lng = 6.958307;
				}
			}
		}
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

}
