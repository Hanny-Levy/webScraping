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
    public String getLongestArticleTitle() throws IOException {
        int max=0;
        String paragraphs="";
        String title="";
       for (int articleIndex = 0; articleIndex < this.url.size(); articleIndex++) {
            Document article = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleArticle=article.getElementsByTag("h1");
            Elements bodyArticle=article.getElementsByTag("p");
            for (int i=0 ; i<bodyArticle.size()-1; i++)
               paragraphs += bodyArticle.get(0).text();

           if (max<paragraphs.length()){
               max=paragraphs.length();
               title=titleArticle.get(0).text();
            }
           paragraphs="";


       }
        return title;

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

    public void printUrl() {
        for (int i=0 ; i<url.size();i++) {
            System.out.println(url.get(i));
        }
        //return url;
    }
}
