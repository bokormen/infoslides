package no.teknikerlauget.infoslides.server;

import no.teknikerlauget.infoslides.database.DatabaseConnector;

import java.io.File;

/**
 * Receives new slides, changes, and instructions to delete old slides
 *
 * @author Oeyvind
 * @author kryel
 */
public class Receiver {
	private DatabaseConnector databaseConnector;

	/**
	 * Construct a new sender
	 */
	public Receiver(File databaseSettingsPath) {
		this.databaseConnector = new DatabaseConnector(databaseSettingsPath);
//		databaseConnector.open();
	}
}
