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

	@Override
	public String toString() {
		return "Day{" +
				"id=" + id +
				", day='" + day + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				'}';
	}

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("day", day);
		object.put("startTime", startTime);
		object.put("endTime", endTime);
		return object;
	}

	public static Day fromJson(JSONObject json) {
		int id = Integer.parseInt(json.get("id").toString());
		String day = json.get("day").toString();
		String startTime = json.get("startTime").toString();
		String endTime = json.get("endTime").toString();
		return new Day(id, day, startTime, endTime);
	}
}
