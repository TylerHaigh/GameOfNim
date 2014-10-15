package nim;

import java.util.Random;
import java.util.Scanner;

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
		setGameType();
		playGame();
	}
	
	/**
	 * Offers the user the choice to set the game type to play with
	 */
	private void setGameType() {
		//Display choices
		System.out.println("Please select the level:");
		System.out.println("\t[1] You Win");
		System.out.println("\t[2] Fair Go");
		System.out.println("\t[3] Mission Impossible");
		
		//Get user input for game type
		int gameType = console.nextInt();
		while (gameType < 1 || gameType > 3) {
			System.out.println("Error: Level is out of bounds");
			System.out.print("Please select the level: ");
			gameType = console.nextInt();
		}
		
		//Get the Game type to use when playing
		this.gameType = GameType.fromInteger(gameType);
	}
	
	/**
	 * Main game loop for the Game of NIM. Continues playing game until the
	 * game is over (0 sticks left) or the player chooses to quit (enters -1)
	 */
	private void playGame() {
		System.out.println("To return to the previous menu, enter -1 at any time");

		if (gameType == GameType.FairGo) {
			//Determine whether the player wants to go first
			System.out.print("Do you want to go first? [Y|N]: ");
			
			//Get the user input
			console = new Scanner(System.in);
			String playFirst = console.next();
			
			//Perform error checking for invalid entry
			while (!playFirst.equalsIgnoreCase("Y") && !playFirst.equalsIgnoreCase("N")) {
				System.out.println("Error: Please enter either Y or N");
				System.out.print("Do you want to go first? [Y|N]: ");
				playFirst = console.next();
			}
			
			//Set whose turn it is
			playersTurn = (playFirst.equalsIgnoreCase("Y")) ? true : false;
		} else {
			playersTurn = (gameType == GameType.MissionImpossible) ? false: true;
		}
		
		//Re-initialise the scanner to prevent issues with reading input
		console = new Scanner(System.in);
		
		//TODO: Implement game
		System.out.println("*** Playing Game ***");
		while (numSticksLeft > 0) {
			int numSticksToRemove = -1;
			
			System.out.println("It is " + ((playersTurn) ? "your" : "the computer's") + " turn");
			
			if (playersTurn) {
				//Get the player to input the number of sticks to remove
				System.out.print("Enter the number of sticks to remove: ");
				numSticksToRemove = console.nextInt();
				
				//Stop playing if they quit the game
				if (numSticksToRemove == -1) return;
				
				//Validate input
				while (!validMove(numSticksToRemove)) {
					System.out.println("Error: Invalid number of sticks");
					System.out.print("Enter the number of sticks to remove: ");
					numSticksToRemove = console.nextInt();
				}
				
			} else {
				numSticksToRemove = getComputerMove();
				System.out.print("The computer takes " + numSticksToRemove + " sticks");
			}
			
			numSticksLeft -= numSticksToRemove;
			System.out.print("The computer takes " + numSticksToRemove + " sticks");
			
			if (gameOver()) {
				System.out.println((playersTurn) ? "You Win!" : "I Win!");
			}
			
			playersTurn = !(playersTurn);
		}
		
	}
 	
	/**
	 * Calculates the number of sticks the computer will take based on the Game
	 * Type as chosen by the player
	 * 
	 * @return The number of sticks the computer will remove
	 */
	private int getComputerMove() {
		Random rand = new Random();
		
		//TODO: Base computers decision on the game type
		if (gameType == GameType.MissionImpossible) {
			//Send player to losing position
		} else if (gameType == GameType.YouWin) {
			//Send player to winning position
		} else {
			//Play fair
			
			if (numSticksLeft == initialNumMatchsticks) {
				return rand.nextInt(initialNumMatchsticks);
			} else {
				int upperBound = Math.min(lastNumRemoved * 2, numSticksLeft);
				return rand.nextInt(upperBound);
			}
		}
		
		return 1; //TODO: Remember to remove this after implementation
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
		
		return numSticksToRemove <= upperBound;
	}
	
	/**
	 * Checks whether the game has been won
	 * @return Whether the game is over or not
	 */
	private boolean gameOver() {
		return numSticksLeft == 0;
	}

}
