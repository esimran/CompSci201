import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class SyllableSorting {

	public static void main(String[] args){
		System.out.println("hello Felicia");
	}
	
	public String[] sortWords(String[] words) {
		return getSyllables(words[1]).toArray(new String[0]);
	}

	public ArrayList<String> getSyllables(String s) {
	String vowel = "aeiou";
	ArrayList<String> temp = new ArrayList<String>();
	int i = 0;
	int sum = 0;
	int check = 0;
	int k = 0;
	while(i<s.length()-1){
		while(sum != 0 && check == 0){
			if(vowel.indexOf(s.charAt(i)) != -1){
				sum++;
				return new ArrayList<String>();
			} else if((sum != 0 && vowel.indexOf(s.charAt(i)) == -1) || i == s.length()-2) {
				check = 1;
				sum = 1;
			}
//		if(check != 1){
		i++;
		k++;
//		}
		}
		temp.add(s.substring(i-k, i-1));
		i--;
		sum = 0;
		check = 0;
		k=0;
	}
	return temp;
}
}