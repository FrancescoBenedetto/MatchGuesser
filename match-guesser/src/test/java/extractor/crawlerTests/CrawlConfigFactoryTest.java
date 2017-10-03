package extractor.crawlerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import extractor.crawler.CrawlConfigFactory;
import extractor.crawler.EnvCrawlConfig;

public class CrawlConfigFactoryTest {

	EnvCrawlConfig config;
	
	@Before
	public void setUp() throws Exception {
		config = CrawlConfigFactory
				.createEnvCrawlConfig("/home/francesco/workspace/MatchGuesser/match-guesser/src/main/resources/footballPlayersCrawlerConfig.xml");
	}

	@Test
	public void testProxyIsActive() {
		assertTrue(config.getProxyConfig().isActive());
	}
	
	@Test
	public void testProxyHost() {
		assertEquals("192.168.10.1", config.getProxyConfig().getHost());
	}
	
	@Test
	public void testProxyPort() {
		assertTrue(3128 == config.getProxyConfig().getPort());
	}
	
	@Test
	public void testProxyPassword() {
		assertEquals("FBenedet.90", config.getProxyConfig().getPassword());
	}
	
	@Test
	public void testSeed() {
		assertEquals("http://www.futhead.com/10/", config.getSeeds().get(0));
	}
	
	@Test
	public void testThreads() {
		assertEquals(10, config.getThreads());
	}
	
	

}
