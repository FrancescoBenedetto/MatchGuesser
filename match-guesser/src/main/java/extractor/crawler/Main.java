package extractor.crawler;


public class Main {

	public static void main(String[] args) {
		String configFilePath = "/home/francesco/workspace/MatchGuesser/match-guesser/src/main/resources/matchesCrawlerConfig.xml";
		try {
			ControllerFactory.createController(configFilePath)
				.start(MatchesCrawler.class, 
						CrawlConfigFactory.createEnvCrawlConfig(configFilePath).getThreads());
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
