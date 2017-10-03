package extractor.crawler;

import java.util.List;

public class EnvCrawlConfig {
	
	private String storageFolderPath;
	private int threads;
	private ProxyConfig proxyConfig;
	private List<String> seeds;
	
	public EnvCrawlConfig(String storageFolderPath, int threads, ProxyConfig proxyConfig, List<String> seeds) {
		this.storageFolderPath = storageFolderPath;
		this.threads = threads;
		this.proxyConfig = proxyConfig;
		this.seeds = seeds;
	}

	public String getStorageFolderPath() {
		return storageFolderPath;
	}

	public void setStorageFolderPath(String storageFolderPath) {
		this.storageFolderPath = storageFolderPath;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public ProxyConfig getProxyConfig() {
		return proxyConfig;
	}

	public void setProxyConfig(ProxyConfig proxyConfig) {
		this.proxyConfig = proxyConfig;
	}

	public List<String> getSeeds() {
		return seeds;
	}

	public void setSeeds(List<String> seeds) {
		this.seeds = seeds;
	}
	


}
