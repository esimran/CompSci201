import java.util.Arrays;

public class Anonymous {
	public int howMany(String[] headlines, String[] messages) {
		int ans = 0;
		int check = 0;
		int[] counthead = new int[26];
		int[] countmess = new int[26];
		String head = Arrays.toString(headlines);
		head.toLowerCase();
		head.toCharArray();
		for (int i = 0; i<head.length(); i++) {
			int a = Character.getNumericValue(head.charAt(i));
			a -= 10;
			if (a>=0 && a<=25) {
				counthead[a] += 1;
			}
		}
		System.out.print(counthead);
		for (String s: messages) {
			check = 0;
			s = s.toLowerCase();
			for (int i = 0; i<s.length(); i++) {
				int a = Character.getNumericValue(s.charAt(i));
				a -= 10;
				if (a>=0 && a<=25) {
					countmess[a] += 1;
				}
			}
			for (int i = 0; i<26; i++) {
				if (counthead[i] - countmess[i] >= 0) {
					check += 1;
				}
			}
			if (check == 26) {
				ans += 1;
			}
			for (int i = 0; i<26; i++) {
				countmess[i] = 0;
			}
		}
		return ans; 
	}
}
