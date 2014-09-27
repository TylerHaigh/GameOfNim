package nim;

import java.util.Scanner;

public class NimInterface {
	
	private int initialMatchsticks = -1;
	private Scanner console;
	
	public NimInterface() {
		console = new Scanner(System.in);
	}
	
	public void run() {
		
		System.out.println("Hello and welcome to NIM!");
		displayRules();
		updateMatchsticks();
		displayMainMenu();
	}
	
	private void displayRules() {
		System.out.println("Theses are the rules for the NIM game:\n");
		
		System.out.println("There are two players, A and B.\n");
		
		System.out.println("There is a pile of matchstiks on the table.\n");
		
		System.out.println("Player A starts, taking some matchsticks; he/she\n" +
				"must take at least one on the table.\n");
		
		System.out.println("Player B then takes some matchsticks; he/she\n" +
				"must take at lease one, but cannot take more than double the\n" +
				"number of matchsticks player A took; obviously player B cannot\n" + 
				"take more than the number of the matchsticks left on the table.\n");
		
		System.out.println("In general, a player can take any number of\n" +
				"matchsticks between 1 and double the numer that the previous\n" +
				"player took, but obviously no more than the number of\n" +
				"matchsticks left on the table.\n");
		
		System.out.println("Player to take the last matchstick wins.");
	}
	
	private void updateMatchsticks() {
		
		String message = "Please enter the " +
				((initialMatchsticks == -1) ? "initial " : "") + 
				"number of matchsticks on the table: ";
		
		System.out.print(message);
		int numSticks = console.nextInt();
		while (numSticks < 2) {
			System.out.println("Error: NIM requires at least 2 initial sticks to play");
			System.out.print(message);
			numSticks = console.nextInt();
		}
		
		this.initialMatchsticks = numSticks;
		System.out.println("There are " + initialMatchsticks + " matchsticks on the table");
	}
	
	private void displayMainMenu() {
		int userOption = -1;
		while (userOption != 6) {
			
			displayMainMenuOptions();
			userOption = console.nextInt();
			
			switch (userOption) {
				case 1 : constructNimGraph(); break;
				case 2 : labelNimGraph(); break;
				case 3 : playNim(); break;
				case 4 : updateMatchsticks(); break;
				case 5 : displayRules(); break;
				case 6 : /*Do Nothing*/ break;
				default : System.out.println("Error: Input is out of bounds");
			}
		}
	}
	
	private void displayMainMenuOptions() {
		System.out.println("Please select a number from the following menu:");
		System.out.println("\t[1] Construct the NIM graph");
		System.out.println("\t[2] Label the graph nodes winnong/losing");
		System.out.println("\t[3] Play NIM");
		System.out.println("\t[4] Change the initial number of matchsticks on the table");
		System.out.println("\t[5] Display Help");
		System.out.println("\t[6] Exit");
	}
	
	private void constructNimGraph() {
		//TODO: Construct Graph
		System.out.println("*** Construct Graph ***");
	}
	
	private void labelNimGraph() {
		//TODO: Label Graph
		System.out.println("*** Label Graph ***");
	}
	
	private void playNim() {
		//TODO: Implement Nim
		System.out.println("*** Play NIM ***");
		NimGame game = new NimGame(initialMatchsticks);
		game.start();
	}
}
