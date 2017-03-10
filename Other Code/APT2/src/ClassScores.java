import java.util.ArrayList;

public class ClassScores {
	public int[] findMode(int[] scores) {
		ArrayList<Integer> ansl = new ArrayList<Integer>();
		int[] counter = new int[101];
		int maxval = 0;
		for (int i=0; i<scores.length; i++) {
			counter[scores[i]] += 1;
		}
		for (int i=0; i<counter.length; i++) {
			if (counter[i] > maxval) {
				maxval = counter[i];
			}
		}
		for (int i=0; i<counter.length; i++) {
			if (maxval == counter[i]) {
				ansl.add(i);
			}
		}
		int[] ans = new int[ansl.size()];
		for (int i=0; i<ansl.size(); i++) {
			ans[i] = ansl.get(i);
		}
		return ans;
	}
}