import java.util.Scanner;
import java.util.Random;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;

public class Game{
    



    //
    //methods for setting up
    //

    public static int levelChoose(){
	Scanner userInput = new Scanner(System.in);

	int ans = 1;
	System.out.println();
	delayGame(500);
	System.out.println("What level opponent would you like to battle? Type a number from 1 to 50.");
	System.out.println();
	try{
	    ans = Integer.parseInt(userInput.nextLine());
	} catch (Exception e) {
	    System.out.println();
	    delayGame(500);
	    System.out.println("Invalid number. Try again.");
	    System.out.println();
	    delayGame(500);
	    ans = levelChoose();
	}
	if (ans <= 0 || ans > 50){
	    System.out.println();
	    delayGame(500);
	    System.out.println("Your number must be greater than zero and less than or equal to 50. Try again.");
	    System.out.println();
	    ans = levelChoose();
	}
	return ans;
    }

    public static Adventurer makePlayer(int n){
	Scanner userInput = new Scanner(System.in);
	Adventurer player;
	System.out.println();
	delayGame(500);
       	System.out.println("Player " + n + ", what is your name?");
	System.out.println();
	String name = userInput.nextLine();
	player = choose(name);
	return player;
    }


    public static int chooseStat(int p, boolean second){
	Scanner userInput = new Scanner(System.in);
	int d;
	try{
	    d = Integer.parseInt(userInput.nextLine());
	} catch (Exception e) {
	    System.out.println("");
	    delayGame(500);
	    System.out.println("Invalid number. Try again.");
	    System.out.println("");
	    d = chooseStat(p,false);
	}
	if (second == false && (d <= 0 || d > (p-1))){
	    System.out.println("");
	    delayGame(500);
	    System.out.println("Your number must be greater than zero and less than " + p + ". Try again.");
	    System.out.println("");	    
	    d = chooseStat(p,false);
	} else if (second == true && (d <= 0 || d > (p-2))) {
	    System.out.println("");
	    delayGame(500);
	    System.out.println("Your number must be greater than zero and less than " + (p-1) + ". Try again.");
	    System.out.println("");	    
	    d = chooseStat(p,true);
	}
	return d;
    }


    public static Adventurer choose(String name){
	Adventurer p;
	int Dex;
	int Str;
	int Int;
	int points = 30;

	Scanner userInput = new Scanner(System.in);
	System.out.println("");
	delayGame(500);
	System.out.println("Choose a class:\nA : Warrior\nB : Wizard\nC : Rogue\nD : Martial Artist");
	System.out.println("");
	String type = userInput.nextLine();
	if (type.equalsIgnoreCase("A")){
	    p = new Warrior(name);
	} else if (type.equalsIgnoreCase("B")){
	    p = new Wizard(name);
	} else if (type.equalsIgnoreCase("C")){
	    p = new Rogue(name);
	} else if (type.equalsIgnoreCase("D")){
	    p = new MartialArtist(name);
	} else {
	    System.out.println("");
	    delayGame(500);
	    System.out.println("Please type either A, B, C, or D.");
	    System.out.println("");
	    p = choose(name);
	}     
	System.out.println("");
	delayGame(500);
	System.out.println("You have " + points + " points.\n\nChoose how many you would like to allocate towards your dexterity.\n\nPlease type a non-negative integer less than " + (points-1) + ".");
	System.out.println("");
	Dex = chooseStat(points,true);
	points -= Dex;
	System.out.println("");
	delayGame(500);
	System.out.println("You have " + points + " points remaining.\n\nChoose how many you would like to allocate towards your strength.\n\nPlease type a non-negative integer less than " + points + ".");
	System.out.println("");
	Str = chooseStat(points,false);
	points -= Str;
	Int = points;

	
	p.setDEX(Dex);
	p.setSTR(Str);
	p.setINT(Int);
        return p;
    }

   
    public static Adventurer setOpp(){
	Random r = new Random();
	int opp = r.nextInt(4);
	Adventurer o;
	if (opp == 0) {
	    o = new MartialArtist("Spongebob");
	} else if (opp == 1) {
	    o = new Warrior("Wario");
	} else if (opp == 2) {
	    o = new Wizard("Merlin");
	} else {
	    o = new Rogue("Jack Skellington");	   
	}
	int level = levelChoose();
	o.setHP(level*10);
	return o;
    }


    public static int randP(int n){
	Random r = new Random();
        int x = r.nextInt(n);
	int ans = 0;
	switch (n){
	case 0: ans = 1;
	    break;
	case 1: ans = 2;
	    break;
	case 2: ans = 3;
	    break;
	case 3: ans = 4;
	    break;
	}
	return ans;
    }


    public static int chooseNumPlay(){

	Scanner userInput = new Scanner(System.in);
	System.out.println("");
	delayGame(500);
	System.out.println("How many players? Choose a number from 1 to 4.");
	System.out.println("");
	int numPlayers = Integer.parseInt(userInput.nextLine());

	if (numPlayers > 4 || numPlayers < 1){
	    System.out.println("");
	    delayGame(500);
	    System.out.println("Please choose a number of players, from 1 to 4.");
	    System.out.println("");
	    numPlayers = chooseNumPlay();
	}

	return numPlayers;

    }




    
    //
    //methods to check things
    //

    public static boolean checkSA(Adventurer p){
	if (p.getSA() >= 5){
	    return true;
	} else {
	    return false;
	}
    }


    public static int hs(Adventurer[] players){
	int healthSum = 0;
	for (int x = 0; x < players.length; x++){
	    healthSum += players[x].getHP();
	}
	return healthSum;
    }


    public static void checkIfPlayerDies(Adventurer[] players,int numPlayers){

        for (int i = 0; i < numPlayers; i++){
	    if (checkHealth(players[i])){
		System.out.println("");
		delayGame(500);
		System.out.println(players[i] + " is dead.");
		delayGame(500);
		System.out.println("");
	    }
	}
    }


    public static boolean checkHealth(Adventurer who){
	boolean ans = false;
	if (who.getHP() <= 0){
	    who.setHP(0);
	    ans = true;
	}
	return ans;
    }





    //
    //methods to do things
    //

    public static int chooseAction(Adventurer p){
        int action;
	Scanner userInput = new Scanner(System.in);
	if (checkSA(p)) {
	    System.out.println("");
	    System.out.println("\nChoose an action:\nA : attack\nS : special attack");
	    System.out.println("");
	    String type = userInput.nextLine();	
	    if (type.equalsIgnoreCase("A")){
		action = 0;
	    } else if (type.equalsIgnoreCase("S")){
		action = 1;
	    } else {
		delayGame(500);
		System.out.println("");
		System.out.println("Please type either A or S.");
		System.out.println("");
		System.out.println("");
		action = chooseAction(p);
	    }
	}  else {
	    delayGame(500);
	    System.out.println("");
	    System.out.println("\nYou are no longer powerful enough for a special action!\nChoose an action:\nA : attack");
	    System.out.println("");
	    String type = userInput.nextLine();
	
	    if (type.equalsIgnoreCase("A")){
		action = 0;
	    } else {
		delayGame(500);
		System.out.println("");
		System.out.println("Please type A.");
		System.out.println("");
		System.out.println("");
		action = chooseAction(p);
	    }
	}
        return action;
    }
    

    public static void printStatsForAll(Adventurer[] players, int numPlayers, Adventurer opponent, int whoTurn){

	switch (numPlayers){

	case 1: 
	    switch (whoTurn){
	    case 0: players[0].printStats();
		System.out.print("^");
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    default: players[0].printStats();        
		opponent.printStats();
		System.out.print("^");
		System.out.println("");
		System.out.println("");
		break;
	    }
	    break;
	case 2:
	    switch (whoTurn){
	    case 0: players[0].printStats();
		System.out.print("^");
		players[1].printStats();
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    case 1: players[0].printStats();	        
		players[1].printStats();
		System.out.print("^");
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    default: players[0].printStats();
		players[1].printStats();	        
		opponent.printStats();
		System.out.print("^");
		System.out.println("");
		System.out.println("");
		break;
	    }
	    break;
	case 3:
	    switch (whoTurn){
	    case 0: players[0].printStats();
		System.out.print("^");
		players[1].printStats();
		players[2].printStats();
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    case 1: players[0].printStats();       
		players[1].printStats();
		System.out.print("^");
		players[2].printStats();
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    case 2: players[0].printStats();	   
		players[1].printStats();	        
		players[2].printStats();
		System.out.print("^");
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;

	    default: players[0].printStats();
		players[1].printStats();
		players[2].printStats();	        
		opponent.printStats();
		System.out.print("^");
		System.out.println("");
		System.out.println("");
		break;
	    }
	    break;
	case 4:
	    switch (whoTurn){
	    case 0: players[0].printStats();
		System.out.print("^");
		players[1].printStats();
		players[2].printStats();
		players[3].printStats();
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    case 1: players[0].printStats();        
		players[1].printStats();
		System.out.print("^");
		players[2].printStats();
		players[3].printStats();
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    case 2: players[0].printStats();	   
		players[1].printStats();       
		players[2].printStats();
		System.out.print("^");
		players[3].printStats();
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    case 3: players[0].printStats();	   
		players[1].printStats();
		players[2].printStats();     
		players[3].printStats();
		System.out.print("^");
		opponent.printStats();
		System.out.println("");
		System.out.println("");
		break;
	    default: players[0].printStats();
		players[1].printStats();
		players[2].printStats();
		players[3].printStats();        
		opponent.printStats();
		System.out.print("^");
		System.out.println("");
		System.out.println("");
		break;
	    }
	    break;

	}
		    
    }


    public static void doSomething(Adventurer pGoingNow, Adventurer pBeingAttacked, int whatToDo, Adventurer[] players, int numPlayers){
	for (int i = 0; i < numPlayers; i++){
	    checkHealth(players[i]);
	}

	switch (whatToDo) {
	
	case 0: pGoingNow.attack(pBeingAttacked);
	    break;
	case 1: pGoingNow.specialAttack(pBeingAttacked);
	    break;
	}
	
    }

    
    public static void delayGame(int millisec){
	try {
	    Thread.sleep(millisec);                
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }


    public static boolean go(Adventurer[] players, int whoTurn, int numPlayers, Adventurer opponent) {
	boolean over = false;
	Random r = new Random();
	int healthSum = hs(players);
	Adventurer beingAttacked = players[r.nextInt(numPlayers)];


	for (int i = 0; i < numPlayers; i++){
	    boolean x = checkHealth(players[i]);
	}

 
	if (opponent.getHP() > 0 && healthSum > 0) {

	    if (whoTurn == numPlayers){
		if (checkSA(opponent)){		    
		    if (checkHealth(beingAttacked) == false){
			delayGame(500);
			printStatsForAll(players, numPlayers, opponent, whoTurn);
			delayGame(750);
			doSomething(opponent, beingAttacked, r.nextInt(1) ,players,numPlayers); 
			delayGame(1000);
		    } else {
			go(players,whoTurn,numPlayers,opponent);
		    }		   
		} else if (checkHealth(beingAttacked) == false) {
		    delayGame(500);
		    printStatsForAll(players, numPlayers, opponent, whoTurn);
		    delayGame(750);
		    doSomething(opponent, beingAttacked, r.nextInt(2),players,numPlayers);
		    delayGame(1000);
		} else {
		    go(players,whoTurn,numPlayers,opponent);
		}
	    } else if (checkHealth(players[whoTurn]) == false){
		checkIfPlayerDies(players,numPlayers);
		delayGame(500);
		printStatsForAll(players, numPlayers, opponent, whoTurn);
		delayGame(500);
		doSomething(players[whoTurn], opponent, chooseAction(players[whoTurn]),players,numPlayers);
		delayGame(500);
	    }

	} else {
	    delayGame(500);
	    System.out.println("");
	    delayGame(500);
	    System.out.println("Game over!");
	    delayGame(500);
	    printStatsForAll(players, numPlayers, opponent, whoTurn);
	    System.out.println("");
	    delayGame(500);
	    System.out.println("");
	    if (healthSum == 0) {
		System.out.println("All players have died.");
		System.out.println("");
		delayGame(500);
		System.out.println(opponent.getName() + " has won.");
		System.out.println("");
		delayGame(500);
	    } else if (opponent.getHP() == 0) {
		System.out.println(opponent.getName() + " has died.");
		System.out.println("");
		delayGame(500);
		System.out.println("Players win!");
		delayGame(500);
		System.out.println("");
	    }
	    over = true;
	}
	return over;

    }


    public static void testGo(Adventurer[] players, Adventurer opponent, int numPlayers){
	boolean over = false;
	boolean playAgain = false;

	Adventurer[] storePlayers = new Adventurer[numPlayers];
	
	for (int i = 0; i < numPlayers; i++){
	    storePlayers[i] = players[i];
	}

	while (over == false){
	    for (int i = 0; i < numPlayers+1; i++){
		over = go(players, i, numPlayers, opponent);
	    }
	}

	if (over == true){
	    for (int i = 0; i < numPlayers; i++){
		players[i].setHP(20);
		players[i].setSA(10);
	    }

	    playAgain = playAgain("");
	}

	if (playAgain == true){
	    playGame(numPlayers,players);
	}

    }

    public static String askAgain(){
	Scanner userInput = new Scanner(System.in);
	String a = "";
	System.out.println("");
	delayGame(250);
	System.out.println("");
	delayGame(250);
	System.out.println("Would you like to play again?\nY : YES\nN : NO");
	a = userInput.nextLine();
	return a;
    }


    public static boolean playAgain(String ans){
	boolean again = false;
	String x = askAgain();	
	if (x.equalsIgnoreCase("Y")) {
	    again = true;
	} else if (x.equalsIgnoreCase("N")) {
	    again = false;
	} else {
	    System.out.println("");
	    System.out.println("Please type either Y or N.");
	    System.out.println("");
	    again = playAgain(x);
	}
	return again;

    }


    public static void playGame(int numPlayers,Adventurer[] players){

	Adventurer opponent = setOpp();

	printStatsForAll(players,numPlayers,opponent,numPlayers);
	delayGame(1000);
	testGo(players,opponent,numPlayers);

    }






    //
    // MAIN METHOD
    //

    public static void main(String[]args){
	Adventurer p1 = new Warrior();
	Adventurer p2 = new Warrior();
	Adventurer p3 = new Warrior();
	Adventurer p4 = new Warrior();
	Adventurer opponent;

	int numPlayers = chooseNumPlay();

	for (int n = numPlayers; n > 0; n--){
	    switch (n){
	    case 1: p1 = makePlayer(1);
		delayGame(500);
		break;
	    case 2: p2 = makePlayer(2);
		delayGame(500);
		break;
	    case 3: p3 = makePlayer(3);
		delayGame(500);
		break;
	    case 4: p4 = makePlayer(4);
		delayGame(500);
		break;
	    }
	}

	Adventurer[] players = new Adventurer[numPlayers];

	switch (numPlayers){
	case 1: players[0] = p1;
	    break;
	case 2: players[0] = p1;
	    players[1] = p2;
	    break;
	case 3: players[0] = p1;
	    players[1] = p2;
	    players[2] = p3;
	    break;
	case 4: players[0] = p1;
	    players[1] = p2;
	    players[2] = p3;
	    players[3] = p4;
	    break;
	}

	opponent = setOpp();
	delayGame(1000);
	testGo(players,opponent,numPlayers);

    }

  
}

