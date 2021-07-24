import org.jsoup.select.Elements;

public class Article {

  private String title;
  private String subtitle ;
  private String text;

    public Article(){

    }
    public Article(Elements title, Elements subtitle, Elements text) {
        this.setTitle(title);
        this.setSubtitle(subtitle);
        this.setText(text);
    }


    public void setTitle(Elements title) {
        this.title = title.text()+" ";
    }

    public void setSubtitle(Elements subtitle) {
        this.subtitle = subtitle.text()+" ";
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle+" ";
    }
    public void setText(Elements text) {
        for (int i=0; i<text.size()-1 ; i++){
            this.text +=" "+text.get(i).text();
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
