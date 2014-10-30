import java.util.Scanner;
import java.util.Random;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;

public class Game{
    



    //
    //methods for setting up
    //

    public static Adventurer makePlayer(int n){
	Scanner userInput = new Scanner(System.in);
	Adventurer player;
       	System.out.println("What is your name?");
	String name = userInput.nextLine();
	player = choose(name);
	player.setNum(n);
	return player;
    }


    public static int chooseStat(int p){
	Scanner userInput = new Scanner(System.in);
	int d;
	try{
	    d = Integer.parseInt(userInput.nextLine());
	} catch (Exception e) {
	    System.out.println("Invalid number. Try again.");
	    d = chooseStat(p);
	}
	if (d <= 0 || d > (p-1)){
	    System.out.println("Your number must be greater than zero and less than " + (p-1) + ". Try again.");
	    d = chooseStat(p);
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

	System.out.println("Choose a class:\nA : Warrior\nB : Wizard\nC : Rogue\nD : Martial Artist");
	String type = userInput.nextLine();
	
	System.out.println("You have " + points + " points.\nChoose how many you would like to allocate towards your dexterity.\nPlease type a non-negative integer less than " + points + ".");
	Dex = chooseStat(points);
	points -= Dex;
	System.out.println("You have " + points + " points remaining.\nChoose how many you would like to allocate towards you strength.\nPlease type a non-negative integer less than " + points + ".");
	Str = chooseStat(points);
	points -= Str;
	Int = points;

	if (type.equalsIgnoreCase("A")){
	    p = new Warrior(name);
	} else if (type.equalsIgnoreCase("B")){
	    p = new Wizard(name);
	} else if (type.equalsIgnoreCase("C")){
	    p = new Rogue(name);
	} else if (type.equalsIgnoreCase("D")){
	    p = new MartialArtist(name);
	} else {
	    System.out.println("Please type either A, B, C, or D.");
	    p = choose(name);
	}     
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

	System.out.println("How many players? Choose a number from 1 to 4.");

	int numPlayers = Integer.parseInt(userInput.nextLine());

	if (numPlayers > 4 || numPlayers < 1){
	    System.out.println("Please choose a number of players, from 1 to 4.");
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


    public static Adventurer[] removePlayer(Adventurer[] players, int who, int numPlayers){
	ArrayList<Adventurer> playerList = new ArrayList<Adventurer>();

	for (int i= 0; i < numPlayers; i++) {
	    if (i == who) {
		playerList.add(players[i]);
	    }
	}

	Adventurer[] ans = new Adventurer[numPlayers];
        ans = playerList.toArray(ans);
	
	return ans;
    }


    public static Adventurer[] checkIfPlayerDies(Adventurer[] players,int numPlayers){
	for (int i = 0; i < numPlayers; i++){ 
	    if (checkHealth(players[i])){
		System.out.println(players[i].getName() + "has died.");
		players = removePlayer(players,i,numPlayers);
	    }
    }
	return players;
    }


    public static boolean checkHealth(Adventurer who){
	boolean ans = false;
	if (who.getHP() <= 0){
	    who.setHP(0);
	    ans =  true;
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
	    System.out.println("\nChoose an action:\nA : attack\nS : special attack");
	    String type = userInput.nextLine();	
	    if (type.equalsIgnoreCase("A")){
		action = 0;
	    } else if (type.equalsIgnoreCase("S")){
		action = 1;
	    } else {
		System.out.println("Please type either A or S.");
		action = chooseAction(p);
	    }
	}  else {
	    System.out.println("\nYou are no longer powerful enough for a special action!\nChoose an action:\nA : attack");
	    String type = userInput.nextLine();
	
	    if (type.equalsIgnoreCase("A")){
		action = 0;
	    } else {
		System.out.println("Please type A.");
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
		System.out.println();
		System.out.println();
		break;
	    default: players[0].printStats();        
		opponent.printStats();
		System.out.print("^");
		System.out.println();
		System.out.println();
		break;
	    }
	    break;
	case 2:
	    switch (whoTurn){
	    case 0: players[0].printStats();
		System.out.print("^");
		players[1].printStats();
		opponent.printStats();
		System.out.println();
		System.out.println();
		break;
	    case 1: players[0].printStats();	        
		players[1].printStats();
		System.out.print("^");
		opponent.printStats();
		System.out.println();
		System.out.println();
		break;
	    default: players[0].printStats();
		players[1].printStats();	        
		opponent.printStats();
		System.out.print("^");
		System.out.println();
		System.out.println();
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
		System.out.println();
		System.out.println();
		break;
	    case 1: players[0].printStats();       
		players[1].printStats();
		System.out.print("^");
		players[2].printStats();
		opponent.printStats();
		System.out.println();
		System.out.println();
		break;
	    case 2: players[0].printStats();	   
		players[1].printStats();	        
		players[2].printStats();
		System.out.print("^");
		opponent.printStats();
		System.out.println();
		System.out.println();
		break;

	    default: players[0].printStats();
		players[1].printStats();
		players[2].printStats();	        
		opponent.printStats();
		System.out.print("^");
		System.out.println();
		System.out.println();
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
		System.out.println();
		System.out.println();
		break;
	    case 1: players[0].printStats();        
		players[1].printStats();
		System.out.print("^");
		players[2].printStats();
		players[3].printStats();
		opponent.printStats();
		System.out.println();
		System.out.println();
		break;
	    case 2: players[0].printStats();	   
		players[1].printStats();       
		players[2].printStats();
		System.out.print("^");
		players[3].printStats();
		opponent.printStats();
		System.out.println();
		System.out.println();
		break;
	    case 3: players[0].printStats();	   
		players[1].printStats();
		players[2].printStats();     
		players[3].printStats();
		System.out.print("^");
		opponent.printStats();
		System.out.println();
		System.out.println();
		break;
	    default: players[0].printStats();
		players[1].printStats();
		players[2].printStats();
		players[3].printStats();        
		opponent.printStats();
		System.out.print("^");
		System.out.println();
		System.out.println();
		break;
	    }
	    break;

	}
		    
    }


    public static void doSomething(Adventurer pGoingNow, Adventurer pBeingAttacked, int whatToDo){

	switch (whatToDo) {
	
	case 0: pGoingNow.attack(pBeingAttacked);
	    break;
	case 1: pGoingNow.specialAttack(pBeingAttacked);
	    break;
	}
	
    }


    public static boolean go(Adventurer[] players, int whoTurn, int numPlayers, Adventurer opponent) {
	boolean over = false;
	Random r = new Random();
	int healthSum = hs(players);
	
	for (int i = 0; i < numPlayers; i++){
	    boolean x = checkHealth(players[i]);
	}

	boolean y = checkHealth(opponent);
 
	if (opponent.getHP() > 0 && healthSum > 0) {

	    if (whoTurn == numPlayers){
		if (checkSA(opponent)){
		    doSomething(opponent, players[r.nextInt(numPlayers)], r.nextInt(1)); 
		} else {
		    doSomething(opponent, players[r.nextInt(numPlayers)], r.nextInt(2));
		}
	    } else {	    
		doSomething(players[whoTurn], opponent, chooseAction(players[whoTurn]));		
	    }

	} else {
	
	    System.out.println("Game over!");

	    if (healthSum == 0) {
		System.out.println("All players have died.");
		System.out.println(opponent.getName() + "has won.");
	    } else if (opponent.getHP() == 0) {
		System.out.println(opponent.getName() + " has died.");
		System.out.println("Players win!");
	    }
	    over = true;
	}
	printStatsForAll(players, numPlayers, opponent, whoTurn);
	checkIfPlayerDies(players,numPlayers);
	return over;

    }


    public static void testGo(Adventurer[] players, Adventurer opponent, int numPlayers){

	Random r = new Random();
	
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
	    playAgain = playAgain("");
	}

	if (playAgain == true){
	    playGame(numPlayers,players);
	}

    }

    public static String askAgain(){
	Scanner userInput = new Scanner(System.in);
	String ans = "";
	System.out.println("Would you like to play again?\nY : YES\nN : NO");
	ans = userInput.nextLine();
	return ans;
    }


    public static boolean playAgain(String ans){ 
	Scanner userInput = new Scanner(System.in);
	boolean again = false;

	System.out.println("Would you like to play again?\nY : YES\nN : NO");
	ans = userInput.nextLine();
	
	if (ans.equalsIgnoreCase("Y")) {
	    again = true;
	} else if (ans.equalsIgnoreCase("N")) {
	    again = false;
	} else {
	    System.out.println("Please type either Y or N.");
	    ans = askAgain();
	    again = playAgain(ans);
	}
	return again;

    }


    public static void playGame(int numPlayers,Adventurer[] players){

	Adventurer opponent = setOpp();

	printStatsForAll(players,numPlayers,opponent,numPlayers);
 
	testGo(players,opponent,numPlayers);

    }





    //
    // MAIN METHOD
    //

    public static void main(String[]args){
	Random r = new Random();
	Adventurer p1 = new Warrior();
	Adventurer p2 = new Warrior();
	Adventurer p3 = new Warrior();
	Adventurer p4 = new Warrior();
	Adventurer opponent;

	int numPlayers = chooseNumPlay();

	for (int n = numPlayers; n > 0; n--){
	    switch (n){
	    case 1: p1 = makePlayer(n);
		break;
	    case 2: p2 = makePlayer(n);
		break;
	    case 3: p3 = makePlayer(n);
		break;
	    case 4: p4 = makePlayer(n);
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
 
	testGo(players,opponent,numPlayers);

    }

  
}

