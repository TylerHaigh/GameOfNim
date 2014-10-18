import nim.*;

/**
 * The entry point class to the NIM Game. Starts the interface for the game
 * 
 * @author Tyler Haigh - C3182929
 * @author Simon Hartcher - C3185790
 */
public class assign {
	
	/**
	 * Entry point to the program. Starts the NIM Interface
	 * @param args Any arguments to the program
	 */
	public static void main(String[] args) {
		//Start the NIM interface 
		NimInterface nInterface = new NimInterface();
		nInterface.run();
	}
}
