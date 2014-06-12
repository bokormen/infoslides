package no.teknikerlauget.infoslides.database;

import java.io.File;

/**
 * Created by Oeyvind on 12.06.2014.
 */
public class Querys extends DatabaseConnector {
	/**
	 * Initiates a databaseConnector using properties from the given path
	 *
	 * @param propertiesPath
	 */
	public Querys(File propertiesPath) {
		super(propertiesPath);
	}
}
