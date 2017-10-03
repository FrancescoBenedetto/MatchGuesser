package extractor.crawler;

public class ProxyConfig {
	
	private String host, user, password;
	private int port;
	private boolean isActive;

	public ProxyConfig(){}
	
	public ProxyConfig(String host, String user, String password, int port, boolean isActive) {
		this.host = host;
		this.user = user;
		this.password = password;
		this.port = port;
		this.isActive = isActive;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
