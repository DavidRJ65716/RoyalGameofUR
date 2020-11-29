package application.model;
import java.util.Random;

/**
 * Stores and rolls for the dices
 *
 */
public class Dice {
  
	private int DiceArray[] = new int[4];
    Random rand=new Random();
    int[] die=new int[4];
    int total;
    boolean flag;
    
    public void rollDice() {
    	total = 0;
    	
    	for(int i=0;i<4;i++){
      
    		DiceArray[i]=rand.nextInt(2);
    		total += DiceArray[i];
       	}
    }
    
    public void reset() {
    	
    	total = 0;
    	flag = false;
    	
    	for(int i=0;i<4;i++){
    	      
    		DiceArray[i] = 0;
       	}
    }
}
