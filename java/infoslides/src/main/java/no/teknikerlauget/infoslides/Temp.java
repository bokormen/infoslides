package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.data.Slide;
import no.teknikerlauget.infoslides.data.Tag;

import java.util.ArrayList;

public class Temp {

	public static void main(String[] args) {
		ArrayList<Tag> tags = new ArrayList<>();
		tags.add(new Tag("a", false, null, null, null, null));
		tags.add(new Tag("b", false, null, null, null, null));
		tags.add(new Tag("c", false, null, null, null, null));
		Slide slide = new Slide(0, "temp", "temp", "temp", null, tags);
		System.out.println(slide.toJson());
		System.out.println(Slide.fromJson(slide.toJson()));
	}
}
