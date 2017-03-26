package de.illilli.opendata.service.baumkataster;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LatLngFromStringTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetLat() {
		String latlng = "50.941357,6.958307";
		double expected = 50.941357;
		double delta = 0.0;
		double actual = new LatLngFromString(latlng).getLat();
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void testGetLng() {
		String latlng = "50.941357,6.9583078";
		double expected = 6.9583078;
		double delta = 0.0;
		double actual = new LatLngFromString(latlng).getLng();
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void testNoValues() {
		String latlng = "lat,lng";
		double expected = 50.941357;
		double delta = 0.0;
		double actual = new LatLngFromString(latlng).getLat();
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void testValueTooBig() {
		String latlng = "320.12,-320.34";
		double expected = 50.941357;
		double delta = 0.0;
		double actual = new LatLngFromString(latlng).getLat();
		Assert.assertEquals(expected, actual, delta);
	}

}
