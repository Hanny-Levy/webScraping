import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MakoRobot extends BaseRobot{
    private ArrayList<String> url;

    public MakoRobot(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        this.setRootWebsiteUrl();
        url=new ArrayList<String>();
        this.setUrl();

    }

    @Override
    public String getRootWebsiteUrl() {
        return super.getRootWebsiteUrl();
    }


    public void setRootWebsiteUrl() {
        super.setRootWebsiteUrl("https://www.mako.co.il");
    }

    @Override
    public Map<String, Integer> getWordsStatistics() {
        return null;
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
        Document mako = Jsoup.connect(this.getRootWebsiteUrl()).get();
        Elements articles = mako.getElementsByClass("slider_image_inside");
        for (int i = 0; i < articles.size(); i++) {
            articleUrl = getRootWebsiteUrl();
            articleUrl += articles.get(i).child(0).attr("href");
            this.url.add(articleUrl);
        }
        articles = mako.getElementsByTag("h5").val("data-ordering");

        for (int i = 25; i < 30; i++) {
            articleUrl = getRootWebsiteUrl();
           articleUrl += articles.get(i).children().attr("href");
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
