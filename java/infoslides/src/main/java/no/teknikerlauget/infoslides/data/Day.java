package no.teknikerlauget.infoslides.data;

import org.json.JSONObject;

public class Day {

	private final int day;
	private final String startTime;
	private final String endTime;

	public Day(int day, String startTime, String endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getDay() {
		return day;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
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

		if (day != day1.day) {
			return false;
		}
		if (!endTime.equals(day1.endTime)) {
			return false;
		}
		if (!startTime.equals(day1.startTime)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = day;
		result = 31 * result + startTime.hashCode();
		result = 31 * result + endTime.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Day{" +
				"day=" + day +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				'}';
	}

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("type", "Day");
		object.put("day", day);
		object.put("startTime", startTime);
		object.put("endTime", endTime);
		return object;
	}

	public static Day fromJson(JSONObject json) {
		int day = Integer.parseInt(json.get("day").toString());
		String startTime = json.get("startTime").toString();
		String endTime = json.get("endTime").toString();
		return new Day(day, startTime, endTime);
	}
}
