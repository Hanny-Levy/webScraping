import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakoRobot extends BaseRobot implements Mapping{
    private ArrayList<String> url;
    private Map<String, Integer> map;

    public MakoRobot(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        this.setRootWebsiteUrl();
        this.url=new ArrayList<String>();
        this.map = new HashMap<String, Integer>();
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
    public Map<String, Integer> getWordsStatistics() throws IOException {
        for (int articleIndex=0 ; articleIndex<this.url.size();articleIndex++){
            Document connectArticle = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleTag=connectArticle.getElementsByTag("h1");
            Elements subTitleTag=connectArticle.getElementsByTag("h2");
            Elements textTag=connectArticle.getElementsByTag("p");
            Article article = new Article(titleTag,subTitleTag,textTag);
            String articleText=article.getTitle()+article.getSubtitle()+article.getText();
            this.map= getWordsIntoMap(articleText,this.map);
        }
        return this.map;
    }

    @Override
    public int countInArticlesTitles(String text) throws IOException {
        int count=0;
        for (int articleIndex=0 ; articleIndex<this.url.size();articleIndex++) {
            Document connectArticle = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleTag=connectArticle.getElementsByTag("h1");
        Elements subTitleTag=connectArticle.getElementsByTag("h2");
        Elements textTag=connectArticle.getElementsByTag("p");
        Article article = new Article(titleTag,subTitleTag,textTag);
        String articleText=article.getTitle()+article.getSubtitle();
            this.map=getWordsIntoMap(articleText,this.map);
}
        if (this.map.containsKey(text)){
                count=this.map.get(text);
                }
                return count;
    }

    @Override
    public String getLongestArticleTitle() throws IOException {
        int max=0;
        Article article = new Article();
       for (int articleIndex = 0; articleIndex < this.url.size(); articleIndex++) {
            Document connectArticle = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleTag=connectArticle.getElementsByTag("h1");
            Elements textTag=connectArticle.getElementsByTag("p");
           article.setText(textTag);
           if (max<article.getText().length()){
               max=article.getText().length();
               article.setTitle(titleTag);
           }
           article.resetText();
       }

        return article.getTitle();

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

    public ArrayList<String> getUrl() {
        return url;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void printUrl() {
        for (int i=0 ; i<url.size();i++) {
            System.out.println(url.get(i));
        }
    }
}
