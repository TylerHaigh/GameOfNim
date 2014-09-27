package nim;

import java.util.LinkedList;

public class Vertex {
	
	//Private static instance variables
	private static int _index = 1;
	
	//Private instance variables
	private LinkedList<Vertex> adjacentVertices;
	private int index;

	//Constructors
	
	public Vertex() {
		adjacentVertices = new LinkedList<Vertex>();
		index = _index;
		_index++;
	}
		
	//Getters
	
	public LinkedList<Vertex> getAdjacentVertices() {
		return adjacentVertices;
	}

	public int getIndex() {
		return index;
	}

	//Setters

	public void setAdjacentVertices(LinkedList<Vertex> adjacentVertices) {
		this.adjacentVertices = adjacentVertices;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	//Queries
	
	public void append(Vertex v) {
		adjacentVertices.add(v);
	}
}