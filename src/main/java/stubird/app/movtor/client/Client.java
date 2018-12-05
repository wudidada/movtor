package stubird.app.movtor.client;


public interface Client {

    /**
     * 获取网页
     * @param url
     * @return 网页的文本
     */
    String getPage(String url);
}
