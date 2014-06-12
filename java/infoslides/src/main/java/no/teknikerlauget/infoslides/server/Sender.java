package no.teknikerlauget.infoslides.server;

/**
 * Sends the current slide to all listening clients
 *
 * @author Oeyvind
 * @author kryel
 */
public class Sender {

	/**
	 * Construct a new sender
	 */
	public Sender() {
		System.out.println(this.getClass().getSimpleName() + " has started");
	}

}
