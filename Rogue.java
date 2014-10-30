import java.util.Random;

public class Rogue extends Adventurer{
    Random rand = new Random();

    public Rogue(){
	super("Rory",20,8,10,12);
        setSA(10);
    }

    public Rogue(String s){
	super(s,20,12,8,10);
        setSA(10);
    }

    public Rogue(String s,int stam){
	super(s,20,12,8,10);
        setSA(stam);
    }

    public void specialAttack(Adventurer other){
	int dexScore = getDEX();
        int intScore = getINT();
        int strScore = getSTR();
	double attackRate = 50 + dexScore + intScore;
	if (rand.nextInt(101) <= attackRate){
	    int hit = rand.nextInt(11) + rand.nextInt(2) * strScore / 10;
	    System.out.println( this.getName() + " violently attacks " + other.getName()  + " for " + hit + " damage.\n");
	    other.setHP(other.getHP() - hit);
	    setSA(getSA() - 5);
	} else {
	    System.out.println( this.getName() + " attempts to violently attack " + other.getName() + " and misses...\n");
	}
    }

    public void printStats(){
	System.out.println("\nRogue " +  getName() + "\t\t\t" +  getHP() + "HP\t" + getSTR() + "STR\t" + getINT() + "INT\t" + getDEX() + "DEX\t" +  getSA() + " stamina");
    }


    public void attack(Adventurer other){
        int dexScore = getDEX();
        int intScore = getINT();
        int strScore = getSTR();
	double attackRate = 50 + dexScore + intScore;
	if (rand.nextInt(101) <= attackRate){
	    int hit = rand.nextInt(11) + rand.nextInt(2) * strScore / 10;
	    System.out.println( this.getName() + " attacks " + other.getName()  + " for " + hit + " damage.\n");
	    other.setHP(other.getHP() - hit);
	} else {
	    System.out.println( this.getName() + " attempts to attack " + other.getName() + " and misses...\n");
	}
    }

}
