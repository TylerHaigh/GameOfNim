package nim;

import java.util.Scanner;

public class NimGame implements Runnable {

	private int initialNumMatchsticks;
	private GameType gameType;
	private Scanner console;
	private boolean playersTurn;
	
	public NimGame(int initialNumMatchsticks) {
		this.initialNumMatchsticks = initialNumMatchsticks;
		console = new Scanner(System.in);
	}
	
	@Override
	public void run() {
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
		
		//TODO: Implement game
		System.out.println("*** Playing Game ***");
		while (userInput != -1) {
			System.out.println("*** Playing Game ***");
			userInput = console.nextInt();
		}
		
	}

}
