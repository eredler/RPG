public class Driver {

    public static void main(String[]args){

	Adventurer p = new Adventurer();
	Adventurer p1 = new Adventurer("Addy");
	Warrior p2 = new Warrior();
	Wizard p3 = new Wizard("Merlin");

	System.out.println(p.getName());
     	System.out.println(p1.getName());
	System.out.println(p2.getName());
	System.out.println(p3.getName());
	System.out.println(p1.getHP());
	System.out.println(p2.getHP());
	System.out.println(p3.getMana());

    }

}
