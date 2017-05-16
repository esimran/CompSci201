
public class RemoveN {
	public ListNode move(ListNode list, int remove, int n) {
		if(find(list, n) == null){
			return list;
		}
		ListNode current = find(list, n);
		ListNode seq = find(list, n);
		for(int i = 0; i<remove; i++){
			seq = seq.next;
		}
		ListNode old = current.next;
		current.next = seq.next;
		seq = seq.next;
		seq.next = old;
		return list;
	}

	public ListNode find(ListNode list, int n) {
		ListNode ans = list;
		while (list.next != null) {
			if (list.info == n) {
				return ans;
			}
			list = list.next;
		}
		if (list.info == n) {
			return ans;
		}
		return null;
	}
}
