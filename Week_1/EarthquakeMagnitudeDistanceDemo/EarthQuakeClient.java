
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
     public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        
        if (where.equalsIgnoreCase("start")){
        for (QuakeEntry qe : quakeData) {
            String string = qe.getInfo(); 
            if (string.startsWith(phrase)) {
                answer.add(qe);
            }
        }
    }
        
        if(where.equalsIgnoreCase("end")){
        for (QuakeEntry qe : quakeData) 
        {
            String string = qe.getInfo(); 
            if (string.endsWith(phrase)) {
                answer.add(qe);
            }
        }
    }
        
        if(where.equalsIgnoreCase("any")){
            for (QuakeEntry qe : quakeData) {
            String string = qe.getInfo(); 
            if (string.contains(phrase)) {
                answer.add(qe);
            }
        }
    }    return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
            ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
            for (QuakeEntry qe: quakeData){
            if (qe.getDepth()> minDepth && qe.getDepth()<maxDepth){
                 answer.add(qe);   
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
                
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
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
    
    public void quakesByPhrase(){
            EarthQuakeParser parser = new EarthQuakeParser();
            String source = "data/nov20quakedata.atom";
            ArrayList<QuakeEntry> list = parser.read(source);
            String where = "any";
            String phrase = "Can";
        ArrayList<QuakeEntry> mlist = filterByPhrase(list, where, phrase);
        System.out.println("read data for " + list.size() + " quakes");
        /* for (QuakeEntry qe : mlist) {
             System.out.println(qe);
          }*/
        System.out.println("Found "+mlist.size()+" quakes that match "+phrase+" at the "+ where);
    }
    
    public void quakesOfDepth(){
            EarthQuakeParser parser = new EarthQuakeParser();
            String source = "data/nov20quakedata.atom";
            ArrayList<QuakeEntry> list = parser.read(source);
            Double minDepth = -4000.0;
            Double maxDepth = -2000.0;
        ArrayList<QuakeEntry> mlist = filterByDepth(list, minDepth, maxDepth);
        System.out.println("read data for " + list.size() + " quakes");
        System.out.println("Find quakes with depth between "+minDepth+" and "+ maxDepth);
      /*  for (QuakeEntry qe : mlist) {
             System.out.println(qe);
          }*/
        System.out.println("Found "+mlist.size()+" quakes that match that criteria");
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> mlist = filterByMagnitude(list, 5.0);
        System.out.println("read data for " + mlist.size() + " quakes");
        
        for (QuakeEntry qe : mlist) {
             System.out.println(qe);
          }
        System.out.println("Found "+mlist.size()+" quakes that match that criteria");
        
     /*   ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }*/
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
       // System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        //Location city  = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
            System.out.println("Read data for: " + list.size()+" quakes");
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("Found "+close.size()+" quakes that match that criteria");

    }
}
