import java.util.Arrays;
import java.util.HashSet;

public class SimpleWordGame {
	public int points(String[] player, String[] dictionary) {
		int ans = 0;
		HashSet<String> dict = new HashSet<String>(Arrays.asList(dictionary));
		HashSet<String> play = new HashSet<String>(Arrays.asList(player));
		play.retainAll(dict);
		for(String s: play) {
			ans += Math.pow(s.length(), 2);
		}
		return ans;
	}
}