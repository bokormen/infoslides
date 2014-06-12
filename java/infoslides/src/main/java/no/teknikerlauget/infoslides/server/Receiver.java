package no.teknikerlauget.infoslides.server;

import no.teknikerlauget.infoslides.database.DatabaseConnector;

/**
 * Receives new slides, changes, and instructions to delete old slides
 *
 * @author Oeyvind
 * @author kryel
 */
public class Receiver {
	private DatabaseConnector databaseConnector;

	/**
	 * Construct a new receiver
	 */
	public Receiver(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
		databaseConnector.open();
	}

}
