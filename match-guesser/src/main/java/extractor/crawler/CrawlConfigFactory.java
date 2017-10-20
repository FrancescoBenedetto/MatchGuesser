package extractor.crawler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CrawlConfigFactory {
	
	public static EnvCrawlConfig envCrawlConfig;
	
	public static EnvCrawlConfig createEnvCrawlConfig(String configFilePath) throws JDOMException, IOException {
		if(envCrawlConfig==null){
			/*prepare Xml parser and get the root element*/
			File inputFile = new File(configFilePath);
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element root = document.getRootElement();
			/*get all the crawler config data from the config file*/
			String storageFolder = document.getRootElement().getChild("folders").getChild("crawlFolder").getValue();
			int threads = Integer.parseInt(document.getRootElement().getChild("threads").getValue());
			ProxyConfig proxyConfig = createProxyConfig(root.getChild("proxy"));
			List<String> seeds = root.getChild("seeds").getChildren().stream().map(e -> e.getValue()).collect(Collectors.toList());
			/*create the config element*/
			envCrawlConfig = new EnvCrawlConfig(storageFolder, threads, proxyConfig, seeds);
		}
		return envCrawlConfig;
	}
	
	private static ProxyConfig createProxyConfig(Element proxyRootElement) {
		ProxyConfig proxyConfig = new ProxyConfig();
		proxyConfig.setActive(false);
		boolean isActive = proxyRootElement.getChild("active").getValue().matches("true");
		if(proxyRootElement!=null && isActive){
			proxyConfig.setActive(isActive);
			proxyConfig.setHost(proxyRootElement.getChild("host").getValue());
			proxyConfig.setPort(Integer.parseInt(proxyRootElement.getChild("port").getValue()));
			proxyConfig.setUser(proxyRootElement.getChild("username").getValue());
			proxyConfig.setPassword(proxyRootElement.getChild("password").getValue());
		}
		
		return proxyConfig;
		
	}
}
