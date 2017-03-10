import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class EqualCommon {
     public String[] matches(String[] a1, String[] a2) {
    	 TreeMap<String, Integer> num1 = new TreeMap<String, Integer>();
    	 TreeMap<String, Integer> num2 = new TreeMap<String, Integer>();
    	 int temp = 0;
    	 for(String s: a1){
    		 if(!num1.containsKey(s)){
    			 num1.put(s, 0);
    		 }
    		 temp = num1.get(s);
    		 temp++;
    		 num1.put(s, temp);
    	 }
    	 for(String s: a2){
    		 if( ! (num2.containsKey(s))){
    			 num2.put(s, 0);
    		 }
    		 temp = num2.get(s);
    		 temp++;
    		 num2.put(s, temp);
    	 }
    	 Set<String> set1 = num1.keySet();
    	 Set<String> set2 = num2.keySet();
    	 ArrayList<String> ans = new ArrayList<String>();
    	 set1.retainAll(set2);
    	 for(String s: set1){
    		 if(num1.get(s).equals(num2.get(s)))
    			 ans.add(s);
    	 }
    	 String[] finalans = new String[ans.size()];
    	 for(int i = 0; i<ans.size(); i++)
    		 finalans[i] = ans.get(i);
    	 return finalans;
     }
 }