
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
        private HashMap<String, ArrayList<String>> map = new HashMap<String,ArrayList<String>>();;
        
    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        numChar = num;
        }
    
    public void setRandom(int seed){
        myRandom.setSeed(seed);
    }
    
    public void buildMap(){        
      String key;
      System.out.println("Length " + myText.length()/*+" "+ myText*/);
       ArrayList<String> follows;
       for (int i = 0;i< myText.length()-numChar+1;i++ ) {
            key = myText.substring(i, i+numChar);
           if (map.isEmpty() || !map.containsKey(key)) {
              follows= new ArrayList<String>();   
                int pos = 0;
                while(pos <= myText.length()){
                int start = myText.indexOf(key, pos);
                if(start == -1 || start + key.length() >= myText.length()){
                 //   System.out.println("Key: " + key);
                    break;}
                String next = myText.substring(start+key.length(), start+key.length()+1);
                follows.add(next);
                pos = start+key.length();
              }
                map.put(key,follows);
                // debug string
                //System.out.println("KEY: "+ key+ " Follows count: "+ follows.size());
                }     
            } 
}

    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> mFollows = new ArrayList<String>();
        if(!map.containsKey(key) || map.get(key).size()==0){
        return mFollows ;
        }
       return mFollows = map.get(key);
    }

    public void printHashMapInfo() {
       System.out.println("HashMap size is: " + map.size());
       String maxKey=null;
       int size = 0;
       StringBuilder sb = new StringBuilder();
         if(map.size()!=0){
            for(String k: map.keySet()){
                //    System.out.println("Key: "+ k);
             // ArrayList<String> set = map.get(k); 
             // System.out.print("Set size: " + set.size());
              for(int i = 0; i< map.get(k).size();i++){
            //   System.out.print(" value: "+ map.get(k).get(i));
                }
            //    System.out.println(sb.toString());
                //sb=null;
                if (map.get(k).size()> size){
                    size= map.get(k).size();
                    maxKey = k;
                }
           }
       }
       System.out.println("Maximum key is: " + maxKey+ " max size is: "+ size);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-numChar);
        String key = myText.substring(index, index+numChar);
        sb.append(key);
        for(int k=0; k < numChars-numChar; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size()==0){break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return new String("EfficientMarkovModel class of "+ numChar+" number");
        
    }
}
