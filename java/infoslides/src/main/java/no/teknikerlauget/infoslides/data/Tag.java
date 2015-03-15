package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tag {

	private final int id;
	private final String tag;
	private final boolean overrideOtherTags;
	private final Date startDate;
	private final Date endDate;
	private final List<Day> days;
	private final String repeat;

	public Tag(int id, String tag, boolean overrideOtherTags, Date startDate, Date endDate, List<Day> days, String repeat) {
		this.id = id;
		this.tag = tag;
		this.overrideOtherTags = overrideOtherTags;
		this.startDate = startDate;
		this.endDate = endDate;
		this.days = days;
		this.repeat = repeat;
	}

	public int getId() {
		return id;
	}

	public String getTag() {
		return tag;
	}

	public boolean isOverrideOtherTags() {
		return overrideOtherTags;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<Day> getDays() {
		return days;
	}

	public String getRepeat() {
		return repeat;
	}

	/**
	 * Creates and returns a JSONObject with the data from this object
	 *
	 * @return this object as a JSONObject
	 */
	public JSONObject toJson() {
		// Used for formatting start- and endDate
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// Create JSON object and add data
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("tag", tag);
		object.put("overrideOtherTags", overrideOtherTags);
		object.put("startDate", sdf.format(startDate));
		object.put("endDate", sdf.format(endDate));
		object.put("repeat", repeat);

		// Add the list of days
		List<JSONObject> jsonDays = new ArrayList<>();
		for (Day day : days) {
			jsonDays.add(day.toJson());
		}
		object.put("days", jsonDays);

		return object;
	}
}
