package no.teknikerlauget.infoslides.data;

import org.json.JSONObject;

public class Theme {

	private final int id;
	private final String name;
	private final String css;
	private final String description;

	public Theme(int id, String name, String css, String description) {
		this.id = id;
		this.name = name;
		this.css = css;
		this.description = description;
	}

	public int getId() {
		return id;
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Theme theme = (Theme) o;

		if (id != theme.id) {
			return false;
		}
		if (css != null ? !css.equals(theme.css) : theme.css != null) {
			return false;
		}
		if (description != null ? !description.equals(theme.description) : theme.description != null) {
			return false;
		}
		if (name != null ? !name.equals(theme.name) : theme.name != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (css != null ? css.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Theme{" +
				"id=" + id +
				", name='" + name + '\'' +
				", css='" + css + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("type", "Theme");
		object.put("id", id);
		object.put("name", name);
		object.put("css", css);
		object.put("description", description);
		return object;
	}

	public static Theme fromJson(JSONObject json) {
		int id = json.getInt("id");
		String name = json.get("name").toString();
		String css = json.get("css").toString();
		String description = json.get("description").toString();
		return new Theme(id, name, css, description);
	}
}
