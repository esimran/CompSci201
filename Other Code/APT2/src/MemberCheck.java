import java.util.Arrays;
import java.util.HashSet;

public class MemberCheck {
	public String[] whosDishonest(String[] club1, String[] club2, String[] club3) {
		String[] ans;
		HashSet<String> place1 = new HashSet<String>(Arrays.asList(club1));
		HashSet<String> place2 = new HashSet<String>(Arrays.asList(club2));
		HashSet<String> place3 = new HashSet<String>(Arrays.asList(club3));
		HashSet<String> inter1to2 = new HashSet<String>(place1);
		HashSet<String> inter1to3 = new HashSet<String>(place1);
		HashSet<String> inter2to3 = new HashSet<String>(place2);
		inter1to2.retainAll(place2);
		inter1to3.retainAll(place3);
		inter2to3.retainAll(place3);
		inter1to2.addAll(inter1to3);
		inter1to2.addAll(inter2to3);
		int size = inter1to2.size();
		ans = inter1to2.toArray(new String[size]);
		Arrays.sort(ans);
		return ans;
	}
}