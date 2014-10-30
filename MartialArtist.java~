import java.util.Random;

public class MartialArtist extends Adventurer{
    Random rand = new Random();

    public void printStats(){
       	System.out.println("\nMartial Artist " + getName() + "\t\t" + getHP() + "HP\t" + getSTR() + "STR\t" + getINT() + "INT\t" + getDEX() + "DEX\t" + getSA() + " qi");
    }

    public MartialArtist(){
	super("Jackie Chan",20,8,10,12);
        setSA(20);
    }

    public MartialArtist(String s){
	super(s,20,10,10,10);
        setSA(20);
    }

    public MartialArtist(String s,int q){
	super(s,20,10,10,10);
        setSA(q);
    }

 public void attack(Adventurer other){
     int dexScore = getDEX();
     int intScore = getINT();
     int strScore = getSTR();
     double attackRate = dexScore + intScore + 50;
     if (rand.nextInt(101) <= attackRate){
	    int hit = rand.nextInt(11) + rand.nextInt(2) * strScore / 10;
	    System.out.println( this.getName() + " attacks " + other.getName()  + " for " + hit + " damage.\n");
	    other.setHP(other.getHP() - hit);
	} else {
	    System.out.println( this.getName() + " attempts to attack " + other.getName() + " and misses...\n");
	}
    }

    public void specialAttack(Adventurer other){
	int dexScore = getDEX();
        int intScore = getINT();
        int strScore = getSTR();
	double attackRate = 50 + dexScore + intScore;
	if (rand.nextInt(101) <= attackRate){
	    int hit = rand.nextInt(11) + rand.nextInt(2) * strScore / 10;
	    System.out.println( this.getName() + " karate chops " + other.getName()  + " for " + hit + " damage.\n");
	    other.setHP(other.getHP() - hit);
	    setSA(getSA() - 5);
	} else {
	    System.out.println( this.getName() + " attempts to karate chop " + other.getName() + " and misses...\n");
	}
    }

}
