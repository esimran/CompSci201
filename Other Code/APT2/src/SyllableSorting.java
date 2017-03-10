import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class SyllableSorting {

	public static void main(String[] args){
		String[] ans = {"hgnibqqaxeiuteuuvksi", "jxbuzui", "zrotyqeruiydozui", "ywuuzkto", "lmopbookoagyco", "vredfvavvexliu" };
//		System.out.println(ans.length);
//		ans[0] = "cwsoygiokiuo";
		
		SyllableSorting myWork = new SyllableSorting();
		int w = myWork.sortWords(ans).length;
		String[] test = myWork.sortWords(ans);
		for(int i = 0; i<w; i++){
		System.out.println(test[i]);
		for(int k = 0; k<SyllableSorting.getSyllables(test[i]).size(); k++)
			System.out.println(SyllableSorting.getSyllables(test[i]).get(k));
		}
//		System.out.println(ans.length);
	}
	
	class myComp implements Comparator<String>{
		
		@Override
		public int compare(String a, String b){
			ArrayList<String> a1 = SyllableSorting.getSyllables(a);
			ArrayList<String> b1 = SyllableSorting.getSyllables(b);
			Collections.sort(a1);
			Collections.sort(b1);
			String apple = String.join("", a1);
			String ball = String.join("", b1);
			ArrayList<String> a2 = SyllableSorting.getSyllables(a);
			ArrayList<String> b2 = SyllableSorting.getSyllables(b);
			for(int i = 0; i< Math.min(a1.size(), b1.size()); i++){
				if(a1.get(i).compareTo(b1.get(i)) != 0)
					return a1.get(i).compareTo(b1.get(i));
			}
				
				if(a1.size()!=b1.size()){
					return a1.size()-b1.size();
				}
				
				
			for(int i = 0; i< Math.min(a2.size(), b2.size()); i++){
				if(a2.get(i).compareTo(b2.get(i)) != 0)
					return a2.get(i).compareTo(b2.get(i));
				}
				
					
			

			
			return apple.compareTo(ball);
		}
	}
	
	public String[] sortWords(String[] words) {
		ArrayList<String> setans = new ArrayList<String>();
		for(String s: words){
				setans.add(s);
		}
		String[] finans = new String[setans.size()];
		int i = 0;
		Collections.sort(setans, new myComp());
		for(String s: setans){
//			System.out.println(s);
			finans[i] = s;
			i++;
		}
		return finans;
	}

	public static ArrayList<String> getSyllables(String s) {
	String vowel = "a e i o u";
	ArrayList<String> temp = new ArrayList<String>();
	int i = 0;
	int sum = 0;
	int check = 0;
	int k = 0;
	while(i<s.length()-1){
		while((sum == 0 || check == 0) && i<s.length()){
			if(vowel.indexOf(s.charAt(i)) != -1){
				sum++;
			} else if((sum != 0 && vowel.indexOf(s.charAt(i)) == -1)) {
				check = 1;
			}
		if(check != 1){
		i++;
		k++;
		}
		}
		temp.add(s.substring(i-k, i));
		sum = 0;
		check = 0;
		k=0;
	}
	return temp;
}
}