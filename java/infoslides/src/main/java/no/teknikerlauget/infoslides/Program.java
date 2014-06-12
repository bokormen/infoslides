package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.server.Reciver;
import no.teknikerlauget.infoslides.server.Send;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kryel
 */
public class Program {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(Send::new);
		es.execute(Reciver::new);
	}

}
