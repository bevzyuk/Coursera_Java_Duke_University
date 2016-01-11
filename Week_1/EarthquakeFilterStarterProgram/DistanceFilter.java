
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location location;
     private double maxDist;
     private String name;
     
     public DistanceFilter(String _name, Location _location, double max){
        location = _location;
        maxDist = max;
        name = _name;
        }
     
        public boolean satisfies(QuakeEntry qe){
             if( qe.getLocation().distanceTo(location) < maxDist ){
                return true;
                };          
                return false;
        }
    
         public String getName(){
    return name;}
}
