package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONObject;

public class Day {

	private final int id;
	private final String day;
	private final String startTime;
	private final String endTime;

	public Day(int id, String day, String startTime, String endTime) {
		this.id = id;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public String getDay() {
		return day;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("day", day);
		object.put("startTime", startTime);
		object.put("endTime", endTime);
		return object;
	}
}
