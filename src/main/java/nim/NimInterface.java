package nim;

import java.util.*;

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
		System.out.println("Hello and welcome to NIM!\n");
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
		
		System.out.println("Player A starts, taking some matchsticks; he/she " +
				"must take at least one on the table.\n");
		
		System.out.println("Player B then takes some matchsticks; he/she " +
				"must take at lease one, but cannot take more than double the " +
				"number of matchsticks player A took; obviously player B cannot " + 
				"take more than the number of the matchsticks left on the table.\n");
		
		System.out.println("In general, a player can take any number of " +
				"matchsticks between 1 and double the numer that the previous " +
				"player took, but obviously no more than the number of " +
				"matchsticks left on the table.\n");
		
		System.out.println("Player to take the last matchstick wins.");
	}
	
	/**
	 * Updates the initial number of matchsticks used when the NIM game is
	 * played. Gets the new number from the user
	 */
	private void updateMatchsticks() {
		
		System.out.println();
		
		//Display the message to the user
		String message = "Please enter the " +
				((initialMatchsticks == -1) ? "initial " : "") + 
				"number of matchsticks on the table.\n";
		System.out.println(message);

		//Get the new number of matchsticks
		int numSticks = console.nextInt();
		
		System.out.println("<" + numSticks + ">\n");
		
		while (numSticks < 2) {
			System.out.println("Error: NIM requires at least 2 initial sticks to play\n");
			System.out.println(message);
			numSticks = console.nextInt();
			
			System.out.println("<" + numSticks + ">\n");
		}
		
		//Update the number of matchsticks and clear out the generated NIM Graph
		this.initialMatchsticks = numSticks;
		this.game = new NimGame(numSticks);
		this.nimGraph = null;
		
		//Notify that the number of sticks has been updated
		System.out.println("There are " + initialMatchsticks + " matchsticks on the table.\n");
	}
	
	/**
	 * Main loop for the NimInterface. Gets user input to various functions available
	 */
	private void displayMainMenu() {
		int userOption = -1;
		
		//Loop while the user as not selected quit
		while (userOption != 5) {
			
			displayMainMenuOptions();
			userOption = console.nextInt();
			
			System.out.println("<" + userOption + ">");
			
			//Run the selected function
			switch (userOption) {
				case 1 : constructNimGraph(); break;
				case 2 : labelNimGraph(); break;
				case 3 : playNim(); break;
				case 4 : updateMatchsticks(); break;
				//case 5 : displayRules(); break;
				case 5 : /*Do Nothing*/ break;
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
		System.out.println("\t1. Construct the NIM graph");
		System.out.println("\t2. Label the graph nodes winning/losing");
		System.out.println("\t3. Play NIM");
		System.out.println("\t4. Change the initial number of matchsticks on the table");
		System.out.println("\t5. Exit");
		System.out.println();
	}
	
	/**
	 * Generates an Adjacency List representation of the NIM Graph for the
	 * input number of matchsticks. Keeps the graph in memory for fast access
	 */
	private void constructNimGraph() {
		
		System.out.println();
		System.out.println("The NIM graph for n = " + initialMatchsticks + ":");
		System.out.println();
		
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
		
		System.out.println();
	}
	
	/**
	 * Labels each vertex in the NIM graph as either winning or losing
	 * and displays the result to the user
	 */
	private void labelNimGraph() {
		
		System.out.println();
		System.out.println("The node labels in the NIM graph for n = " + initialMatchsticks + ":");
		System.out.println();
		
		LinkedList<NimVertex> sortedList;
		try {
			
			sortedList = (this.nimGraph != null) ? 
					NimAlgorithms.labelNimGraph(nimGraph) :
					NimAlgorithms.labelNimGraph(initialMatchsticks);
			
		} catch (OutOfMemoryError err) {
			System.out.println("Error: Java ran out of memory attempting " + 
					"to generate the NIM Graph. Please change the number " + 
					"of sticks to a lower number");
			return;
		}
		
		for (int i = 0; i < sortedList.size(); i++) {
			NimVertex v = sortedList.get(i);
			if (v != null) {
				String message = v.toString() + ": ";
				message += (v.isWinning()) ? "Winning" : "Losing";
				System.out.println(message);
			}
				
		}
		
		System.out.println();
	}
	
	/**
	 * Starts playing the game of NIM with the input number of matchsticks
	 */
	private void playNim() {
		System.out.println();
		this.game.start();
	}
}
