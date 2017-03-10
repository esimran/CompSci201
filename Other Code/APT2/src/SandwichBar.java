import java.util.Arrays;
import java.util.HashSet;

public class SandwichBar {
	public int whichOrder(String[] available, String[] orders) {
		HashSet<String> bar = new HashSet<String>(Arrays.asList(available));
		int ans = 0;
		for (String s: orders) {
			HashSet<String> cs = new HashSet<String>(Arrays.asList(s.split(" ")));
			if (bar.containsAll(cs)) {
				break;
			} else {
				ans += 1;
			}
		}
		if (ans >= orders.length) {
			ans = -1;
		}
		return ans;
	}
}