import java.util.*;
public class CommonAPT8 {
    public String[] sort(String[] arr){
        Arrays.sort(arr, (String a, String b) -> a.compareTo(b));
        return arr;
    }
}
