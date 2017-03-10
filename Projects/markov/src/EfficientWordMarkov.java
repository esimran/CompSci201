import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EfficientWordMarkov implements MarkovInterface<WordGram> {
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private Map<WordGram, ArrayList<String>> myMap = new HashMap<WordGram, ArrayList<String>>();
	
	private static String PSEDUO_EOS = "";
	private static long RANDOM_SEED = 1234;
	
	public EfficientWordMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	public EfficientWordMarkov() {
		this(3);
	}
	
	@Override
	public void setTraining(String text) {
		myText = text.split("\\s+"); //splits mytext into string array of every word by spaces
		WordGram current = new WordGram(myText, 0, myOrder); //first wordgram is created
		for(int i = 0; i<(myText.length-myOrder); i++) { //runs for rest of wordgrams being created
			if(! myMap.containsKey(current)) //checks map value and adds if not there
				myMap.put(current, new ArrayList<String>());
			ArrayList<String> currentArray = myMap.get(current); //gets the values of the current wordgram 
			String next = myText[myOrder+i]; //adds the next word into array
			currentArray.add(next);
			current = current.shiftAdd(next); //changes current wordgram to next possible wordgram
		}
	}

	@Override
	public String getRandomText(int length) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);
		WordGram current = new WordGram(Arrays.copyOfRange(myText, index, index+myOrder), 0, myOrder); //starts wordgram in random position in text
		sb.append(current.toString());
		ArrayList<String> follows; //initialize arraylist
		for(int i = 0; i<(length-myOrder); i++) { //run to get proper length
			follows = getFollows(current); //follows contains potential words that are next after wordgram 
			if(follows == null) //if empty it stops 
				break;
			index = myRandom.nextInt(follows.size()); //get random word from follows
			if(follows.get(index).equals(PSEDUO_EOS)) //if the word is end of file it stops
				break;
			sb.append(" " + follows.get(index)); //ads space and the next word into string
			current = current.shiftAdd(follows.get(index)); //onto the next wordgram
		}
		return sb.toString();
	}

	@Override
	public ArrayList<String> getFollows(WordGram key) { //retrieves the values for wordgram key in map
		return myMap.get(key);		
	}

	@Override
	public int getOrder() {
		return myOrder;
	}

	@Override
	public void setSeed(long seed) {
		RANDOM_SEED = seed;
		myRandom = new Random(RANDOM_SEED);	
	}
}
