package no.teknikerlauget.infoslides.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tag {


	private final int id;
	private final String name;
	private final boolean overrideOtherTags;
	private final String startDate;
	private final String endDate;
	private final List<Day> days;
	private final Repeat repeat;

	public Tag(int id, String name, boolean overrideOtherTags, String startDate, String endDate, List<Day> days, Repeat repeat) {
		this.id = id;
		this.name = name;
		this.overrideOtherTags = overrideOtherTags;
		this.startDate = startDate;
		this.endDate = endDate;
		this.days = days;
		this.repeat = repeat;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public Repeat getRepeat() {
		return repeat;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Tag tag = (Tag) o;

		if (id != tag.id) {
			return false;
		}
		if (overrideOtherTags != tag.overrideOtherTags) {
			return false;
		}
		if (!days.equals(tag.days)) {
			return false;
		}
		if (!endDate.equals(tag.endDate)) {
			return false;
		}
		if (!name.equals(tag.name)) {
			return false;
		}
		if (!repeat.equals(tag.repeat)) {
			return false;
		}
		if (!startDate.equals(tag.startDate)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		result = 31 * result + (overrideOtherTags ? 1 : 0);
		result = 31 * result + startDate.hashCode();
		result = 31 * result + endDate.hashCode();
		result = 31 * result + days.hashCode();
		result = 31 * result + repeat.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Tag{" +
				"id=" + id +
				", name='" + name + '\'' +
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
		object.put("type", "Tag");
		object.put("id", id);
		object.put("name", name);
		object.put("overrideOtherTags", overrideOtherTags);
		object.put("startDate", startDate);
		object.put("endDate", endDate);
		object.put("repeat", repeat.name());

		// Add the list of days
		JSONArray jsonDays = new JSONArray();
		for (int i = 0; i < days.size(); i++) {
			jsonDays.put(i, days.get(i).toJson());
		}
		object.put("days", jsonDays);

		return object;
	}

	public static Tag fromJson(JSONObject json) {
		int id = Integer.parseInt(json.get("id").toString());
		String tag = json.get("name").toString();
		boolean overrideOtherTags = Boolean.parseBoolean(json.get("overrideOtherTags").toString());
		String startDate = json.get("startDate").toString();
		String endDate = json.get("endDate").toString();
		Repeat repeat = Repeat.getFromString(json.getString("repeat"));

		JSONArray dayList = json.getJSONArray("days");
		List<Day> days = new ArrayList<>();
		for (int i = 0; i < dayList.length(); i++) {
			JSONObject day = dayList.getJSONObject(i);
			days.add(new Day(day.getInt("id"), day.getInt("day"), day.getString("startTime"), day.getString("endTime")));
		}

		return new Tag(id, tag, overrideOtherTags, startDate, endDate, days, repeat);
	}
}
