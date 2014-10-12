package nim;

public class NimVertex extends Vertex {

	private int sticksRemaining;
	private int sticksCanTake;
	
	private boolean winning;
	
	public NimVertex(int sticksRemaining, int sticksCanTake) {
		super();
		this.sticksRemaining = sticksRemaining;
		this.sticksCanTake = sticksCanTake;
	}

	public int getSticksRemaining() {
		return sticksRemaining;
	}

	public int getSticksCanTake() {
		return sticksCanTake;
	}
	
	public boolean isWinning() {
		return winning;
	}

	public void setSticksRemaining(int sticksRemaining) {
		this.sticksRemaining = sticksRemaining;
	}

	public void setSticksCanTake(int sticksCanTake) {
		this.sticksCanTake = sticksCanTake;
	}
	
	public void setWinning(boolean winning) {
		this.winning = winning;
	}
	
	public String toString() {
		return "(" + sticksRemaining + "," + sticksCanTake + ")";
	}
}
