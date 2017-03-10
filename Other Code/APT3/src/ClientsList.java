import java.util.*;

public class ClientsList {
	class Person {
		private String myFirst;
		private String myLast;

		public Person(String s) {
			if(s.contains(",")){
				myFirst = s.split(", ")[1];
				myLast = s.split(", ")[0];
			}
			else{
			myFirst = s.split(" ")[0];
			myLast = s.split(" ")[1];
			}
		}

		public String getLast() {
			return myLast;
		}

		public String getFirst() {
			return myFirst;
		}
	}

	public String[] dataCleanup(String[] names) {
		Person[] pplarr = new Person[names.length];
		int i = 0;
		for(String s: names){
			pplarr[i] = new Person(s);
			i++;
		}
		Comparator<Person> comp = 
				Comparator.comparing(Person::getLast)
		                  .thenComparing(Person::getFirst);
		Arrays.sort(pplarr,comp);
		String[] ans = new String[names.length];
		i = 0;
		for(Person p: pplarr){
			ans[i] = p.myFirst+" "+p.myLast;
			i++;
		}
		return ans;

	}

}
