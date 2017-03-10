import java.util.Arrays;
import java.util.Comparator;

public class TheBestName {
	
	class WeightComp implements Comparator<String>{
		public int compare(String a, String b){
			int diff = weight(b)-weight(a);
			if(diff != 0)
				return diff;
			return a.compareTo(b);
		}
	}
	
	public int weight(String s){
		int weight=0;
		if(s.equals("JOHN"))
			return Integer.MAX_VALUE;
		for(int k = 0; k<s.length(); k++)
			weight+=s.charAt(k)-'A'+1;
		return weight;
	}
	
      public String[] sort(String[] names) {
    	  Arrays.sort(names, new WeightComp());
    	  return names;
      }
      
      public static void main(String[] args){
    	  String[] a = {"JOHN", "A", "AA", "AAA", "JOHN", "B", "BB", "BBB", "JOHN", "C", "CC", "CCC", "JOHN"};
    	  TheBestName b = new TheBestName();
    	  System.out.println(Arrays.toString(b.sort(a)));
      }
}