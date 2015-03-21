package no.teknikerlauget.infoslides.data;

import org.json.JSONArray;
import org.json.JSONObject;

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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Slide slide = (Slide) o;

		if (id != slide.id) {
			return false;
		}
		if (!picture.equals(slide.picture)) {
			return false;
		}
		if (!tagIdList.equals(slide.tagIdList)) {
			return false;
		}
		if (!text.equals(slide.text)) {
			return false;
		}
		if (!theme.equals(slide.theme)) {
			return false;
		}
		if (!title.equals(slide.title)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + title.hashCode();
		result = 31 * result + text.hashCode();
		result = 31 * result + picture.hashCode();
		result = 31 * result + theme.hashCode();
		result = 31 * result + tagIdList.hashCode();
		return result;
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
		tagIdList.forEach(jsonArray::put);
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
		for (int i = 0; i < tagArray.length(); i++) {
			tags.add(tagArray.getInt(i));
		}
		return new Slide(id, title, text, picture, theme, tags);
	}
}
