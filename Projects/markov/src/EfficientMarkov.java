import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EfficientMarkov implements MarkovInterface<String> {
	private String myText;
	private Random myRandom;
	private int myOrder;
	private Map<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
	
	private static String PSEDUO_EOS = "";
	private static long RANDOM_SEED = 1234;
	
	public EfficientMarkov(int order) {
		myRandom = new Random();
		myOrder = order;
	}
	
	public EfficientMarkov() {
		this(3);
	}
	
	public void setTraining(String text) {
		myText = text;
		String current = myText.substring(0, getOrder()); //Gets first substring of length order
		ArrayList<String> currentArray = null; //initializes variable used to get values from map
		for(int i = 0; i<(size() - getOrder()); i++) { //runs for entire text
			if (! myMap.containsKey(current)) //check if in map
				myMap.put(current, new ArrayList<String>());
			currentArray = myMap.get(current); //values from map
			String next = myText.substring(myOrder + i, myOrder+ i+1); //next is next word in string
			currentArray.add(next);
			current = current.substring(1) + next; //changes current string into the next string that is examined
			}
	}
	
	public String getRandomText(int length) {
		if(myText.length() == myOrder)
			return "";
		StringBuilder sb = new StringBuilder();
		int index = 0;
		if(size() - getOrder()>0)
			index = myRandom.nextInt(size() - getOrder()); 
		String current = myText.substring(index, index+getOrder()); //gets random substring with length order from text
		sb.append(current); //adds the first substring
		ArrayList<String> follows; //initialize the string array that contains following words
		String next; //initialize next word
		for(int i = 0; i<(length-getOrder()); i++) { //runs until correct length will be gotten
			follows = getFollows(current); //follows contains characters that are present after the current substring
			if (follows == null) //error check if empty
				break;
			index = myRandom.nextInt(follows.size()); 
			next = follows.get(index); //picks random word from follows
			if (next.equals(PSEDUO_EOS)) //check if the next character is the end of string
				break;
			sb.append(next);
			current = current.substring(1) + next; //redefine the substring in question to continue generating text 
		}
		return sb.toString();
	}
	
	public ArrayList<String> getFollows(String key) { //get values of follow from map
		return myMap.get(key);
	}
	
	public int size() { //value of text size
		return myText.length();
	}
				
	@Override
	public int getOrder() { //value of order
		return myOrder;
	}

	@Override
	public void setSeed(long seed) {
		RANDOM_SEED = seed;
		myRandom = new Random(RANDOM_SEED);	
	}
	
}
