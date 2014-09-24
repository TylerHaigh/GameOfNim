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
		int userInput = 0;
		
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
		while (userInput != -1) {
			System.out.println("It is " + ((playersTurn) ? "your" : "my") + " turn");
			playersTurn = !(playersTurn);
			
			userInput = console.nextInt();
		}
		
	}
	
	private int getComputerMove() {
		Random rand = new Random();
		
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

}
