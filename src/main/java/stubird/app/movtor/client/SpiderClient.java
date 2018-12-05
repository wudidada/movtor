package stubird.app.movtor.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stubird.app.movtor.util.Constans;

import java.io.IOException;

/**
 * 网页抓取类
 * @author Jusbin
 */
public class SpiderClient implements Client {

    final Logger logger = LoggerFactory.getLogger(SpiderClient.class);

    public static CloseableHttpClient client = HttpClients.createDefault();

    public String getPage(String url) {
        String strHtml = null;

        HttpGet httpGet = new HttpGet(url);

        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(Constans.REQUEST_TIME_OUT_MILLS)
                .setConnectTimeout(Constans.REQUEST_TIME_OUT_MILLS).setSocketTimeout(Constans.REQUEST_TIME_OUT_MILLS)
                .build();
        httpGet.setConfig(config);

        try {
            CloseableHttpResponse response = client.execute(httpGet);
            strHtml = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
            response.close();
        } catch (IOException e) {
            logger.debug("get page failed : {}, url={}", e, url);
        }

        return strHtml;
    }
}
