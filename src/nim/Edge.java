package nim;

public class Edge {

	//Private static instance variables
	private static int _index = 1;
	
	//Private instance variables
	private int weight = 1;
	private Vertex v; //First Vertex
	private Vertex w; //Second Vertex
	private int index;

	//Constructors

	public Edge() {
		index = _index;
		_index++;
	}

	//Getters
	
	public int getWeight() {
		return weight;
	}

	public Vertex getV() {
		return v;
	}

	public Vertex getW() {
		return w;
	}

	public int getIndex() {
		return index;
	}
	
	//Setters

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setV(Vertex v) {
		this.v = v;
	}

	public void setW(Vertex w) {
		this.w = w;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}