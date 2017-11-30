import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
/**
 * A class to read words from an input text file.  Common English "stop" words 
 * such as "the", "to", "a", etc, are ignored.  A method is provided to read 
 * the next word from the input.
 * 
 * @author Dr A J Beaumont 
 * @version November 2011
 */
public class TextReader
{
    // instance variables - replace the example below with your own
    private String file;
    private Scanner s;
    private Set<String> stopWords;

    /**
     * Constructor for objects of class TextReader.
     * @param file the name of the input file containing the text to be read.
     */
    public TextReader(String file)
    {
        stopWords = new HashSet<String>();
        try {
            initialiseStopWords();
            s = new Scanner(new BufferedReader(new FileReader(file)));
            s.useDelimiter("[^a-zA-Z0-9]+");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    private void initialiseStopWords() throws FileNotFoundException{
        Scanner s = new Scanner(new BufferedReader(new FileReader("StopWords.txt")));
        s.useDelimiter(",");
        while (s.hasNext()) {
            stopWords.add(s.next());
        }
    }

    /**
     * Read the next word from the input.  Common English "stop" words are ignored.
     * @return the next word from the input.  When there is no more input, this
     * method will return null.
     */
    public String readNextWord() {
        while (s.hasNext()) {
            String word = s.next().toLowerCase();
            if (!stopWords.contains(word) && word.length() > 1) {
                return word;
            }
        }
        return null;
    }

}
