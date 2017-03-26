package de.illilli.opendata.service.baumkataster;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

public class ErrorFacade implements Facade {

	private String json;

	/**
	 * Hier muss der key für die properties übergeben werden. Dieser wird in
	 * Abhängigkeit von der Sprache aus den Sprachproperties ausgelesen.
	 * 
	 * @param key
	 */
	public ErrorFacade(String key) {
		json = Config.getProperty(key);
	}

	@Override
	public String getJson() {
		return json;
	}

}
