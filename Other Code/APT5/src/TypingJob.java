
public class TypingJob {
	public int bestTime(int[] pages){
		return bestTime(pages, 0, 0, 0, 0);
	}

	public int bestTime(int[] pages, int index, int t1, int t2, int t3) {
		if(index>=pages.length)
			return Math.max(Math.max(t1, t2), t3);
		int assign1 = bestTime(pages, index+1, t1+pages[index], t2, t3);
		int assign2 = bestTime(pages, index+1, t1, t2+pages[index], t3);
		int assign3 = bestTime(pages, index+1, t1, t2, t3+pages[index]);
		return Math.min(Math.min(assign1, assign2), assign3);
	}
}
