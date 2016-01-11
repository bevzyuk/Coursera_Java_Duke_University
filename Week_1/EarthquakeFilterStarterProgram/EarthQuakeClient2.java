import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        MatchAllFilter maf = new MatchAllFilter();
        
        maf.addFilter(new MagnitudeFilter("MagnitudeFilter",0.0, 5.0));
        maf.addFilter(new DepthFilter("DepthFilter",-55000.0,-20000.0));
        maf.addFilter(new DistanceFilter("DistanceFilter",new Location(55.7308, 9.1153),3000000));
        maf.addFilter(new PhraseFilter("PhraseFilter","any","e"));
        
        ArrayList<QuakeEntry> mlist = filter(list, maf);
        
        for(QuakeEntry qe: mlist){
                System.out.println(qe);
        }
            System.out.println("Found "+mlist.size()+" elements");
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        MatchAllFilter maf = new MatchAllFilter();
        
        maf.addFilter(new MagnitudeFilter("MagnitudeFilter",1.0, 4.0));
        maf.addFilter(new DepthFilter("DepthFilter",-180000.0,-30000.0));
        maf.addFilter(new PhraseFilter("PhraseFilter","any","o"));
        
        ArrayList<QuakeEntry> mlist = filter(list, maf);
        
        for(QuakeEntry qe: mlist){
                System.out.println(qe);
        }
            System.out.println("Found "+mlist.size()+" elements");
            System.out.println("Filters used are: " + maf.getName());
    }
    
    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        //Filter f = new MinMagFilter(4.0); 
        Location city = new Location(39.7392, -104.9903);
        double maxDistance = 1000000;
        Filter maxDist = new DistanceFilter("DistanceFilter",city, maxDistance);
        Filter phrase = new PhraseFilter("PhraseFilter","end","a");
        //Filter magnitudeFilter = new MagnitudeFilter("MagnitudeFilter",3.5, 4.5);
        //Filter depthFilter = new DepthFilter("DepthFilter",-55000.0,-20000.0);
        ArrayList<QuakeEntry> m7  = eqc.filter(list, maxDist); 
        ArrayList<QuakeEntry> mlist = eqc.filter(m7, phrase);
        for (QuakeEntry qe: mlist) {             
            System.out.println(qe);
        } 
        System.out.println("Found result: "+ mlist.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
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
