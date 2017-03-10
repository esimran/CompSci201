import java.util.Arrays;
import java.util.TreeMap;

public class SortByFreqs {
	private TreeMap<String, Integer> counter = new TreeMap<String, Integer>();
	public String[] sort(String[] data) {
		for(String s: data){
			if(! counter.containsKey(s))
				counter.put(s, 0);
		Integer value = counter.get(s);
		value ++;
		counter.put(s, value);
		}
		String[] words = counter.keySet().toArray(new String[0]);
		Arrays.sort(words, (String a, String b) ->
		counter.get(b)-counter.get(a));
		return words;
		}		
      }