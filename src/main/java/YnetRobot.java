import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YnetRobot extends BaseRobot implements Mapping {
    private ArrayList<String> url;
    private Map<String, Integer> map;

    public YnetRobot(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        this.setRootWebsiteUrl();
        this.url = new ArrayList<String>();
        this.map = new HashMap<String, Integer>();
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
    public Map<String, Integer> getWordsStatistics() throws IOException {
        for (int articleIndex=0 ; articleIndex<this.url.size();articleIndex++){
            Document connectArticle = Jsoup.connect(this.url.get(articleIndex)).get();
            Elements titleClass = connectArticle.getElementsByClass("mainTitle");
            Elements subTitleClass = connectArticle.getElementsByClass("subTitle");
            Elements textKey=connectArticle.getElementsByAttribute("data-text");
            Article article = new Article(titleClass,subTitleClass,textKey);
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
            Elements titleClass = connectArticle.getElementsByClass("mainTitle");
            Elements subTitleClass = connectArticle.getElementsByClass("subTitle");
            Elements textKey=connectArticle.getElementsByAttribute("data-text");
            Article article = new Article(titleClass,subTitleClass,textKey);
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
            Elements titleClass = connectArticle.getElementsByClass("mainTitle");
            Elements textKey=connectArticle.getElementsByAttribute("data-text");
            article.setText(textKey);
            if (max<article.getText().length()){
                max=article.getText().length();
                article.setTitle(titleClass);
            }
            article.resetText();
        }

        return article.getTitle();

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

    public Map<String, Integer> getMap() {
        return map;
    }

    public void printUrl() {
        for (int i=0 ; i<url.size();i++) {
            System.out.println(url.get(i));
        }
    }

    public ArrayList<String> getUrl() {
        return url;
    }
}
