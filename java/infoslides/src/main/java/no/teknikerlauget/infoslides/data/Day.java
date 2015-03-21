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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Day day1 = (Day) o;

		if (id != day1.id) {
			return false;
		}
		if (day != null ? !day.equals(day1.day) : day1.day != null) {
			return false;
		}
		if (endTime != null ? !endTime.equals(day1.endTime) : day1.endTime != null) {
			return false;
		}
		if (startTime != null ? !startTime.equals(day1.startTime) : day1.startTime != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (day != null ? day.hashCode() : 0);
		result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
		result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
		return result;
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
