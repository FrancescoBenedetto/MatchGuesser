package extractor.crawlerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WebCrawlerTestCase {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testDataExtractionFromUrl(){
		String url = "http://www.futhead.com/13/players/13545/antonio-di-natale/";
		assertEquals("13", url.split("/")[3]);
		assertEquals("antonio-di-natale", url.split("/")[6]);
	}

}
