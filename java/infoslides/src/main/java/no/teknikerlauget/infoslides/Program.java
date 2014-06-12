package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.server.Receiver;
import no.teknikerlauget.infoslides.server.Sender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kryel
 */
public class Program {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(Sender::new);
		es.execute(Receiver::new);
	}

}
