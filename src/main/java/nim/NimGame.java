package nim;

import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Plays the Game of NIM (The Subtraction Game)
 * 
 * @author Tyler Haigh - C3182929
 * @author Simon Hartcher - C3185790
 *
 */
public class NimGame {

	//Private instance variables
	private int initialNumMatchsticks;
	private int numSticksLeft;
	private int lastNumRemoved;
	
	private GameType gameType;
	private Scanner console;
	private boolean playersTurn;
	
	private AdjacencyList nimGraph;
	private NimVertex gameState;
	
	/**
	 * Constructor for the NIM Game. Sets the initial state of the game
	 * 
	 * @param initialNumMatchsticks The number of sticks to start the game with
	 */
	public NimGame(int initialNumMatchsticks) {
		this.initialNumMatchsticks = initialNumMatchsticks;
		this.numSticksLeft = initialNumMatchsticks;
		console = new Scanner(System.in);
	}
	
	/**
	 * Starts the NIM game
	 */
	public void start() {
		boolean userQuit = false;
		while (!userQuit) {
			userQuit = setGameType();
			if (!userQuit) userQuit = playGame();
		}
	}
	
	/**
	 * Offers the user the choice to set the game type to play with
	 * 
	 * @return True if the player quit, False otherwise
	 */
	private boolean setGameType() {
		//Display choices
		System.out.println("Please select the level:");
		System.out.println("\t1. You Win");
		System.out.println("\t2. Fair Go");
		System.out.println("\t3. Mission Impossible");
		
		System.out.println();
		System.out.println("To return to the previous menu, enter -1 at any time\n");
		
		//Get user input for game type
		int gameType = console.nextInt();
		
		System.out.println("<" + gameType + ">\n");
		
		//User selected to quit
		if (gameType == -1) return true;
		
		while (gameType < 1 || gameType > 3) {
			System.out.println("Error: Level is out of bounds");
			System.out.print("Please select the level: ");
			gameType = console.nextInt();
		}
		
		//Get the Game type to use when playing
		this.gameType = GameType.fromInteger(gameType);
		return false;
	}
	
	/**
	 * Main game loop for the Game of NIM. Continues playing game until the
	 * game is over (0 sticks left) or the player chooses to quit (enters -1)
	 * 
	 * @return True if the player quit, False otherwise
	 */
	private boolean playGame() {
		//create the game board
		this.nimGraph = NimAlgorithms.constructNimGraph(initialNumMatchsticks);
		
		numSticksLeft = initialNumMatchsticks;
		playersTurn = getFirstPlayerTurn();
		this.gameState = (NimVertex)nimGraph.getVertex(0);
		
		while (numSticksLeft > 0) {
			int numSticksToRemove = -1;
			
			System.out.println();
			
			String message = (numSticksLeft == initialNumMatchsticks) ? 
					"There are " + numSticksLeft + " matchsticks on the table. " :
					(numSticksLeft == 1) ?
							"There is now " + numSticksLeft + " matchstick on the table. " :
							"There are now " + numSticksLeft + " matchsticks on the table. ";
			
			System.out.print(message);
			
			if (playersTurn) {
				//Get the player to input the number of sticks to remove
				System.out.println("How many will you take?\n");
				numSticksToRemove = console.nextInt();
				
				System.out.println("<" + numSticksToRemove + ">\n");
				
				//Stop playing if they quit the game
				if (numSticksToRemove == -1) return true;
				
				//Validate input
				while (!validMove(numSticksToRemove)) {
					String error = (numSticksToRemove == 0) ?
							"Invalid move! You have to take at least 1 matchstick." :
							(numSticksLeft == initialNumMatchsticks) ?
									"Invalid move! As there is only " + initialNumMatchsticks + " matchsticks, " +
										"you cannot take more than " + (initialNumMatchsticks - 1):
									(lastNumRemoved == 1) ?
										"Invalid move! As I only took 1 matchstick last time, you cannot take more than 2.": 
										"Invalid move! As I only took " + lastNumRemoved + " matchsticks last time, " +
											"you cannot take more than " + Math.min(2 * lastNumRemoved, numSticksLeft);
					
					System.out.println(error);
					
					System.out.println("How many will you take?\n");
					numSticksToRemove = console.nextInt();
					
					System.out.println("<" + numSticksToRemove + ">\n");
				}
				
			} else {
				numSticksToRemove = getComputerMove();
				System.out.println("I take " + numSticksToRemove);
			}
			
			numSticksLeft -= numSticksToRemove;
			lastNumRemoved = numSticksToRemove;
			this.gameState = (NimVertex)gameState.getAdjacentVertexAt(numSticksToRemove - 1);
			//System.out.println(gameState);
			
			if (gameOver()) {
				System.out.println();
				System.out.print("There are now 0 matchsticks on the table. ");
				System.out.println((playersTurn) ? "You Win!\n" : "I Win!\n");
			}
			
			playersTurn = !(playersTurn);
		}
		
		//Game is over
		return false;
	}
 	
	/**
	 * Determines who will take the first move based on the Game Type
	 * 
	 * @return True if the player will go first, False if the computer will go first
	 */
	private boolean getFirstPlayerTurn() {
		boolean playersTurn = false;
		
		if (gameType == GameType.FairGo) {
			//Get the user input
			console = new Scanner(System.in);
			
			//Determine whether the player wants to go first
			System.out.println("Do you want to go first? [Y|N]?\n");
			String playFirst = console.next();
			System.out.println("<" + playFirst + ">\n");

			//Perform error checking for invalid entry
			while (!playFirst.equalsIgnoreCase("Y") && !playFirst.equalsIgnoreCase("N")) {
				System.out.println("Error: Please enter either Y or N");
				System.out.print("Do you want to go first? [Y|N]?\n");
				playFirst = console.next();
				System.out.println("<" + playFirst + ">\n");
			}
			
			//Set whose turn it is
			playersTurn = (playFirst.equalsIgnoreCase("Y")) ? true : false;
		} else {
			//Ask the computer if he wants to go first
			playersTurn = (gameType == GameType.MissionImpossible) ? !computerFirstTurn(gameType) : true;
		}
		
		String message = (playersTurn) ?
				"You go first." :
				"I go first.";
		System.out.println(message);
		
		//Re-initialise the scanner to prevent issues with reading input
		console = new Scanner(System.in);
		
		return playersTurn;
	}

	/**
	 * Determines whether the computer would like to go first
	 * @param  gameType The given game type
	 * @return boolean	The computer's choice
	 */
	private boolean computerFirstTurn(GameType gameType) {

		//get first position
		NimVertex currentState = 
			(NimVertex)this.nimGraph.getVertex(initialNumMatchsticks - numSticksLeft);
		
		//label the graph
		NimAlgorithms.labelNimGraph(currentState);	

		//get (n, n-1)
		NimVertex vertex = (NimVertex)this.nimGraph.getVertex(initialNumMatchsticks - 1);

		return !vertex.isWinning();
	}
	
	/**
	 * Calculates the number of sticks the computer will take based on the Game
	 * Type as chosen by the player
	 * 
	 * @return The number of sticks the computer will remove
	 */
	private int getComputerMove() {
		LinkedList<NimVertex> labels = NimAlgorithms.labelNimGraph(gameState);

		boolean wantToWin = gameType == GameType.MissionImpossible 
			? true : gameType == GameType.FairGo ? true : false;

		//System.out.println("Current vertex: " + gameState);

		//determine next position
		NimVertex chosenPosition = null;
		for (Vertex v: gameState.getAdjacentVertices()) {
			NimVertex thisVertex = (NimVertex)v;
			//System.out.println("Considering Vertex: " + thisVertex);

			if (!thisVertex.isWinning() == wantToWin) {
				chosenPosition = thisVertex;

				//System.out.println("Choosing vertex: " + chosenPosition);
			}
		}
		
		chosenPosition = chosenPosition == null ? (NimVertex)gameState.getAdjacentVertices().getFirst() : chosenPosition;
		//System.out.println("Chosen vertex: " + chosenPosition);
		int numSticks = gameState.getSticksRemaining() - chosenPosition.getSticksRemaining();
		return numSticks;
	}
	
	/**
	 * Validates a move as made by the computer or player
	 *
	 * @param numSticksToRemove The number of sticks attempting to remove
	 * @return Whether removing the input number of sticks is a valid move or not
	 */
	private boolean validMove(int numSticksToRemove) {
		
		int upperBound = (numSticksLeft == initialNumMatchsticks) ?
				initialNumMatchsticks - 1
				: Math.min(lastNumRemoved * 2, numSticksLeft);
		
		return (numSticksToRemove != 0) && (numSticksToRemove <= upperBound);
	}
	
	/**
	 * Checks whether the game has been won
	 * @return Whether the game is over or not
	 */
	private boolean gameOver() {
		return numSticksLeft == 0;
	}

}
