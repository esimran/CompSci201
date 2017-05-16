
public class ListNRev {
	public ListNode make(int n){
		int current = 2;
		int temp = 1;
		ListNode list = new ListNode(1);
		while(current<=n){
			list = new ListNode(current, list);
			temp = 1;
			while(temp<current){
				list = new ListNode(current, list);
				temp++;
			}
			current++;
		}
		return list;
	}
}
