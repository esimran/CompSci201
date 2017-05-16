
public class ListCount {
	public int count(ListNode list){
		if(list == null)
			return 0;
		int count = 1;
		while(list.next != null){
			list = list.next;
			count++;
		}
		return count;
	}
}
