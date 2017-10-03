package extractor.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class ControllerFactory {

	public static CrawlController createController(String pathToConfig) throws Exception {
		
		EnvCrawlConfig envCrawlConfig = CrawlConfigFactory.createEnvCrawlConfig(pathToConfig);

        CrawlConfig config = createCrawlerConfigs(envCrawlConfig);
        
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        envCrawlConfig.getSeeds().stream().forEach(seed -> controller.addSeed(seed));
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        return controller;
    }
    
    private static CrawlConfig createCrawlerConfigs(EnvCrawlConfig envConfig) {
		CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(envConfig.getStorageFolderPath());
        ProxyConfig proxyConfig = envConfig.getProxyConfig();
    	
    	if(proxyConfig.isActive()) {
            config.setProxyHost(proxyConfig.getHost());
            config.setProxyPort(proxyConfig.getPort());
            config.setProxyUsername(proxyConfig.getUser());
            config.setProxyPassword(proxyConfig.getPassword());
    	}
    	
    	return config;
    }
}
