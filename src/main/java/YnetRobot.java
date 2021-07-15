import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class YnetRobot extends BaseRobot{
    private ArrayList <String> url;
    public YnetRobot(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        setRootWebsiteUrl();
        url=new ArrayList<String>();
        this.setUrl();
    }

    @Override
    public String getRootWebsiteUrl() {
        return super.getRootWebsiteUrl();
    }


    public void setRootWebsiteUrl() {
        super.setRootWebsiteUrl("https://www.ynet.co.il/home/0,7340,L-8,00.html");
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
        String mainArticle;
        String minorArticle;
        Document site = Jsoup.connect(this.getRootWebsiteUrl()).get();
        Elements mainArticleClass=site.getElementsByClass("slotSubTitle");
        mainArticle=mainArticleClass.get(0).child(0).attr("href");
        this.url.add(mainArticle);
        Elements minorArticles = site.getElementsByClass("textDiv");
        for (int i=1 ; i<4;i++){
          minorArticle=minorArticles.get(i).child(0).attr("href");
          this.url.add(minorArticle);
        }
        minorArticles=site.getElementsByClass("slotTitle medium");
        for (int i=4; i<6;i++){
        minorArticle=minorArticles.get(i).child(0).attr("href");
            this.url.add(minorArticle);
        }
        minorArticles=site.getElementsByClass("slotTitle small");
        for (int i=0 ; i<8;i++){
            minorArticle=minorArticles.get(i).child(0).attr("href");
            this.url.add(minorArticle);
        }

    }

    public ArrayList<String> getUrl() {
        return url;
    }
}
