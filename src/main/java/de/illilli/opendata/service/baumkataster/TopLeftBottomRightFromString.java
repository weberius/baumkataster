package de.illilli.opendata.service.baumkataster;

import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * default values: 50.940692,6.951216,50.931568,6.977266
 *
 */
public class TopLeftBottomRightFromString {

	private double[] tlbr = new double[4];
	private String delim = ",";

	public TopLeftBottomRightFromString(String topLeftBottomRight) {
		StringTokenizer st = new StringTokenizer(topLeftBottomRight, delim);
		for (int i = 0; st.hasMoreTokens(); i++) {
			String token = st.nextToken();
			if (NumberUtils.isNumber(token)) {
				tlbr[i] = NumberUtils.createDouble(token);
			}
		}
	}

	public double getTopx() {

		if (tlbr[0] > 180.0) {
			tlbr[0] = 50.940692;
		} else if (tlbr[0] < -180.0) {
			tlbr[0] = 50.940692;
		}

		return tlbr[0];
	}

	public double getTopy() {
		if (tlbr[1] > 180.0) {
			tlbr[1] = 6.951216;
		} else if (tlbr[1] < -180.0) {
			tlbr[1] = 6.951216;
		}
		return tlbr[1];
	}

	public double getBottomx() {
		if (tlbr[2] > 180.0) {
			tlbr[2] = 50.931568;
		} else if (tlbr[2] < -180.0) {
			tlbr[2] = 50.931568;
		}
		return tlbr[2];
	}

	public double getBottomy() {
		int i = 3;
		if (tlbr[i] > 180.0) {
			tlbr[i] = 6.977266;
		} else if (tlbr[i] < -180.0) {
			tlbr[i] = 6.977266;
		}
		return tlbr[i];
	}

}
