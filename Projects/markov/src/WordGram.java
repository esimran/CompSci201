import java.util.ArrayList;
import java.util.Arrays;

public class WordGram implements Comparable<WordGram>{
	private String[] myWords;
	private int myHash;
	
	public WordGram(String[] source, int start, int size){
		myWords = Arrays.copyOfRange(source, start, start+size); //copies the source string array with serach criteria to create wordgram
	}
	
	public int hashCode() {
		int hash = 0;
		for(int i = 0; i<length(); i++) {
			hash += i*i*myWords[i].hashCode(); //adds the first character integer value; //gets hashcode from first character integer value and hashcode from words plus numerical value of first character
		}
		myHash = hash;
		return myHash;
	}
	
	public boolean equals(Object other) {
		if(! (other instanceof WordGram)) //check if input is wordgram
			return false;
		WordGram wg = (WordGram) other;
		if(wg.length() != this.length()) //check same lengths of wordgram
			return false;
		for(int i = 0; i<wg.length(); i++) { //checks individual myWords for equal value
			if(! (this.myWords[i].equals(wg.myWords[i])))
				return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(WordGram obj) {
		if(this.equals(obj)) //if same object, then there is no difference
			return 0;
		if(this.length()>obj.length()) //checks length and responds accordingly
			return 1;
		if(this.length()<obj.length())
			return -1;
		for(int i = 0; i<this.length(); i++) { //checks individual characters for a difference
			if(this.myWords[i].compareTo(obj.myWords[i])>0)
				return 1;
			if(this.myWords[i].compareTo(obj.myWords[i])<0)
				return -1;
		}
		return 0; //if no difference, then returns they are equal
	}
	
	public int length() {
		return myWords.length;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{"); //first bracket
		for(int i = 0; i<myWords.length - 1; i++) { //adds comma and word for all words except last
			sb.append(myWords[i]);
			sb.append(", ");
		}
		sb.append(myWords[myWords.length-1]); //adds last word
		sb.append("}"); //adds final bracket
		return sb.toString();
	}
	
	public WordGram shiftAdd(String last) { //new wordgram with argument as last myWord, remvoes first word to compensate for size
		ArrayList<String> temp = new ArrayList<String>(Arrays.asList(this.myWords)); //turn to arraylist to modify
		temp.add(last);
		String[] size = new String[temp.size()];//used for size purposes
		return new WordGram(temp.toArray(size), 1, temp.size()-1); //returns new wordgram
	}
}
