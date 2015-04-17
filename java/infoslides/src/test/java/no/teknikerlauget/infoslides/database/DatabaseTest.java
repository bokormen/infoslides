package no.teknikerlauget.infoslides.database;

import no.teknikerlauget.infoslides.TestData;
import no.teknikerlauget.infoslides.data.Tag;
import no.teknikerlauget.infoslides.data.Theme;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import no.teknikerlauget.infoslides.Program;

import java.util.List;

/**
 * Created by Oeyvind on 21.03.2015.
 */
public class DatabaseTest {


	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {

	}

	@Test
	public void writeTag() {
		TestData testData = new TestData();
		DatabaseQueries databaseQueries = new DatabaseQueries(Program.databaseSettingsPath);

		List<Tag> tags = testData.GetNewTags();

		for (Tag tag : tags){
			databaseQueries.newTag(tag);
		}
	}

	@Test
	public void writeTheme() {
		TestData testData = new TestData();
		DatabaseQueries databaseQueries = new DatabaseQueries(Program.databaseSettingsPath);

		List<Theme> themes = testData.getNewThemes();

		for (Theme theme : themes) {
			databaseQueries.newTheme(theme);
		}

	}
}
