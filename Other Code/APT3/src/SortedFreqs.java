import java.util.TreeMap;

public class SortedFreqs {
      public int[] freqs(String[] data) {
    	  TreeMap<String, Integer> datMap = new TreeMap<String, Integer>();
    	  for(String s: data){
    		  if(! datMap.containsKey(s))
    			  datMap.put(s, 0);
    		  int val = datMap.get(s)+1;
    		  datMap.put(s, val);
    	  }
    	  int[] ans = new int[datMap.size()];
    	  int i = 0;
    	  for(String s: datMap.keySet()){
    		  ans[i] = datMap.get(s);
    		  i++;
    	  }
    	  return ans;
      }
   }