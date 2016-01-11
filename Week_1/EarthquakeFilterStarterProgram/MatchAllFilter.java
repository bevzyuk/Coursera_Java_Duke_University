import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filterList;
    
    public MatchAllFilter(){filterList = new ArrayList<Filter>();}
    
    public void addFilter(Filter f){
        filterList.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f: filterList){
            if(!f.satisfies(qe)){
            return false;}
        }
    return true;
};
     public String getName(){
         String string = "";
         for(Filter f: filterList){
           string += " \n"+ f.getName();}
    return string;
   }
}
