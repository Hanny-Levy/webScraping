import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) throws IOException {
            while (Def.gameOver==false) {
                try {
                    new GuessingGame();
                } catch (Exception exception) {
                    System.out.println("Invalid selection Please try again");
                }

            }

        }

    private Scanner intScanner ;
    private Scanner stringScanner ;
    private MakoRobot makoRobot ;
    private WallaRobot wallaRobot;
    private YnetRobot ynetRobot;
    private int points;
    private int userSite;
    private String player;
    private ArrayList<String> userWords;
    public GuessingGame() throws IOException {
        intScanner = new Scanner(System.in);
        stringScanner=new Scanner(System.in);
        this.points=0;
        this.userWords=new ArrayList<>();
        Def.gameOver=false;
        this.firstMenu();
        this.secondMenu();
        this.thirdMenu();

    }

    public void firstMenu() throws IOException {
        System.out.println("Welcome to the guessing game, what's your name?");
        this.player=stringScanner.nextLine();
            System.out.println("Nice to meet you " + this.player+" , which site do you want to scan?");
            System.out.println("Mako - press 1");
            System.out.println("Walla - press 2");
            System.out.println("Ynet - press 3");
            this.userSite=this.intScanner.nextInt();
                switch (userSite) {
                    case Def.MAKO: {
                        this.setMakoRobot();
                        System.out.println("Welcome to Mako");
                        System.out.println("Our longest article's title is: ");
                        System.out.println(this.makoRobot.getLongestArticleTitle());

                    }
                    break;
                    case Def.WALLA: {
                        this.setWallaRobot();
                        System.out.println("Welcome to Walla");
                        System.out.println("Out longest article's title is: ");
                        System.out.println(this.wallaRobot.getLongestArticleTitle());

                    }
                    break;
                    case Def.YNET: {
                        this.setYnetRobot();
                        System.out.println("Welcome to Ynet");
                        System.out.println("Out longest article's title is: ");
                        System.out.println(this.ynetRobot.getLongestArticleTitle());
                    }
                    break;
                    default:this.firstMenu();
                    break;

                }

            }

            public void secondMenu() throws IOException {
            Map<String,Integer>map=new HashMap<>();
            String word;
                System.out.println("Guess the most common words on the site"+'\n'+"*You only have five guesses!*");
                for (int guess=1 ;guess<=5 ; guess++){
                    word = this.stringScanner.nextLine();

                    switch (this.userSite){
                    case Def.MAKO: {
                        map=this.makoRobot.getWordsStatistics();
                    }
                        break;
                        case Def.WALLA:{
                            map=this.wallaRobot.getWordsStatistics();

                        }break;
                            case Def.YNET:{
                                map=this.ynetRobot.getWordsStatistics();

                            }break;

                }
                    if (map.containsKey(word)&&(!userWords.contains(word))){
                        this.points+=map.get(word);
                        System.out.println("Correct! you have "+this.points+" points! ");
                        this.userWords.add(word);
                    }
                    else System.out.println("You were close!"+'\n'+"You have "+(5-guess)+" more guesses left.");
            }

            }
    public void thirdMenu() throws IOException {
        int pointsText=0;
        System.out.println("Enter text that may appear in the article headings on the site. This text can be 1-20 characters long.");
        String text=this.stringScanner.nextLine();
        switch (this.userSite){
            case Def.MAKO: {
                pointsText=this.makoRobot.countInArticlesTitles(text);
            }
            break;
            case Def.WALLA:{
                pointsText=this.wallaRobot.countInArticlesTitles(text);

            }break;
            case Def.YNET:{
                pointsText=this.ynetRobot.countInArticlesTitles(text);
            }break;
        }
        if ((pointsText>0)&&(!this.userWords.contains(text))){
            this.points+=250;
            System.out.println("Correct ! You have accumulated another 250 points!");
        } else System.out.println("Wrong guess!");
        System.out.println("The game is over ! you were great :)"+'\n'+"Your final score: "+this.points);
        Def.gameOver=true;
    }


    public void setMakoRobot() throws IOException {
        this.makoRobot =new MakoRobot("");
    }

    public void setWallaRobot() throws IOException {
        this.wallaRobot =new WallaRobot("");
    }

    public void setYnetRobot() throws IOException {
        this.ynetRobot =new YnetRobot("");
    }

    public MakoRobot getMakoRobot() {
        return makoRobot;
    }

    public WallaRobot getWallaRobot() {
        return wallaRobot;
    }

    public YnetRobot getYnetRobot() {
        return ynetRobot;
    }

    public int getPoints() {
        return points;
    }

    public int getUserSite() {
        return userSite;
    }

}






