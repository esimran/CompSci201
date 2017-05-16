import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.omg.CORBA.Current;

/**
 * 
 * Using a sorted array of Term objects, this implementation uses binary search
 * to find the top term(s).
 * 
 * @author Austin Lu, adapted from Kevin Wayne
 * @author Jeff Forbes
 */
public class BinarySearchAutocomplete implements Autocompletor {

	Term[] myTerms;

	/**
	 * Given arrays of words and weights, initialize myTerms to a corresponding
	 * array of Terms sorted lexicographically.
	 * 
	 * This constructor is written for you, but you may make modifications to
	 * it.
	 * 
	 * @param terms
	 *            - A list of words to form terms from
	 * @param weights
	 *            - A corresponding list of weights, such that terms[i] has
	 *            weight[i].
	 * @return a BinarySearchAutocomplete whose myTerms object has myTerms[i] =
	 *         a Term with word terms[i] and weight weights[i].
	 * @throws a
	 *             NullPointerException if either argument passed in is null
	 */
	public BinarySearchAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null)
			throw new NullPointerException("One or more arguments null");
		myTerms = new Term[terms.length];
		for (int i = 0; i < terms.length; i++) {
			myTerms[i] = new Term(terms[i], weights[i]);
		}
		Arrays.sort(myTerms);
	}

	/**
	 * Uses binary search to find the index of the first Term in the passed in
	 * array which is considered equivalent by a comparator to the given key.
	 * This method should not call comparator.compare() more than 1+log n times,
	 * where n is the size of a.
	 * 
	 * @param a
	 *            - The array of Terms being searched
	 * @param key
	 *            - The key being searched for.
	 * @param comparator
	 *            - A comparator, used to determine equivalency between the
	 *            values in a and the key.
	 * @return The first index i for which comparator considers a[i] and key as
	 *         being equal. If no such index exists, return -1 instead.
	 */
	public static int firstIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
		int low = 0;
		int high = a.length - 1;
		int index = (a.length - 1) / 2;
		Term current;
		int value = 0;
		int ans = -1;
		while (high >= low) {
			index = (high + low) / 2;
			current = a[index];
			value = comparator.compare(key, current);
			if (value > 0) {
				low = index + 1;
			} else {
				if (value == 0) {
					ans = index;
				}
				high = index - 1;
			}
		}
		return ans;
	}

	/**
	 * The same as firstIndexOf, but instead finding the index of the last Term.
	 * 
	 * @param a
	 *            - The array of Terms being searched
	 * @param key
	 *            - The key being searched for.
	 * @param comparator
	 *            - A comparator, used to determine equivalency between the
	 *            values in a and the key.
	 * @return The last index i for which comparator considers a[i] and key as
	 *         being equal. If no such index exists, return -1 instead.
	 */
	public static int lastIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
		// TODO: Implement lastIndexOf
		int low = 0;
		int high = a.length - 1;
		int index = (a.length - 1) / 2;
		Term current;
		int value = 0;
		int ans = -1;
		while (high >= low) {
			index = (high + low) / 2;
			current = a[index];
			value = comparator.compare(key, current);
			if (value > 0) {
				low = index + 1;
			} else if (value == 0) {
				ans = index;
				low = index + 1;
			} else
				high = index - 1;

		}
		return ans;

	}

	/**
	 * Required by the Autocompletor interface. Returns an array containing the
	 * k words in myTerms with the largest weight which match the given prefix,
	 * in descending weight order. If less than k words exist matching the given
	 * prefix (including if no words exist), then the array instead contains all
	 * those words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then
	 * topKMatches("b", 2) should return {"bell", "bat"}, but topKMatches("a",
	 * 2) should return {"air"}
	 * 
	 * @param prefix
	 *            - A prefix which all returned words must start with
	 * @param k
	 *            - The (maximum) number of words to be returned
	 * @return An array of the k words with the largest weights among all words
	 *         starting with prefix, in descending weight order. If less than k
	 *         such words exist, return an array containing all those words If
	 *         no such words exist, reutrn an empty array
	 * @throws a
	 *             NullPointerException if prefix is null
	 */
	public Iterable<String> topMatches(String prefix, int k) {
		ArrayList<Term> TermList = new ArrayList<Term>();
		ArrayList<String> StringList = new ArrayList<String>();
		Term inputTerm = new Term(prefix, 0.0);
		Comparator<Term> comp = new Term.PrefixOrder(prefix.length());
		int bottomBound = firstIndexOf(myTerms, inputTerm, comp);
		int topBound = lastIndexOf(myTerms, inputTerm, comp);
		if(bottomBound == -1 || topBound == -1)
			return StringList;
		for(int i = bottomBound; i<=topBound; i++){
			TermList.add(myTerms[i]);
			}
		Collections.sort(TermList, new Term.ReverseWeightOrder());
		for(int i = 0; i<Math.min(TermList.size(), k); i++){
			StringList.add(TermList.get(i).getWord());
		}
		return StringList;
		}

	/**
	 * Given a prefix, returns the largest-weight word in myTerms starting with
	 * that prefix. e.g. for {air:3, bat:2, bell:4, boy:1}, topMatch("b") would
	 * return "bell". If no such word exists, return an empty String.
	 * 
	 * @param prefix
	 *            - the prefix the returned word should start with
	 * @return The word from myTerms with the largest weight starting with
	 *         prefix, or an empty string if none exists
	 * @throws a
	 *             NullPointerException if the prefix is null
	 * 
	 */
	public String topMatch(String prefix) {
		if(prefix == null)
			throw new NullPointerException("Prefix is null!");
		Term preTerm = new Term(prefix, 0.0);
		Comparator<Term> comp = new Term.PrefixOrder(prefix.length());
		int bottomBound = firstIndexOf(myTerms, preTerm, comp);
		int topBound = lastIndexOf(myTerms, preTerm, comp);
		if(bottomBound == -1 || topBound == -1)
			return "";
		double max = -1;
		int index = -1;
		for(int i = bottomBound; i<=topBound; i++){
			double currentWeight = myTerms[i].getWeight();
			if(currentWeight > max){
				max = currentWeight;
				index = i;
			}
		}
		return myTerms[index].getWord();
	}

	/**
	 * Return the weight of a given term. If term is not in the dictionary,
	 * return 0.0
	 */
	public double weightOf(String term) {
		int ind = firstIndexOf(myTerms, new Term(term, 0.0), new Term.PrefixOrder(term.length()));
		if(ind >= 0){
			return myTerms[ind].getWeight();
		}
		return 0.0;
	}
}
