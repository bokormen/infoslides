package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Slide {

	private final int id;
	private final String title;
	private final String text;
	private final String picture;
	private final Theme theme;
	private final List<Tag> tags;

	public Slide(int id, String title, String text, String picture, Theme theme, List<Tag> tags) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.picture = picture;
		this.theme = theme;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public String getPicture() {
		return picture;
	}

	public Theme getTheme() {
		return theme;
	}

	public List<Tag> getTags() {
		return tags;
	}

	@Override
	public String toString() {
		return "Slide{" +
				"id=" + id +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				", picture='" + picture + '\'' +
				", theme=" + theme.getName() +
				", tags=" + tags +
				'}';
	}

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("title", title);
		object.put("text", text);
		object.put("picture", picture);
		object.put("theme", theme.toJson());
		// Add tags
		List<JSONObject> jsonTags = new ArrayList<>();
		for (Tag tag : tags) {
			jsonTags.add(tag.toJson());
		}
		object.put("tags", jsonTags);
		return object;
	}

	public static Slide fromJson(JSONObject json) {
		int id = Integer.parseInt(json.get("id").toString());
		String title = json.get("title").toString();
		String text = json.get("text").toString();
		String picture = json.get("picture").toString();
		Theme theme = Theme.fromJson((JSONObject) json.get("theme"));
		List<Tag> tags = new ArrayList<>();
		JSONObject jsonTagList = (JSONObject) json.get(tags);
		// TODO Fill tags
		return new Slide(id, title, text, picture, theme, tags);
	}
}
