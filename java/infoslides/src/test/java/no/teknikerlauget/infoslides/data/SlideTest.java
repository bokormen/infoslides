package no.teknikerlauget.infoslides.data;

import no.teknikerlauget.infoslides.TestSlide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SlideTest {

	private Slide slide;

	@Before
	public void setUp() throws Exception {
		slide = new TestSlide().getSlide();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFromJson() throws Exception {
		assertEquals(slide, Slide.fromJson(slide.toJson()));
	}
}
