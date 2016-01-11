
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		
		WordGram key = new WordGram(myText, index, myOrder);
		//key.printWordGram(key);
		//String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-myOrder; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			WordGram k2 = key.shiftAdd(next);
			key = k2;
			key.printWordGram(key);
		//	System.out.println("... " + sb.toString().trim());
		}
		
		return sb.toString().trim();
	}
	
    
    public void testIndexOf() {
        String[] words = new String("this is just a test yes this is a simple test").split("\\s+");
        ArrayList<String> myFollows;
        for(int key = 0; key< words.length; key++){
     //       System.out.println("Key: "+ words[key] + " indexOf: "+ indexOf(words, words[key],key));
            
        }
    }

	public int indexOf(String[] words, WordGram target, int start) {	    
	        
        for(int k = start; k < words.length-myOrder; k++){
            WordGram wg = new WordGram(words, k, myOrder);
         //   wg.printWordGram(wg);
            if(wg.equals(target))
            return k;
        }
        return -1;
    }
	
	 private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText, kGram, pos);
            if(start == -1){break;}
         //   if(start + key.length() >= myText.length){break;}
            String next = myText[start+1];
            follows.add(next);
            pos = start+1;
        //    System.out.println("Key: "+ key + " follows: "+ next);
        }
        return follows;
    }

}
