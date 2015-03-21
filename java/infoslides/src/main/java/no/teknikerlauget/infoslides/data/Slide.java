package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Slide {

	private final int id;
	private final String title;
	private final String text;
	private final String picture;
	private final Theme theme;
	private final List<Integer> tagIdList;

	public Slide(int id, String title, String text, String picture, Theme theme, List<Integer> tagIdList) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.picture = picture;
		this.theme = theme;
		this.tagIdList = tagIdList;
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

	public List<Integer> getTagIdList() {
		return tagIdList;
	}

	@Override
	public String toString() {
		return "Slide{" +
				"id=" + id +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				", picture='" + picture + '\'' +
				", theme=" + theme.getName() +
				", tags=" + tagIdList +
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
		JSONArray jsonArray = new JSONArray();
		for (Integer tag : tagIdList) {
			jsonArray.add(tag);
		}
		object.put("tags", jsonArray);
		return object;
	}

	public static Slide fromJson(JSONObject json) {
		int id = Integer.parseInt(json.get("id").toString());
		String title = json.get("title").toString();
		String text = json.get("text").toString();
		String picture = json.get("picture").toString();
		Theme theme = Theme.fromJson((JSONObject) json.get("theme"));
		JSONArray tagArray = (JSONArray) (json.get("tags"));
		ArrayList<Integer> tags = new ArrayList<>();
		for (Object tag : tagArray) {
			int tagId = Integer.parseInt((String) tag);
			tags.add(tagId);
		}
		return new Slide(id, title, text, picture, theme, tags);
	}
}
