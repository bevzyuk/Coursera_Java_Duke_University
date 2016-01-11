
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter  implements Filter{
    private double minDepth;
     private double maxDepth;
     private String name;
     public DepthFilter(String _name, double min, double max){
        minDepth = min;
        maxDepth = max;
        name= _name;
        }
     
        public boolean satisfies(QuakeEntry qe){
             if( qe.getDepth()>=minDepth &&
                qe.getDepth()<=maxDepth){
                return true;
                };          
                return false;
        }
    
         public String getName(){
    return getClass().getName();}
}
