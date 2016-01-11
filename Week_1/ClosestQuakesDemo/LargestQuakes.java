import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {

    public void findLargestQuakes(){
    EarthQuakeParser parser = new EarthQuakeParser();
    
    String source = "data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list  = parser.read(source);
    Integer index = indexOfLargest(list);
    System.out.println("read data for " + list.size());
    ArrayList<QuakeEntry> mlist = getLargest(list, 50);
    //System.out.println("Max magnitude at the " +index+ " position");
    
    //System.out.println(list.get(index).toString());
        for(QuakeEntry qe: mlist){
        System.out.println(qe.toString());
        }
        System.out.println("Found: "+ mlist.size()+" Earthquakes");
    }
   
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, Integer howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
                ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry>();
                
                for(int j=0;j<howMany;j++){
                 Integer index = 0;
                 Double curMagnitude = copy.get(index).getMagnitude();
                for(int i = 0; i< copy.size();i++){
                    Double maxMagnitude = copy.get(i).getMagnitude();
                    if(maxMagnitude > curMagnitude){
                        curMagnitude=maxMagnitude; 
                        index = i;
                        //System.out.println("maxMagnitude "+maxMagnitude+" curMagnitude "+curMagnitude+" at index:"+index);   
                        // System.out.println("Current Max magnitude at the " +index+ " position");
                    }
                }
                list.add(copy.get(index));
                copy.remove(copy.get(index));
            }
                
        return list;
    }
    
    public Integer indexOfLargest(ArrayList<QuakeEntry> data){
        //ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry>();
            Integer index = 0;
            Double curMagnitude = data.get(index).getMagnitude();
        for(int i = 0; i< data.size();i++){
            Double maxMagnitude = data.get(i).getMagnitude();
            if(maxMagnitude > curMagnitude){
                curMagnitude=maxMagnitude; 
                index = i;
             //System.out.println("maxMagnitude "+maxMagnitude+" curMagnitude "+curMagnitude+" at index:"+index);   
           // System.out.println("Current Max magnitude at the " +index+ " position");
            }
        }        
        return index;
    }
   
}
