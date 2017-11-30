import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Write a description of class DisplayWords here.
 * 
 * @author (Tony Beaumont) 
 * @version (November 2013)
 */
public class DisplayWords
{
    private int canvasWidth;
    private int canvasHeight;
    private int cx; // canvas center x
    private int cy; // canvas center y
    private int percentLoop;
    private int x;
    private int y;
    private int size,textHeightDown, textWidthDown;

    private int exchange;
    double sizeDouble;
    private boolean isMultiColor;
    private final String[] colors = { "red", "yellow", "blue", 
            "green", "magenta", "pink", "orange", "cyan", "black"};
    private String strategy;
    private String[] legalStrategies = {"random", "insideOut", "outsideIn", "inLine" , "alternateInLine"};

    /**
     * Constructor for objects of class DisplayWords
     */
    public DisplayWords()
    {
        // initialise instance variables
        canvasWidth = Canvas.getCanvas().getWidth();
        canvasHeight = Canvas.getCanvas().getHeight();
        cx = canvasWidth/2;
        cy = canvasHeight/2;
        isMultiColor = true;
        strategy = "random";

    }

    public void showAll(ArrayList <WordCount> words){

        display(words , words.size());
    }

    public void showPercentage (ArrayList <WordCount> words , int pc){
        int percent = (words.size()*pc)/100;

        display(words , percent);
        while (percentLoop >= percent && percentLoop < words.size()){
            Text text = words.get(percentLoop).getText();
            text.makeInvisible();
            percentLoop++;
        }

    }

    private void display(ArrayList <WordCount> words , int whileLoop){
        x = 0;
        y = 0;
        textHeightDown =768;
        textWidthDown = 1024;
        size = 200;

        exchange =0;
        sizeDouble =0;
        int i = 0;
        int currentColor = 0;
        int currentColorBottom = 0;
        while ( i < whileLoop){
            Text text = words.get(i).getText();

            if (strategy.equalsIgnoreCase(legalStrategies[0]) || strategy.equalsIgnoreCase(legalStrategies[3])){
                if (isMultiColor == true){

                    text.changeColor(colors[currentColor]);
                    currentColor++;
                    if (currentColor==9){
                        currentColor = 0;
                    }

                }
                else{
                    text.changeColor("black");
                }
            }else if  (strategy.equalsIgnoreCase(legalStrategies[4])){
                if (exchange == 0){
                    if (isMultiColor == true){

                        text.changeColor(colors[currentColor]);
                        currentColor++;
                        if (currentColor==9){
                            currentColor = 0;
                        }

                    }
                    else{
                        text.changeColor("black");
                    }
                }

                else {
                    if (isMultiColor == true){

                        text.changeColor(colors[currentColorBottom]);
                        currentColorBottom++;
                        if (currentColorBottom==9){
                            currentColorBottom = 0;
                        }

                    }
                    else{
                        text.changeColor("black");
                    }
                }

            }

            displayWord(words.get(i).getText(), words.get(i).getCount(), i, words.get(0).getCount());
            text.makeVisible();
            i++;
            percentLoop = i;
        }
    }

    public void eraseWords (ArrayList < WordCount > words){
        int i=0;
        while (i < words.size()){
            Text text = words.get(i).getText();
            text.makeInvisible();
            i++;
        }
    }

    private void displayWord (Text t, int count,int posInList, int mostFrequent){
        
        if (strategy.equalsIgnoreCase(legalStrategies[0])){
            size( t, count, posInList, mostFrequent);
            t.randomizePosition();
        }
        else if(strategy.equalsIgnoreCase(legalStrategies[3])){
            size( t, count, posInList, mostFrequent);
            if (posInList ==0 ){

                x +=(t.getTextHeight()/2);
            }
            if ((y +t.getTextWidth())>1024)
            {
                x +=(t.getTextHeight()/2);
                y = 0;
            }    
            t.setPosition(y, x);
            y += t.getTextWidth();

        }else if(strategy.equalsIgnoreCase(legalStrategies[4])){

            if (exchange ==0){
                size = Math.abs(size);
                size( t, count, posInList, mostFrequent);
                if (posInList ==0 ){
                    
                    x +=(t.getTextHeight()/2);
                }

                if ((y +t.getTextWidth())>1024)
                {
                    x +=(t.getTextHeight()/2);
                    y = 0;
                }
                t.setPosition(y, x);
                y += t.getTextWidth();
                exchange = 1;
            }
            else if (exchange ==1){
                size( t, count, posInList, mostFrequent);
                if(posInList ==1) {
                    textHeightDown -=(t.getTextHeight()/2);
                }

                if ((textWidthDown -t.getTextWidth())<0)
                {
                    textHeightDown -=(t.getTextHeight()/2);
                    textWidthDown = 1024;
                }
                
                t.setPosition(textWidthDown, textHeightDown);
                textWidthDown -= t.getTextWidth();
                size = -size;
                t.changeSize(size);
                exchange = 0;
            }
        }
        
        
        t.makeVisible();
    }
    
    private void size(Text t, int count,int posInList, int mostFrequent){
    
    if(size <=200 && size>100){ 
            size = size-12;
        }

        else if(size <= 100 && size > 50){ 
            sizeDouble = sizeDouble + 0.5;
            if(sizeDouble >=1){
                size = size-4;
                sizeDouble =0;
            }
        }
        else if(size <= 50&&size>20){ 
            sizeDouble = sizeDouble + 0.5;

            if(sizeDouble >=2){
                size = size-2;
                sizeDouble =0;
            }
        }
        else if(size <= 20 &&size>10){

            sizeDouble = sizeDouble + 0.5;

            if(sizeDouble >=2){
                size = size-1;
                sizeDouble =0;
            }
        }
        else {
            sizeDouble = sizeDouble + 0.5;

            if(sizeDouble >=4){
                size = size-2;
                sizeDouble =0;
            }

        }

        if(size > 200){
            size =200;
        }
        else if (size <=5){
            size = 5;
        }

        if (count == mostFrequent){
            t.changeSize(200);
        }

        else{
            t.changeSize(size);
        }
    }

    public void setMulticolor (boolean mc){
        isMultiColor = mc;
    }

    public void setStrategy (String strategy){
        this.strategy = strategy;
    }

}
