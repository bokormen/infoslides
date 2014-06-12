package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.database.DatabaseConnector;
import no.teknikerlauget.infoslides.server.Receiver;
import no.teknikerlauget.infoslides.server.Sender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Entry point for the program
 *
 * @author kryel
 */
public class Program {
	private static String user;
	private static String password;
	private static String url;
	private static String port;
	private static String databaseName;

	public static void main(String[] args) {
		// Separate thread for Sender and Receiver using ExecutorService
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(() -> { new Sender(new DatabaseConnector(user, password, url, port, databaseName)); });
		es.execute(() -> { new Receiver(new DatabaseConnector(user, password, url, port, databaseName)); });
	}

}
