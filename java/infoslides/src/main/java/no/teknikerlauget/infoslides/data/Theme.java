package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONObject;

public class Theme {

	private final String name;
	private final String css;
	private final String description;

	public Theme(String name, String css, String description) {
		this.name = name;
		this.css = css;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getCss() {
		return css;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Theme{" +
				"name='" + name + '\'' +
				", css='" + css + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("name", name);
		object.put("css", css);
		object.put("description", description);
		return object;
	}
}
