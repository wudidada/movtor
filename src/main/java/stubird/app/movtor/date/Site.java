package stubird.app.movtor.date;

public class Site {
	private String url;
	
	private String cookie;
	
	private String key;
	
	private String linkFormat;
	
	private String[] channels;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLinkFormat() {
		return linkFormat;
	}

	public void setLinkFormat(String linkFormat) {
		this.linkFormat = linkFormat;
	}
	
	public String[] getChannels() {
		return channels;
	}
}
