import java.util.ArrayList;
import java.util.Arrays;

public class ListStretch {
	public ListNode stretch (ListNode list, int amount){
		if(list == null)
			return null;
		if(amount == 1)
			return list;
		ArrayList<Integer> info = getSize(list);
		int temp = 1;
		int size = info.size();
		ListNode ans = new ListNode(info.get(size-1));
		while(temp+1<=amount){
			ans = new ListNode(info.get(size-1), ans);
			temp++;
		}
		size--;
		while(size>0){
			temp = 1;
			while(temp<=amount){
				ans = new ListNode(info.get(size-1), ans);
				temp++;
			}
			size--;
		}
		return ans;
	}
	
	public ArrayList<Integer> getSize(ListNode list){
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while(list.next != null){
			ans.add(list.info);
			list = list.next;
		}
		ans.add(list.info);
		return ans;
	}
}
