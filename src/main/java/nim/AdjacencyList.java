package nim;

import java.util.ArrayList;
import java.util.LinkedList;

public class AdjacencyList {

	private ArrayList<LinkedList<Vertex>> list;
	private int size;
	
	public AdjacencyList(int size) {
		this.list = new ArrayList<LinkedList<Vertex>>();
		this.size = size;
		
		for (int i = 0; i < size; i++) {
			list.add(new LinkedList<Vertex>());
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public void add(Vertex v, int index) {
		LinkedList<Vertex> adjList = this.list.get(index);
		adjList.add(v);
	}
	
	public int adjacentVertices(int vertexIndex) {
		LinkedList<Vertex> adjList = this.list.get(vertexIndex);
		return adjList.size();
	}
	
	public Vertex getVertex(int listIndex, int vertexIndex) {
		LinkedList<Vertex> adjList = this.list.get(listIndex);
		return adjList.get(vertexIndex);
	}
	
	public String printList(int index) {
		String result = "";
		LinkedList<Vertex> adjList = this.list.get(index);
		
		for (Vertex v: adjList) {
			result += v.toString();
		}
		
		return result;
	}
	
	public String toString() {
		String result = "";
		for (LinkedList<Vertex> adjList : list) {
			if (adjList.size() > 0) {
				for (Vertex v : adjList) {
					result += v.toString();
				}
				result += "\n";
			}
		}
		result = result.substring(0,  result.length()-1);
		return result;
	}
}
