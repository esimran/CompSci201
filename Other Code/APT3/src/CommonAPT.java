import java.util.*;
public class CommonAPT {
    
    class APTComp implements Comparator<String>{

        /**
         * return < 0 or 0 or > 0 according to a < b, a == b, a > b
         */
        public int compare(String a, String b) {
            return a.compareTo(b);   // default is to use the default String comparison
        }
        
    }
    
    public String[] sort(String[] arr){
        String[] ret = arr;
        Arrays.sort(ret, new APTComp());
        return ret;
    }
}
