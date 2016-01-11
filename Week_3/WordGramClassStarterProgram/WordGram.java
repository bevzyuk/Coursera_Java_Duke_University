
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i=0; i< myWords.length; i++) {
            ret += myWords[i]; 
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()) {
            return false;
        }
        for(int i = 0; i< myWords.length; i++){
            if (!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        int size = this.length();
        String[] dest = new String[size];
     //   System.arraycopy(this, 1, dest, 0, size-1);
        for(int i=1; i< this.length();i++) {
            dest[i-1]=this.wordAt(i);
        }
        dest[this.length()-1] = word;
        WordGram newWordGram = new WordGram(dest, 0, size);
        return newWordGram;
    }

    
    public void textShiftAdd(String string) {
        String[] st= string.split("\\s+");
        WordGram wg = new WordGram(st, 0 , 4);
        printWordGram(wg);
        String word = st[4];
        WordGram wg1 = shiftAdd(word);
        printWordGram(wg1);
        
        
    }

        public  void printWordGram(WordGram wg) {
            StringBuilder sb= new StringBuilder();
            for (int i = 0; i<wg.length();i++) {
                sb.append(wg.wordAt(i)).append(" ");
        }
        System.out.println(sb.toString());
        }
}