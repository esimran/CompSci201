import java.util.Arrays;
import java.util.Comparator;

public class SlowSort {
	/**
	 * Exchange the elements at index i & j in array a. The other array elements
	 * should be unchanged
	 */
	public static void exchange(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	/**
	 * Returns the index of the minimum element in the range [index, a.length-1]
	 * 
	 * @param a
	 *            non-null array
	 * @param index
	 *            the index from which to start the search. 0 <= index <
	 *            a.length
	 * @return the index of the minimum element starting at the specified index
	 */
	public static int min(String[] a, int index, Comparator<String> comp) {
		String min = a[index];
		int ans = index;
		for(int i = index; i<a.length; i++){
			if(comp.compare(a[i], min) < 0){
				min = a[i];
				ans = i;
			}
		}
		return ans;
	}

	/**
	 * Sort array a using comp using selection sort
	 */
	public static void sort(String[] a, Comparator<String> comp) {
		for (int k = 0; k < a.length; k++)
			exchange(a, k, min(a, k, comp));
	}

	public static void main(String[] args) {
		// TODO add more tests
		String[] words = { "a", "A", "apple", "b", "b", "c" };
		System.out.println("Orig: " + Arrays.toString(words));
		sort(words, String.CASE_INSENSITIVE_ORDER);
		System.out.println("Sort: " + Arrays.toString(words));
	}
}
