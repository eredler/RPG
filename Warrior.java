import java.util.Random;

public class Warrior extends Adventurer{

    Random rand = new Random();

    public void printStats(){
       	System.out.println("\nWarrior " + getName() + "\t\t\t" + getHP() + "HP\t" + getSTR() + "STR\t" + getINT() + "INT\t" + getDEX() + "DEX\t" + getSA() + " rage");
    }


    public Warrior(){
	super("Ted",20,10,12,8);
	setSA(15);
    }

    public Warrior(String s){
	super(s,20,10,12,8);
        setSA(15);
    }

    public Warrior(String s,int r){
	super(s,20,10,12,8);
	setSA(r);
    }


    public void attack(Adventurer other){
	int dexScore = getDEX();
	int intScore = getINT();
        int strScore = getSTR();
	double attackRate = dexScore + intScore + 50;
	if (rand.nextInt(101) <= attackRate){
	    int hit = rand.nextInt(11) + rand.nextInt(2) * strScore / 10;
	    System.out.println("");
	    delayGame(500);
	    System.out.println( this.getName() + " attacks " + other.getName()  + " for " + hit + " damage.\n");
	    System.out.println("");
	    other.setHP(other.getHP() - hit);
	} else {
	    System.out.println("");
	    delayGame(500);
	    System.out.println( this.getName() + " attempts to attack " + other.getName() + " and misses...\n");
	    System.out.println("");
	}
    }

    public void specialAttack(Adventurer other){
	int dexScore = getDEX();
        int intScore = getINT();
        int strScore = getSTR();
	double attackRate = 50 + dexScore + intScore;
	if (rand.nextInt(101) <= attackRate){
	    int hit = rand.nextInt(11) + rand.nextInt(2) * strScore / 10;
	    System.out.println("");
	    delayGame(500);
	    System.out.println( this.getName() + " slices " + other.getName()  + " for " + hit + " damage.\n");
	    System.out.println("");
	    other.setHP(other.getHP() - hit);
	    setSA(getSA() - 5);
	} else {
	    System.out.println("");
	    delayGame(500);
	    System.out.println( this.getName() + " attempts to slice " + other.getName() + " and misses...\n");
	    System.out.println("");
	}
   
    }



}
