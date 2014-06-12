package no.teknikerlauget.infoslides;

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

	public static void main(String[] args) {
		// Separate thread for Sender and Receiver using ExecutorService
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(Sender::new);
		es.execute(Receiver::new);
	}

}
