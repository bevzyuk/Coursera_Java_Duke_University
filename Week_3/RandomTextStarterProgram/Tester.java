
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
 
public class Tester {
    
    public void testGetFollows(){
       MarkovOne markovOne = new MarkovOne();
       String mString = "this is a test yes this is a test.";
       markovOne.setTraining(mString);
       String test = "es";
       ArrayList<String> follows = markovOne.getFollows(test);
       System.out.println("Size of follows for " + test + " is " + follows.size());
       for(String str: follows){
           System.out.println(str);
        }  
    }
    
    
    public void testGetFollowsWithFile() {
        
        MarkovOne markovOne = new MarkovOne();
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		markovOne.setTraining(st);
		String test = "he";
       ArrayList<String> follows = markovOne.getFollows(test);
       System.out.println("Size of follows for " + test + " is " + follows.size());
      /* for(String str: follows){
           System.out.println(str);
        }*/  
    }


}
