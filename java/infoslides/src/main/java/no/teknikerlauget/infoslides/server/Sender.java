package no.teknikerlauget.infoslides.server;

import no.teknikerlauget.infoslides.data.Slide;
import no.teknikerlauget.infoslides.database.DatabaseQueries;

import java.io.File;
import java.util.List;

/**
 * Sends the current slide to all listening clients
 *
 * @author Oeyvind
 * @author kryel
 */
public class Sender {

	private DatabaseQueries databaseQueries;
	private long sleepTime = 10000;
	private boolean running = true;

	/**
	 * Construct a new sender
	 */
	public Sender(File databaseSettingsPath) {
		this.databaseQueries = new DatabaseQueries(databaseSettingsPath);
		List<Slide> slideShow = databaseQueries.getSlideshow();
		databaseQueries.close();
		startSending(slideShow);
	}

	/**
	 * Start sending slides to clients
	 *
	 * @param slideShow
	 */
	private void startSending(List<Slide> slideShow) {
		int i = 0;
		int size = slideShow.size();
		while (running) {
			sendSlide(slideShow.get(i++));
			i %= size;
			sleep(sleepTime);
		}
	}

	/**
	 * Send a slide to the clients
	 *
	 * @param slide is sent to the client
	 */
	private void sendSlide(Slide slide) {
//		send(slide.toJson());
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
}
