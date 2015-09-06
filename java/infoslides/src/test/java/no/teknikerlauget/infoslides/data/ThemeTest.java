package no.teknikerlauget.infoslides.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThemeTest {

	private Theme theme;

	@Before
	public void setUp() throws Exception {
		theme = new Theme(-1, "default", "TODO css", "Default theme"); // FIXME id
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFromJson() throws Exception {
		assertEquals(theme, Theme.fromJson(theme.toJson()));
	}
}
