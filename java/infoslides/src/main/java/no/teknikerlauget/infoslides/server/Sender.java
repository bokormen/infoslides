package no.teknikerlauget.infoslides.server;

import no.teknikerlauget.infoslides.database.DatabaseConnector;
import org.eclipse.jetty.util.preventers.SecurityProviderLeakPreventer;

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
	private boolean running = true;

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
		while (running) {
			sendSlide();
			sleep(sleepTime);
		}
	}

	/**
	 * Calls Thread.sleep with the given time
	 *
	 * @param sleepTime How long the thread should sleep
	 */
	private void sleep(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a slide to the clients
	 */
	private void sendSlide() {
		// TODO
	}

}
