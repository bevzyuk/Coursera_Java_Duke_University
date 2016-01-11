
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index+1];
		sb.append(key1).append(" ").append(key2).append(" ");
		
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
			
		}
		
		return sb.toString().trim();
	}
	
    
    public void testIndexOf() {
        String[] words = new String("this is just a test yes this is a simple test").split("\\s+");
        ArrayList<String> myFollows;
        for(int key = 0; key< words.length; key++){
       //     System.out.println("Key: "+ words[key] + " indexOf: "+ indexOf(words, words[key],key));
            
        }
    }

	public int indexOf(String[] words, String target1, String target2, int start) {
        for(int k = start; k < words.length; k++){
            if(words[k].equals(target1) && words[k+1].equals(target2))
            return k+1;
        }
        return -1;
    }
	
	 private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText, key1, key2, pos);
            if(start == -1){break;}
            if(start + 2 >= myText.length){break;}
            String next = myText[start+1];
            follows.add(next);
            pos = start+1;
        //    System.out.println("Key: "+ key + " follows: "+ next);
        }
        return follows;
    }
}
 
