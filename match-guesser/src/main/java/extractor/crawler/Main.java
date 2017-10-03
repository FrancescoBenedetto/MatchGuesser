package extractor.crawler;

public class Main {

	public static void main(String[] args) throws Exception {
		String configFilePath = "/home/francesco/workspace/MatchGuesser/match-guesser/src/main/resources/footballPlayersCrawlerConfig.xml";
		ControllerFactory.createController(configFilePath)
			.start(FootballPlayersCrawler.class, 
					CrawlConfigFactory.createEnvCrawlConfig(configFilePath).getThreads());
	}
}
