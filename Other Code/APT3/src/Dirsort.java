import java.util.Arrays;
import java.util.Comparator;

public class Dirsort {
	class APTComp implements Comparator<String> {
		public int compare(String a, String b) {
			String[] d1 = a.split("/");
			String[] d2 = b.split("/");
			int diff = d1.length - d2.length;
			if(diff == 0){
				for(int i = 0; i<d1.length; i++){
					if(d1[i].compareTo(d2[i]) != 0)
						return d1[i].compareTo(d2[i]);
				}
			}
			return diff;
		}
	}

	public String[] sort(String[] dirs) {
		Arrays.sort(dirs, new APTComp());
		return dirs;
	}
}
