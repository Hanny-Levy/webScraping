import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class WallaRobot extends BaseRobot{

    public WallaRobot(String rootWebsiteUrl) {
        super(rootWebsiteUrl);
        this.setRootWebsiteUrl();


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







}
