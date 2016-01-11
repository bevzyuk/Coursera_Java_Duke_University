import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        
        String qe1lastWord = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ")+1);
         String qe2lastWord = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ")+1);
        int result = qe1lastWord.compareTo(qe2lastWord);
        
        if(result == 0){
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        
        return result;
    }
}
