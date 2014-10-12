package nim;

import java.util.Random;
import java.util.Scanner;

public class NimGame {

	private int initialNumMatchsticks;
	private int numSticksLeft;
	private int lastNumRemoved;
	
	private GameType gameType;
	private Scanner console;
	private boolean playersTurn;
	
	public NimGame(int initialNumMatchsticks) {
		this.initialNumMatchsticks = initialNumMatchsticks;
		this.numSticksLeft = initialNumMatchsticks;
		console = new Scanner(System.in);
	}
	
	public void start() {
		setGameType();
		playGame();
	}
	
	private void setGameType() {
		System.out.println("Please select the level:");
		System.out.println("\t[1] You Win");
		System.out.println("\t[2] Fair Go");
		System.out.println("\t[3] Mission Impossible");
		
		int gameType = console.nextInt();
		while (gameType < 1 || gameType > 3) {
			System.out.println("Error: Level is out of bounds");
			System.out.print("Please select the level: ");
			gameType = console.nextInt();
		}
		
		this.gameType = GameType.FromInteger(gameType);
	}
	
	private void playGame() {
		System.out.println("To return to the previous menu, enter -1 at any time");

		if (gameType == GameType.FairGo) {
			System.out.print("Do you want to go first? [Y|N]: ");
			
			console = new Scanner(System.in);
			String playFirst = console.next();
			
			while (!playFirst.equalsIgnoreCase("Y") && !playFirst.equalsIgnoreCase("N")) {
				System.out.println("Error: Please enter either Y or N");
				System.out.print("Do you want to go first? [Y|N]: ");
				playFirst = console.next();
			}
			
			playersTurn = (playFirst.equalsIgnoreCase("Y")) ? true : false;
		} else {
			playersTurn = (gameType == GameType.MissionImpossible) ? false: true;
		}
		
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
 	
	private int getComputerMove() {
		Random rand = new Random();
		
		//TODO: Base computers decision on the game type
		if (gameType == GameType.MissionImpossible) {
			//Send player to losing position
		} else if (gameType == GameType.YouWin) {
			//Send player to winning position
		} else {
			//Play fair?
		}
		
		if (numSticksLeft == initialNumMatchsticks) {
			return rand.nextInt(initialNumMatchsticks);
		} else {
			int upperBound = Math.min(lastNumRemoved * 2, numSticksLeft);
			return rand.nextInt(upperBound);
		}
	}
	
	private boolean validMove(int numSticksToRemove) {
		int upperBound = Math.min(lastNumRemoved * 2, numSticksLeft);
		return numSticksToRemove <= upperBound;
	}
	
	private boolean gameOver() {
		return numSticksLeft == 0;
	}

}
