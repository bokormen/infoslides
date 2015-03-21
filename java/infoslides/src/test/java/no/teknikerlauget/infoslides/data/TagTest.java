package no.teknikerlauget.infoslides.data;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TagTest {

	private Tag tag;

	@Before
	public void setUp() throws Exception {
		ArrayList<Day> days = new ArrayList<>();
		days.add(new Day(0, 5, "18:00", "23:00"));
		tag = new Tag(0, "LM", false, "2015-03-21", "2015-03-29", days, Repeat.WEEKLY);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFromJson() throws Exception {
		JSONObject jsonObject = new JSONObject("{\"endDate\":\"2015-03-29\",\"repeat\":\"WEEKLY\",\"name\":\"LM\",\"days\":[{\"startTime\":\"18:00\",\"id\":0,\"endTime\":\"23:00\",\"day\":5}],\"id\":0,\"overrideOtherTags\":false,\"startDate\":\"2015-03-21\"}");
		assertEquals(Tag.fromJson(jsonObject), tag);
	}
}
