
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
     private String phrase, name;
     
     public PhraseFilter(String _name, String _where,String _phrase){
          where=_where;
         phrase=_phrase;
         name= _name;
        }
     
        public boolean satisfies(QuakeEntry qe){
                
                if (where.equals("start")){
                    if( qe.getInfo().startsWith(phrase)){
                return true;
                };          
            }
              if (where.equals("any")){
                    if( qe.getInfo().contains(phrase)){
                return true;
                };          
            }
             if (where.equals("end")){
                    if( qe.getInfo().endsWith(phrase)){
                return true;
                };          
            }
             
                return false;
        }
    
         public String getName(){
    return name;}
}
