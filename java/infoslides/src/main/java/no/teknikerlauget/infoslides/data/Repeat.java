package no.teknikerlauget.infoslides.data;

public enum Repeat {
	WEEKLY, EVEN_WEEKS, ODD_WEEKS;

	public static Repeat getFromString(String repeat) {
		switch (repeat) {
			case "WEEKLY":
				return WEEKLY;
			case "EVEN_WEEKS":
				return EVEN_WEEKS;
			case "ODD_WEEKS":
				return ODD_WEEKS;
			default:
				return null;
		}
	}
}
