package nim;

/**
 * Defines the type of game to be played
 *  
 * @author Tyler Haigh - C3182929
 * @author Simon Hartcher - C3185790
 */
public enum GameType {
	Undefined,
	YouWin,
	FairGo,
	MissionImpossible;
	
	/**
	 * Converts an integer to its corresponding GameType value
	 * 
	 * @param val The value to convert
	 * @return The corresponding Game Type
	 */
	public static GameType fromInteger(int val) {
		switch (val) {
			case 1 : return YouWin;
			case 2 : return FairGo;
			case 3 : return MissionImpossible;
			default : return Undefined;
		}
	}
}
