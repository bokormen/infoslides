package no.teknikerlauget.infoslides.server;

import no.teknikerlauget.infoslides.database.DatabaseConnector;

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
	public Sender(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
		databaseConnector.open();
	}

}
