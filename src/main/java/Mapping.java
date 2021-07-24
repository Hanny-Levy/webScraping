import java.util.ArrayList;
import java.util.Map;

public interface Mapping {

    default Map<String, Integer> getWordsIntoMap(String text, Map<String, Integer> map) {
        Integer count=1;
        ArrayList <String> words =this.separateTextIntoWords(text);
        for (int i=0 ; i<words.size()-1;i++){
            count=1;
            for (int j=i+1;j<words.size();j++){
                    if ((words.get(i)!=null)&&(words.get(i).equals(words.get(j)))){
                        count++;
                        words.set(j,null);
                    }
                }
            map.put(words.get(i),count);
            if (words.get(words.size()-1)!=null){
                count=1;
                map.put(words.get(words.size()-1),count);
            }
        }map.remove(null);
        map.remove("");
        return map;
    }

    default  ArrayList <String> separateTextIntoWords(String text){
        ArrayList <String> words = new ArrayList<String>();
       String word="";char letter;
        for (int i=0; i<text.length();i++){
            letter=text.charAt(i);
            if((letter>1487&&letter<1515)||(letter>96&&letter<123)||(letter>64&&letter<91))
              word+=text.charAt(i);
            else {
                switch (letter) {
                    case ' ':
                    case '.':
                    case ',':
                    case ':':
                        {
                        words.add(word);
                        word ="";
                    }
                    break;
                }


            }
            if (i==text.length()-1){
                words.add(word);
            }
           }

      return words;
    }
}