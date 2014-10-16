package nim;

import java.util.Scanner;

/**
 * Displays the main user interface for the NIM Game to the user. Provides
 * all interface input and output to the program.
 * 
 * @author Tyler Haigh - C3182929
 * @author Simon Hartcher - C3185790
 *
 */
public class NimInterface {
	
	//Private instance variables
	private int initialMatchsticks = -1;
	private Scanner console;
	private NimGame game;
	
	//Hold the graph for quick access 
	private AdjacencyList nimGraph;
	
	/**
	 * Constructor for the NimInterface. Initialises a Scanner to read the
	 * console
	 */
	public NimInterface() {
		console = new Scanner(System.in);
	}
	
	/**
	 * Starts the interface for the program 
	 */
	public void run() {
		System.out.println("Hello and welcome to NIM!");
		displayRules();
		updateMatchsticks();
		displayMainMenu();
	}
	
	/**
	 * Displays the rules required to play NIM (The Subtraction Game)
	 */
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
	
	/**
	 * Updates the initial number of matchsticks used when the NIM game is
	 * played. Gets the new number from the user
	 */
	private void updateMatchsticks() {
		
		//Display the message to the user
		String message = "Please enter the " +
				((initialMatchsticks == -1) ? "initial " : "") + 
				"number of matchsticks on the table: ";
		System.out.print(message);

		//Get the new number of matchsticks
		int numSticks = console.nextInt();
		while (numSticks < 2) {
			System.out.println("Error: NIM requires at least 2 initial sticks to play");
			System.out.print(message);
			numSticks = console.nextInt();
		}
		
		//Update the number of matchsticks and clear out the generated NIM Graph
		this.initialMatchsticks = numSticks;
		this.game = new NimGame(numSticks);
		this.nimGraph = null;
		
		//Notify that the number of sticks has been updated
		System.out.println("There are " + initialMatchsticks + " matchsticks on the table");
	}
	
	/**
	 * Main loop for the NimInterface. Gets user input to various functions available
	 */
	private void displayMainMenu() {
		int userOption = -1;
		
		//Loop while the user as not selected quit
		while (userOption != 6) {
			
			displayMainMenuOptions();
			userOption = console.nextInt();
			
			//Run the selected function
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
	
	/**
	 * Displays the number of options available to the user and the required
	 * input to access it
	 */
	private void displayMainMenuOptions() {
		System.out.println("Please select a number from the following menu:");
		System.out.println("\t[1] Construct the NIM graph");
		System.out.println("\t[2] Label the graph nodes winnong/losing");
		System.out.println("\t[3] Play NIM");
		System.out.println("\t[4] Change the initial number of matchsticks on the table");
		System.out.println("\t[5] Display Help");
		System.out.println("\t[6] Exit");
	}
	
	/**
	 * Generates an Adjacency List representation of the NIM Graph for the
	 * input number of matchsticks. Keeps the graph in memory for fast access
	 */
	private void constructNimGraph() {
		
		//Check if the graph has not been generated
		if (this.nimGraph == null) {
			try {
				//Generate the Graph and store it to prevent having to generate successively
				AdjacencyList nimGraph = NimAlgorithms.constructNimGraph(initialMatchsticks);
				this.nimGraph = nimGraph;
			} catch (OutOfMemoryError err ) {
				System.out.println("Error: Java ran out of memory attempting " + 
						"to generate the NIM Graph. Please change the number " + 
						"of sticks to a lower number");
				return;
			}
		}
		
		//Print each Vertex's adjacent neighbours
		
		//It was decided that printing then entire graph at once would cause
		//too significant a delay in responsiveness. Printing each Vertex will
		//only improve responsiveness, not efficiency 
		for (int i = 0; i < nimGraph.size(); i++) {
			System.out.println(nimGraph.printList(i));
		}
	}
	
	/**
	 * Labels each vertex in the NIM graph as either winning or losing
	 * and displays the result to the user
	 */
	private void labelNimGraph() {
		
		NimVertex[] sortedList;
		if (this.nimGraph != null) {
			sortedList = NimAlgorithms.labelNimGraph(nimGraph);
		} else {
			try {
				sortedList = NimAlgorithms.labelNimGraph(initialMatchsticks);
			} catch (OutOfMemoryError err) {
				System.out.println("Error: Java ran out of memory attempting " + 
						"to generate the NIM Graph. Please change the number " + 
						"of sticks to a lower number");
				return;
			}
		}
		
		for (int i = 0; i < sortedList.length; i++) {
			Vertex v = sortedList[i];
			System.out.print(v.toString() + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * Starts playing the game of NIM with the input number of matchsticks
	 */
	private void playNim() {
		//TODO: Implement Nim
		System.out.println("*** Play NIM ***");
		this.game.start();
	}
}
