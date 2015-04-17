package no.teknikerlauget.infoslides.data;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DayTest {

	private Day day;

	@Before
	public void setUp() throws Exception {
		day = new Day(0, "16:00", "17:00");
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFromJson() throws Exception {
		assertEquals(day, Day.fromJson(new JSONObject("{\"startTime\":\"16:00\",\"id\":0,\"endTime\":\"17:00\",\"day\":0}")));
	}
}
