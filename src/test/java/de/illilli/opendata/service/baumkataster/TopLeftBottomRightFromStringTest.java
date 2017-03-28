package de.illilli.opendata.service.baumkataster;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Default Values: 50.940692,6.951216,50.931568,6.977266
 */
public class TopLeftBottomRightFromStringTest {

	TopLeftBottomRightFromString tlbr;
	String topLeftBottomRight = "50.904238,6.987348,50.901823,6.994011";

	TopLeftBottomRightFromString tlbrFault;
	String topLeftBottomRightFault = "250.904238,366.987348,350.901823,226.994011";

	@Before
	public void setUp() throws Exception {
		tlbr = new TopLeftBottomRightFromString(topLeftBottomRight);
		tlbrFault = new TopLeftBottomRightFromString(topLeftBottomRightFault);
	}

	@Test
	public void testGetTopx() {
		double expected = 50.904238;
		double actual = tlbr.getTopx();
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetTopxDefault() {
		double expected = 50.940692;
		double actual = tlbrFault.getTopx();
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetTopy() {
		double expected = 6.987348;
		double actual = tlbr.getTopy();
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetTopyDefault() {
		double expected = 6.951216;
		double actual = tlbrFault.getTopy();
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetBottomx() {
		double expected = 50.901823;
		double actual = tlbr.getBottomx();
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetBottomxDefault() {
		double expected = 50.931568;
		double actual = tlbrFault.getBottomx();
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetBottomy() {
		double expected = 6.994011;
		double actual = tlbr.getBottomy();
		Assert.assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testGetBottomyDefault() {
		double expected = 6.977266;
		double actual = tlbrFault.getBottomy();
		Assert.assertEquals(expected, actual, 0.0);
	}

}
