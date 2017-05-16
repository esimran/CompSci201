import java.util.*;

public class SpreadingNews {
	private int[] mySups;
	private ArrayList<ArrayList<Integer>> mySubs;

	public int minTime(int[] supervisors) {
		mySups = supervisors;
		mySubs = new ArrayList<ArrayList<Integer>>();
		for (int k = 0; k < supervisors.length; k++) {
			mySubs.add(new ArrayList<Integer>());
		}
		for (int k = 1; k < supervisors.length; k++) {
			mySubs.get(mySups[k]).add(k);
		}
		return minTime(0);
	}

	//
	/**
	 * helper method to return the minimum amount of time to inform spread news
	 * starting with particular boss
	 */
	public int minTime(int bossIndex) {
		if (mySubs.get(bossIndex).size() == 0)
			return 0;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (Integer i : mySubs.get(bossIndex)) {
			temp.add(minTime(i));
		}
		Collections.sort(temp);
		Collections.reverse(temp);
		for(int i= 0; i<temp.size(); i++){
			temp.set(i, temp.get(i)+1);
		}
		int max = 0;
		for(int i= 0; i<temp.size(); i++){
			if(temp.get(i)+i>max)
				max = temp.get(i)+i;
		}
		return Math.max(max, mySubs.get(bossIndex).size());
	}
}