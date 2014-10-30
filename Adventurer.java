import java.util.Random;

public abstract class Adventurer{
    private String name;
    private int HP;
    private int DEX;
    private int STR;
    private int INT;
    private int SA;
    private int num;
    
    Random rand = new Random();

    public Adventurer(String n,int startHP,int startDEX,int startSTR,int startINT){
	name = n;
	HP = startHP;
	DEX = startDEX;
	STR = startSTR;
	INT = startINT;
    }
    public Adventurer(String n){
	this(n,20,10,10,10);
    }
    
    public Adventurer(){
	this("Lester",20,10,10,10);
    }

    public void setDEX(int d){
	DEX = d;
    }

    public void setSTR(int s){
	STR = s;
    }

    public void setINT(int i){
	INT = i;
    }

    public int getDEX(){
	return DEX;
    }

    public int getSTR(){
	return STR;
    }

    public int getINT(){
	return INT;
    }

    public String toString(){
	return getName();
    }

    public String getName(){
	return name;
    }

    public void setHP(int hp){
	HP = hp;
    }

    public int getHP(){
	return HP;
    }

    public void setSA(int sa){
	SA = sa;
    }

    public int getSA(){
	return SA;
    }

    
    public static void delayGame(int millisec){
	try {
	    Thread.sleep(millisec);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }

    public abstract void attack(Adventurer other);

    public abstract void specialAttack(Adventurer other);

    public abstract void printStats();

}
