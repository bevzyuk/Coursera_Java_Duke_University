
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace
{
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i=0; i<quakes.size()-1;i++){
            if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude()){return false;}
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
            int numSorted = 0;
        for(int i = 0; i<in.size()-1; i++){
            if (!checkInSortedOrder(in)){
                numSorted++;
                onePassBubbleSort(in, numSorted);}
        }
        System.out.println("For sorting need this array were needed "+ numSorted+ " elements");
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
          for(int j=0;j<quakeData.size()-1;j++){             
            if(quakeData.get(j).getMagnitude()>quakeData.get(j+1).getMagnitude()){
                QuakeEntry qeMax = quakeData.get(j);
                QuakeEntry qeMin = quakeData.get(j+1);
                //Swap element if first element is bigger that second
                quakeData.set(j+1,qeMax);
                quakeData.set(j, qeMin);
            }
        
          }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numSorted = 0;
       for (int i=0; i< in.size(); i++) {
        if(!checkInSortedOrder(in)){
            numSorted++;
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        }
        System.out.println("For sorting need this array were needed "+ numSorted+ " elements");
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        //System.out.println("Read data for "+ in.size()+" quakes");
        for(int i = 0; i<in.size()-1;i++){
            onePassBubbleSort(in, i);
            System.out.println("Printing Quakes after pass "+i);
             for(QuakeEntry qe: in){
                 System.out.println(qe);
                }        
            
         }           
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

   
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
            
        for(int i=0; i< /*70*/in.size();i++){
            int maxIdx=getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(maxIdx);
            in.set(i,qmin);
            in.set(maxIdx,qi);
            
        }
       // System.out.println("Sorted count: "+ i);
    }

    public Integer getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
            for(int i=from+1;i<quakeData.size(); i++){
                if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()){
                maxIndex = i;
                }
            }
        return maxIndex;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
  //     String source = "data/earthquakeDataSampleSix2.atom";
         //String source = "data/earthQuakeDataDec6sample1.atom";
       String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
     /* System.out.println("Before sortings: ");
      for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        System.out.println("After sortings: ");
     */ 
      //  sortByMagnitudeWithBubbleSort(list);
          sortByMagnitudeWithBubbleSortWithCheck(list);
      // sortByLargestDepth(list);
        //  sortByMagnitudeWithCheck(list);
       System.out.println("EarthQuakes in sorted order"); 
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
