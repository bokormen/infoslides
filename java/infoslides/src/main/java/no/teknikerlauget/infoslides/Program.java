package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.server.Receiver;
import no.teknikerlauget.infoslides.server.Sender;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Entry point for the program
 *
 * @author kryel
 */
public class Program {
	private static File databaseSettingsPath;

	public static void main(String[] args) {
		// Get the path to the database settings
		databaseSettingsPath = new File("DBSettings.properties");

		// Separate thread for Sender and Receiver using ExecutorService
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(() -> new Sender(databaseSettingsPath));
		es.execute(() -> new Receiver(databaseSettingsPath));
	}

}
