package nim;

import java.util.ArrayList;
import java.util.LinkedList;

public class AdjacencyList {

	private ArrayList<LinkedList<Vertex>> list;
	
	public AdjacencyList(int size) {
		list = new ArrayList<LinkedList<Vertex>>(size);
	}
	
	public void add(Vertex v, int index) {
		LinkedList<Vertex> adjList = this.list.get(index);
		adjList.add(v);
	}
	
	public int adjacentVertices(int vertexIndex) {
		LinkedList<Vertex> adjList = this.list.get(vertexIndex);
		return adjList.size();
	}
	
	public String toString() {
		String result = "";
		for (LinkedList<Vertex> adjList : list) {
			for (Vertex v : adjList) {
				result += v.toString();
			}
			result += "\n";
		}
		result = result.substring(result.length()-1,  result.length());
		return result;
	}
}
