import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class YnetRobot extends BaseRobot {
    private ArrayList<String> url;

    public YnetRobot(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        setRootWebsiteUrl();
        url = new ArrayList<String>();
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
    public String getLongestArticleTitle() throws IOException {
        int max=0;
        String paragraphs="";
        String title="";
        for (int articleIndex = 0; articleIndex < this.url.size(); articleIndex++) {
            Document article = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleArticle = article.getElementsByClass("mainTitle");
            Elements bodyArticle=article.getElementsByAttribute("data-text");
            for (int i=0 ; i<bodyArticle.size()-1 ; i++)
            paragraphs += bodyArticle.get(i).text();

            if (max<paragraphs.length()){
                max=paragraphs.length();
                title = titleArticle.text();
            }
            paragraphs="";
        }
        return title;

    }


    public void setUrl() throws IOException {

        String articleUrl;
        Document ynet = Jsoup.connect(this.getRootWebsiteUrl()).get();
        Elements articlesClass = ynet.getElementsByClass("textDiv");
        for (int i=0 ; i<4;i++){
            articleUrl=articlesClass.get(i).child(0).attr("href");
          this.url.add(articleUrl);
        }
        articlesClass=ynet.getElementsByClass("slotTitle medium");
        for (int i=4; i<6;i++){
            articleUrl=articlesClass.get(i).child(0).attr("href");
            this.url.add(articleUrl);
        }
        articlesClass=ynet.getElementsByClass("slotTitle small");
        for (int i=0 ; i<8;i++){
            articleUrl=articlesClass.get(i).child(0).attr("href");
            this.url.add(articleUrl);
        }

    }

    public void printUrl() {
        for (int i=0 ; i<url.size();i++) {
            System.out.println(url.get(i));
        }
    }
}
