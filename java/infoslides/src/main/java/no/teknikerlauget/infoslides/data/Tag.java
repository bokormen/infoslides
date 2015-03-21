package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tag {

	private final int id;
	private final String tag;
	private final boolean overrideOtherTags;
	private final String startDate;
	private final String endDate;
	private final List<Day> days;
	private final String repeat;

	public Tag(int id, String tag, boolean overrideOtherTags, String startDate, String endDate, List<Day> days, String repeat) {
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

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public List<Day> getDays() {
		return days;
	}

	public String getRepeat() {
		return repeat;
	}

	@Override
	public String toString() {
		return "Tag{" +
				"id=" + id +
				", tag='" + tag + '\'' +
				", overrideOtherTags=" + overrideOtherTags +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", days=" + days +
				", repeat='" + repeat + '\'' +
				'}';
	}

	/**
	 * Creates and returns a JSONObject with the data from this object
	 *
	 * @return this object as a JSONObject
	 */
	public JSONObject toJson() {
		// Create JSON object and add data
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("tag", tag);
		object.put("overrideOtherTags", overrideOtherTags);
		object.put("startDate", startDate);
		object.put("endDate", endDate);
		object.put("repeat", repeat);

		// Add the list of days
		List<JSONObject> jsonDays = new ArrayList<>();
		for (Day day : days) {
			jsonDays.add(day.toJson());
		}
		object.put("days", jsonDays);

		return object;
	}

	public static Tag fromJson(JSONObject json) {
		int id = Integer.parseInt(json.get("id").toString());
		String tag = json.get("tag").toString();
		boolean overrideOtherTags = Boolean.parseBoolean(json.get("overrideOtherTags").toString());
		String startDate = json.get("startDate").toString();
		String endDate = json.get("endDate").toString();
		String repeat = json.get("repeat").toString();
		List<Day> days = null; // TODO
		return new Tag(id, tag, overrideOtherTags, startDate, endDate, days, repeat);
	}
}
