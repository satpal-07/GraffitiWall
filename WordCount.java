
/**
 * This class models a word frequency count.  Each WordCount object
 * contains a word and a count of the frequency of that word.  There 
 * is also a compare method that will compare the WordCount with another
 * given WordCount.
 * 
 * The WordCount class uses the Text class which provides a means of
 * displaying the word on a canvas.
 * 
 * @author Dr A J Beaumont 
 * @version November 2011
 */
public class WordCount implements Comparable<WordCount>
{
    // instance variables - replace the example below with your own
    private int count;
    private Text word;

    /**
     * Constructor for objects of class WordCount.  A new
     * WordCount object contains a Text object representing 
     * the given text and has a count of 1.
     * @param word the word for this frequency count.
     */
    public WordCount(String word)
    {
        // initialise instance variables
        count = 1;
        this.word = new Text(word);
    }

    /**
     * @param wc a WordCount object to compare against.
     * @return When count is less than wc.getCount() this method returns -1. 
     * When count is greater than wc.getCount() this method returns 1. 
     * When count equals wc.getCount() this method returns 0.
     */
    public int compareTo(WordCount wc) {
        // You need to write this method.
        if (wc.getCount() < count){
            return -1;
        }
        else if(wc.getCount() > count){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Increment the count for this word by one.
     */
    public void incrementCount() {
        count++;
    }

    /**
     * @return the count associated with this word.
     */
    public int getCount() {
        return count;
    }

    /**
     * @return the Text object representing this word.
     */
    public Text getText() {
        return word;
    }

}
