
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMagn;
     private double maxMagn;
     private String name;
     
     public MagnitudeFilter(String _name, double min, double max){
        minMagn = min;
        maxMagn = max;
        name= _name;
        }
     
        public boolean satisfies(QuakeEntry qe){
        return (qe.getMagnitude()>=minMagn &&
                qe.getMagnitude()<=maxMagn);            
        }
    
         public String getName(){
    return name;}
}
