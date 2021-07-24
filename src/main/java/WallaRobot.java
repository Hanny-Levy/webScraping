import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WallaRobot extends BaseRobot implements Mapping{
    private ArrayList<String> url;
    private Map<String, Integer> map;

    public WallaRobot(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        this.url=new ArrayList<String>();
        this.map = new HashMap<String, Integer>();
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
        Element subTitleClass;String articleText;Article article;
        for (int articleIndex=0 ; articleIndex<this.url.size();articleIndex++) {
            Document connectArticle = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleTag = connectArticle.getElementsByTag("h1");
            Elements textTag = connectArticle.getElementsByTag("p");
            if (articleIndex == 0) {
                subTitleClass = connectArticle.getElementsByClass("item-main-content").get(0).child(0).child(1);
                article = new Article(titleTag, textTag, textTag);
                article.setSubtitle(subTitleClass.text());
                articleText = article.getTitle() + article.getSubtitle() + article.getText();
            } else {
                article=new Article();
                article.setTitle(titleTag);article.setText(textTag);
                articleText = article.getTitle() + article.getText();
            }
            this.map = getWordsIntoMap(articleText, this.map);
        }

        return this.map;
    }


    @Override
    public int countInArticlesTitles(String text) throws IOException {
        int count=0;Element subTitleClass;String articleText;Article article;
        for (int articleIndex=0 ; articleIndex<this.url.size();articleIndex++) {
            Document connectArticle = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleTag=connectArticle.getElementsByTag("h1");
            Elements textTag=connectArticle.getElementsByTag("p");
            if (articleIndex == 0) {
                subTitleClass = connectArticle.getElementsByClass("item-main-content").get(0).child(0).child(1);
                article = new Article(titleTag, textTag, textTag);
                article.setSubtitle(subTitleClass.text());
            } else {
                article=new Article();
                article.setTitle(titleTag);article.setSubtitle("");
            }
            articleText=article.getTitle()+article.getSubtitle();
            this.map=getWordsIntoMap(articleText,this.map);
        }
        if (this.map.containsKey(text)){
            count=this.map.get(text);
        }
        System.out.println(count);
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

    public ArrayList<String> getUrl() {
        return url;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setUrl() throws IOException {
        String articleUrl;
        Document walla = Jsoup.connect(this.getRootWebsiteUrl()).get();
        Elements articles = walla.getElementsByClass("with-roof ");
        articleUrl = articles.get(0).child(0).attr("href");
        this.url.add(articleUrl);
         articles = walla.getElementsByTag("li");
         for (int i=106; i<126;i++){
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
