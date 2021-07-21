import org.jsoup.select.Elements;

public class Article {

  private String title;
  private String subtitle ;
  private String text;

    public Article(){

    }
    public Article(String title, String subtitle, String text) {
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
    }


    public void setTitle(Elements title) {
        this.title = title.get(0).text();
    }

    public void setSubtitle(Elements subtitle) {
        this.subtitle = subtitle.text();
    }

    public void setText(Elements text) {
        for (int i=0; i<text.size()-1 ; i++){
            this.text += text.get(0).text();
        }
    }
    public void resetText(){
        this.text="";
    }
    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getText() {
        return text;
    }
}
