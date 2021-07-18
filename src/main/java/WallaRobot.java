import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WallaRobot extends BaseRobot{
    private ArrayList<String> url;

    public WallaRobot(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        url=new ArrayList<String>();
        this.setRootWebsiteUrl();
        this.setUrl();

    }

    @Override
    public String getRootWebsiteUrl() {
        return super.getRootWebsiteUrl();
    }


    public void setRootWebsiteUrl() {
        super.setRootWebsiteUrl("https://www.walla.co.il/");
    }

    @Override
    public Map<String, Integer> getWordsStatistics() throws IOException {
        Map<String,Integer> map = new HashMap<String, Integer>();
        return map;
    }

    @Override
    public int countInArticlesTitles(String text) {
        return 0;
    }

    @Override
    public String getLongestArticleTitle() {
        return null;
    }

    public void setUrl() throws IOException {
        String articleUrl;
        Document walla = Jsoup.connect(this.getRootWebsiteUrl()).get();
        Elements articles = walla.getElementsByClass("with-roof with-timer");
        articleUrl=articles.get(0).child(0).attr("href");
        this.url.add(articleUrl);

        articles = walla.getElementsByTag("li");
        for (int i=104; i<164;i++){
            articleUrl=articles.get(i).child(0).attr("href");
            this.url.add(articleUrl);
        }



    }





    public void getUrl() {
        for (int i=0 ; i<url.size();i++) {
            System.out.println(url.get(i));
        }
        //return url;
    }
}
