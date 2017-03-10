import java.util.ArrayList;

public class PairDown {
     public int[] fold(int[] list) {
    	 ArrayList<Integer> ans = new ArrayList<Integer>();
         if(list.length % 2 == 0)
        	 for(int i = 0; i<list.length; i+=2)
        		 ans.add(list[i]+list[i+1]);
         else{
        	 for(int i = 0; i<list.length-1; i+=2)
        		 ans.add(list[i]+list[i+1]);
        	 ans.add(list[list.length-1]);
         } 	 
         int[] finalans = new int[ans.size()];
         for(int i = 0; i<ans.size(); i++)
        	 finalans[i] = ans.get(i);
         return finalans;
     }
 }