package stubird.app.movtor.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stubird.app.movtor.client.SpiderClient;

public class RssSpider {

    static final Logger logger = LoggerFactory.getLogger(RssSpider.class);

    public static void main(String[] args) {
        String rssUrl = "https://hdcmct.org/torrentrss.php?rows=20&icat=1&ismalldescr=1&isize=1&iuplder=1";

        String xml = new SpiderClient().getPage(rssUrl);




    }

}
