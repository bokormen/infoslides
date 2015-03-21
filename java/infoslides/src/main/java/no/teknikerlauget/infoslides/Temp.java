package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.data.Slide;
import no.teknikerlauget.infoslides.data.Theme;

import java.util.ArrayList;

public class Temp {

	public static void main(String[] args) {
		ArrayList<Integer> tags = new ArrayList<>();
		tags.add(0);
		tags.add(1);
		Slide slide = new Slide(0, "temp", "temp", "temp", new Theme("theme", "", "null"), tags);
		System.out.println(slide.toJson());
		System.out.println(Slide.fromJson(slide.toJson()));
	}
}
