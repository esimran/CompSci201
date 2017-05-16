
public class RemoveMin {
	public ListNode remove(ListNode list){
		if(list.next == null)
			return null;
		int min = min(list);
		ListNode temp = list;
		if(list.info == min)
			return list.next;
		while(temp.next.next!= null){
			if(temp.next.info == min){
				temp.next = temp.next.next;
				return list;
			}
			temp = temp.next;
		}
		temp.next = null;
		return list;
	}
	
	public int min(ListNode list){
		int min = Integer.MAX_VALUE;
		while(true){
			if(list.info<min)
				min = list.info;
			if(list.next == null)
				break;
			list = list.next;
		}
		return min;
	}
}