package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.data.Slide;
import no.teknikerlauget.infoslides.data.Theme;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestSlide {

	private final Slide slide;
	private final Random random;

	public TestSlide() {
		random = new Random();
		int id = 0;
		String title = "Test slide";
		String text = "lipsum";

		String picture = "";
		try (BufferedReader br = new BufferedReader(new FileReader("res/base64image.txt"))) {
			picture = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Theme theme = new Theme("default", "", "Default theme"); // TODO css
		ArrayList<Integer> tagIdList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			tagIdList.add(random.nextInt(100));
		}
		slide = new Slide(id, title, text, picture, theme, tagIdList);
	}

	public Slide getSlide() {
		return slide;
	}
}
