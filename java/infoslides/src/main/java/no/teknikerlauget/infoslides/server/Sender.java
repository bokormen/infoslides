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
	private long sleepTime = 10000;

	/**
	 * Construct a new sender
	 */
	public Sender(File databaseSettingsPath) {
		this.databaseConnector = new DatabaseConnector(databaseSettingsPath);
//		databaseConnector.open();
		startSending();
	}

	/**
	 * Start sending slides to clients
	 */
	private void startSending() {
		try {
			sendLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();  // TODO
		}
	}

	/**
	 * Send slides at regular intervals
	 *
	 * @throws InterruptedException
	 */
	private void sendLoop() throws InterruptedException {
		while (true) {
			sendSlide();
			Thread.sleep(sleepTime);
		}
	}

	/**
	 * Send a slide to the clients
	 */
	private void sendSlide() {
		// TODO
	}

}
