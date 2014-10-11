package nim;

public enum GameType {
	Undefined,
	YouWin,
	FairGo,
	MissionImpossible;
	
	public static GameType FromInteger(int val) {
		switch (val) {
			case 1 : return YouWin;
			case 2 : return FairGo;
			case 3 : return MissionImpossible;
			default : return Undefined;
		}
	}
}
