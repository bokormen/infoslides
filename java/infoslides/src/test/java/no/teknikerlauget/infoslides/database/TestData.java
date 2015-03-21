package no.teknikerlauget.infoslides.database;

import no.teknikerlauget.infoslides.data.Day;
import no.teknikerlauget.infoslides.data.Repeat;
import no.teknikerlauget.infoslides.data.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Oeyvind on 21.03.2015.
 */
public class TestData {

	public List<Tag> GetNewTags() {
		List<Tag> tags = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int tagid = -1;
			String tagName = "TestTagNr" + Integer.toString(i+1);
			Boolean trueFalse = new Random().nextBoolean();
			String startDate =  "2015-03-" + String.format("%02d", i);
			String endDate = "2015-08-" + String.format("%02d", i);
			Repeat repeat = Repeat.values()[i % 3];

			Tag tag = new Tag(tagid, tagName , trueFalse , startDate, endDate , GetDays(), repeat);
			tags.add(tag);
		}

		return tags;
	}

	public List<Day> GetDays() {
		List<Day> days = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int dayid = -1;
			int day = i;
			String starttime = String.format("%02d", i) + ":" + String.format("%02d", i);
			String endtime = String.format("%02d", i+10) + ":" + String.format("%02d", i+10);
			Day day1 = new Day(dayid, day, starttime, endtime);

			days.add(day1);
		}
		return days;
	}


}
