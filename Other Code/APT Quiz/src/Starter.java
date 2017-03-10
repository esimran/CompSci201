import java.util.Arrays;
import java.util.HashSet;

public class Starter {
     public int begins(String[] words, String first) {
         int ans = 0;
         HashSet<String> cool = new HashSet<String>(Arrays.asList(words));
         for(String word: cool){
        	 if(word.charAt(0) == (first.charAt(0)))
        		 ans++;
         }
         return ans;
     }
 }