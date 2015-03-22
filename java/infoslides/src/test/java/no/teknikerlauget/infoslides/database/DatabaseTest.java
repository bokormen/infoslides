package no.teknikerlauget.infoslides.database;

import no.teknikerlauget.infoslides.data.Tag;
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
	public void WriteTag() {
		TestData testData = new TestData();

		List<Tag> tags = testData.GetNewTags();
		DatabaseQueries databaseQueries = new DatabaseQueries(Program.databaseSettingsPath);

		for (Tag tag : tags){
			databaseQueries.NewTag(tag);
		}
	}
}
