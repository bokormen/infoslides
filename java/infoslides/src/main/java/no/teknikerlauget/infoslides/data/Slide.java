package no.teknikerlauget.infoslides.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Slide {

	private final int id;
	private final String title;
	private final String text;
	private final String image;
	private final Theme theme;
	private final List<Integer> tagIdList; //TODO should discuss changing list content to Tag instead of Integer

	public Slide(int id, String title, String text, String image, Theme theme, List<Integer> tagIdList) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.image = image;
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

	public String getImage() {
		return image;
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
		if (!image.equals(slide.image)) {
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
		result = 31 * result + image.hashCode();
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
				", image='" + image + '\'' +
				", theme=" + theme.getName() +
				", tags=" + tagIdList +
				'}';
	}

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("type", "Slide");
		object.put("id", id);
		object.put("title", title);
		object.put("text", text);
		object.put("image", image);
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
		String picture = json.get("image").toString();
		Theme theme = Theme.fromJson((JSONObject) json.get("theme"));
		JSONArray tagArray = (JSONArray) (json.get("tags"));
		ArrayList<Integer> tags = new ArrayList<>();
		for (int i = 0; i < tagArray.length(); i++) {
			tags.add(tagArray.getInt(i));
		}
		return new Slide(id, title, text, picture, theme, tags);
	}
}
