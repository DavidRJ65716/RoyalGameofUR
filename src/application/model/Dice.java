package application.model;
import java.util.Random;
public class Dice {
  public int[] DiceArray(){
    Random rand=new Random();
    int[] die=new int[4];
    for(int i=0;i<4;i++){
      die[i]=rand.nextInt(2);
    }
    return die;
  }
  public int total(int die[]){
    int sum=0;
    for(int i=0;i<4;i++){
      sum+=die[i];
    }
    return sum;
  }
}
/*To call this function in another class:
  Dice obj=new Dice();
  int arrDice[]=obj.DiceArray();    //gives you array of dice from class Dice()
  int sum=0;
  sum=obj.total(arrDice);         //returns sum of array of dice
  */
