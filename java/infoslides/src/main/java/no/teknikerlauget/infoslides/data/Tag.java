package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONObject;

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

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("tag", tag);
		object.put("overrideOtherTags", overrideOtherTags);
		// TODO convert to correct format
		object.put("startDate", startDate);
		object.put("endDate", endDate);
		// TODO json list
		object.put("days", days);
		object.put("repeat", repeat);
		return object;
	}
}
