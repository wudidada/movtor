package stubird.app.movtor.spider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import stubird.app.movtor.date.Site;
import stubird.app.movtor.date.Torrent;

public class PtSpider {
	private static final Logger logger = LoggerFactory.getLogger(PtSpider.class);
	
	
	public ArrayList<Torrent> getAllTorrents(Site site) {
		ArrayList<Torrent> torrentList = new ArrayList<Torrent>();
		
		String[] channelUrls = site.getChannels();
		
		if (channelUrls == null) {
			
		}
		
		
		
		
		return torrentList;
	}
	
	private static String getWebpage(String url) {
		String strPage = null;
		
		if (url == null) {
			logger.debug("url is null.");
			return null;
		}
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			strPage = EntityUtils.toString(entity, StandardCharsets.UTF_8);
			
			EntityUtils.consume(entity);
		} catch (Exception e) {
			logger.debug("get page fail : {} \turl={}", e, url);
		}
		return strPage;
	}
	
	public static void main(String[] args) {
		String url = "www.baidu.com";
		System.out.println(getWebpage(url));
	}
	
}
