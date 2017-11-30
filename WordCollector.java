import java.util.ArrayList;
import java.util.Collections;

/**
 * The WordCollector uses the TextReader to read an input text and measures the frequency of the words
 * contained in the input.
 * 
 * @author (Tony Beaumont) 
 * @version (November 2013)
 */
public class WordCollector
{
    private TextReader read;
    private ArrayList<WordCount> word;
    private DisplayWords display;

    public WordCollector(String input){
        word = new ArrayList<WordCount>();
        read = new TextReader(input);
        display = new DisplayWords();
        
        reader();
        Collections.sort(this.word);
    }

    private void reader(){  
        
       

        String temp = read.readNextWord();
        
        while (temp != null){
            //WordCount wd = new WordCount(read.readNextWord());
            //wd.incrementCount();
            //word.add(wd);
            WordCount word = find(temp);

            if(word != null) {
                word.incrementCount();

            } else {
                WordCount wd = new WordCount(temp);
                this.word.add(wd);

            }
            temp = read.readNextWord();
            
        }
    }

    private WordCount find(String find){
        int i = 0;

        while (i < word.size()){
            WordCount wd = word.get(i);//which word?
            String word = wd.getText().getText();
            if (word.equals(find))
            {
                return wd;
            }
            i++;
        }
        return null;
    }

    public void printReport(){
        int i = 0;
        int count = 0;

        while (i<word.size()){
            count+= word.get(i).getCount();
            i++;
        }
        System.out.println("There were " +count+" words.");
        for (i =0; i < word.size(); i++){
            
            System.out.print(word.get(i).getText().getText()+" appeared "+ word.get(i).getCount());
            if (word.get(i).getCount() > 1){
                System.out.println(" times.");
            }
            else {
                System.out.println(" time.");
            }
        }
    } 

    public void getTextObjectDisplay(){
        for (int i = 0; i < word.size(); i++){
            Text textDisplay = word.get(i).getText();
            textDisplay.changeSize(12 +(word.get(i).getCount()));
            textDisplay.randomizePosition();

            textDisplay.makeVisible();
        }
    }

    public void generateDisplay (int percentToShow ){
        display.showPercentage ( word ,percentToShow );
    }

    public void displayAll(){
        display.showAll(word);
    }

    public void eraseAllWords(){
        display.eraseWords (word);
    }

    public void setMulticolor (){
        display.setMulticolor(true);
    }

    public void setBlackAndWhite (){
        display.setMulticolor(false);
    }
    public void setStrategy(String strategy){
    display.setStrategy(strategy);
    }
}

