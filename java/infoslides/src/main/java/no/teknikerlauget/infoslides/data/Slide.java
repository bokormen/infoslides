package no.teknikerlauget.infoslides.data;

import org.json.simple.JSONObject;

public class Slide {

	private final int id;
	private final String title;
	private final String text;
	private final String picture;

	public Slide(int id, String title, String text, String picture) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.picture = picture;
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

	public JSONObject toJson() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("title", title);
		object.put("text", text);
		object.put("picture", picture);
		return object;
	}
}
