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
       this.getLongestArticleTitle();
        //this.printUrl();
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
        Document walla = Jsoup.connect(this.getRootWebsiteUrl()).get();
        Elements articles = walla.getElementsByClass("with-roof with-timer");
        articleUrl = articles.get(0).child(0).attr("href");
        this.url.add(articleUrl);
         articles = walla.getElementsByTag("li");
         for (int i=104; i<124;i++){ //
             articleUrl=articles.get(i).child(0).attr("href");
             this.url.add(articleUrl);
         }
    }








    public void printUrl() {
        for (int i=0 ; i<url.size();i++) {
            System.out.println(url.get(i));
        }
    }
}
