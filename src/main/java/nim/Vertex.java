package nim;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex {
	
	//Private static instance variables
	private static int _index = 1;
	
	//Private instance variables
	private LinkedList<Vertex> adjacentVertices;
	private int index;

	private boolean marked;
	
	//Constructors
	
	public Vertex() {
		this.adjacentVertices = new LinkedList<Vertex>();
		this.index = _index;
		this.marked = false;
		
		_index++;
	}
		
	//Getters
	
	public LinkedList<Vertex> getAdjacentVertices() {
		return adjacentVertices;
	}

	public int getIndex() {
		return index;
	}
	
	public boolean isMarked() {
		return marked;
	}

	//Setters

	public void setAdjacentVertices(LinkedList<Vertex> adjacentVertices) {
		this.adjacentVertices = adjacentVertices;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
	//Queries
	
	public void append(Vertex v) {
		adjacentVertices.add(v);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || this.getClass() != obj.getClass())
			return false;

		Vertex other = (Vertex) obj;
		if (this.adjacentVertices != null && other.adjacentVertices != null) {
			if (adjacentVertices.size() != other.adjacentVertices.size()) {
				return false;
			} else {
				Iterator<Vertex> thisIt = this.adjacentVertices.iterator();
				Iterator<Vertex> otherIt = other.adjacentVertices.iterator();
				
				while (thisIt.hasNext()) {
					Vertex v = thisIt.next();
					Vertex w = otherIt.next();
					
					if (!v.equals(w)) {
						return false;
					}
				}
			}
		} else if (this.adjacentVertices == null && other.adjacentVertices != null) {
			return false;
		} else if (this.adjacentVertices != null && other.adjacentVertices == null) {
			return false;
		}
		
		return true;
	}
}