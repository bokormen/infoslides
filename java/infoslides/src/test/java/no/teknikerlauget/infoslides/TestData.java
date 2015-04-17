package no.teknikerlauget.infoslides;

import no.teknikerlauget.infoslides.data.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class creates data that can be used for testing purposes
 * Created by Oeyvind on 21.03.2015.
 */
public class TestData {

    /**
     * This creates a list of three different tags that can be used for testing
     * @return A list containing three Tag
     */
	public List<Tag> GetNewTags() {
		List<Tag> tags = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String tagName = "TestTagNr" + Integer.toString(i+1);
			Boolean trueFalse = new Random().nextBoolean();
			String startDate =  "2015-03-" + String.format("%02d", i);
			String endDate = "2015-08-" + String.format("%02d", i);
			Repeat repeat = Repeat.values()[i % 3];

			Tag tag = new Tag(-1, tagName , trueFalse , startDate, endDate , getNewDays(), repeat);
			tags.add(tag);
		}

		return tags;
	}

    /**
     * This creates a list of seven different days that can be used for testing, all days have id -1, indicating they have not been read from the database
     * @return A list containing seven Day
     */
	public List<Day> getNewDays() {
		List<Day> days = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int dayId = -1;
            String startTime = String.format("%02d", i) + ":" + String.format("%02d", i);
			String endTime = String.format("%02d", i+10) + ":" + String.format("%02d", i+10);
			Day day = new Day(dayId, i, startTime, endTime);

			days.add(day);
		}
		return days;
	}

    /**
     * This creates a list of three different themes that can be used for testing
     * @return A list of three different Theme
     */
    public List<Theme> getNewThemes() {
        List<Theme> themes = new ArrayList<>();

        String name = "dark";
        String cssStyle = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/dark.css"))) {
            cssStyle = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String description = "Et eksempel theme laget av Rune";
        Theme theme = new Theme(-1, name, cssStyle, description); // FIXME id
        themes.add(theme);

        name = "index";
        cssStyle = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/index.css"))) {
            cssStyle = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        description = "Et stylesheet laget av Øyvind i forbindelse med Webutvikling 1";
        theme = new Theme(-2, name, cssStyle, description); // FIXME id
        themes.add(theme);

        name = "meny";
        cssStyle = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/meny.css"))) {
            cssStyle = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        description = "Et stylesheet laget av Øyvind i forbindelse med Webutvikling 1";
        theme = new Theme(-3, name, cssStyle, description); // FIXME id
        themes.add(theme);

        return themes;
    }

    /**
     * This creates a list of four different slides, all with id -1, indicating that they have not been read from the database, neither one of them have any tags connected to them
     * @return A list of four Slide, the first with little text and a picture, the second withe some text and a picture, the third with much text and a picture, the fourth with just some text
     */
    public List<Slide> getNewSlides() {
        List<Slide> slides = new ArrayList<>();
        List<Theme> themes = getNewThemes();
        List<Integer> tagIds= new ArrayList<>();

        int id = -1;

        String picture = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/base64image.txt"))) {
            picture = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // adds one slide, with little text and with standard picture and id -1
        String title = "title";
        String text = "lipsum";

        Slide slide = new Slide(id, title, text, picture, themes.get(1), tagIds);

        slides.add(slide);

        // adds one slide, with some text and with standard picture and id -1
        title = "longer title";
        text = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/base64image.txt"))) {
            text = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        slide = new Slide(id, title, text, picture, themes.get(1), tagIds);

        slides.add(slide);

        // adds one slide, with much text and with standard picture and id -1
        title = "very very very very very very long title";
        text = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/base64image.txt"))) {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                text += line;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        slide = new Slide(id, title, text, picture, themes.get(1), tagIds);

        slides.add(slide);

        // adds one slide, with some text and id -1
        title = "some title";
        text = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/base64image.txt"))) {
            text = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        slide = new Slide(id, title, text, "", themes.get(1), tagIds);

        slides.add(slide);

        return  slides;
    }
}
