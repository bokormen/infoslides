package no.teknikerlauget.infoslides.server;

import no.teknikerlauget.infoslides.database.DatabaseConnector;

import java.io.File;

/**
 * Sends the current slide to all listening clients
 *
 * @author Oeyvind
 * @author kryel
 */
public class Sender {
	private DatabaseConnector databaseConnector;

	/**
	 * Construct a new sender
	 */
	public Sender(File databaseSettingsPath) {
		this.databaseConnector = new DatabaseConnector(databaseSettingsPath);
//		databaseConnector.open();
	}

}
