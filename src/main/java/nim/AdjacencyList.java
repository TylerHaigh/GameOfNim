package nim;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents an adjacency matrix for a Graph using a list of Adjacent Vertices
 * 
 * @author Tyler Haigh - C3182929
 * @author Simon Hartcher - C3185790
 */
public class AdjacencyList {

	//Private Instance Variables
	private ArrayList<LinkedList<Vertex>> list;
	private int size;
	
	/**
	 * Constructor for an Adjacency List. Initialises a new list on input size
	 * @param size The number of Vertices in the graph
	 */
	public AdjacencyList(int size) {
		//Set the private instance variables
		this.list = new ArrayList<LinkedList<Vertex>>();
		this.size = size;
		
		//Create the empty list
		for (int i = 0; i < size; i++) {
			list.add(new LinkedList<Vertex>());
		}
	}
	
	/**
	 * Gets the size of the Adjacency List 
	 * @return The size of the list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Adds a Vertex to the Adjacency List at the specified index. If the
	 * index has no previously set start Vertex, the input Vertex becomes the
	 * point of reference for adjacent Vertices
	 * 
	 * @param v The Vertex to add to the Adjacency List
	 * @param index The index to add the Vertex to
	 */
	public void add(Vertex v, int index) {
		LinkedList<Vertex> adjList = this.list.get(index);
		adjList.add(v);
	}
	
	/**
	 * Gets the number of adjacent vertices at a given index in the Adjacency
	 * List. Includes the Point of Reference Vertex
	 * 
	 * @param vertexIndex The index within the Adjacency List to refer to
	 * @return The number of adjacent Vertices in the input index
	 */
	public int adjacentVertices(int vertexIndex) {
		LinkedList<Vertex> adjList = this.list.get(vertexIndex);
		return adjList.size();
	}
	
	/**
	 * Gets the Vertex at the List Index, at the Adjacent Vertex Index 
	 * 
	 * @param listIndex The index of the list of Adjacent Vertices to target
	 * @param vertexIndex The index of the Adjacent Vertex being requested
	 * @return The Vertex at the Adjacent Vertex position in the List Index row
	 */
	public Vertex getVertex(int listIndex, int vertexIndex) {
		LinkedList<Vertex> adjList = this.list.get(listIndex);
		return adjList.get(vertexIndex);
	}
	
	/**
	 * Prints a formated String for a given row in the Adjacency List
	 * 
	 * @param index The row to print
	 * @return A formatted string containing all Vertices in the requested list
	 */
	public String printList(int index) {
		String result = "";
		LinkedList<Vertex> adjList = this.list.get(index);
		
		//Loop through all vertices in the index
		for (Vertex v: adjList) {
			result += v.toString();
		}
		
		return result;
	}
	
	/**
	 * Prints a formatted String representation of the entire Adjacency List
	 * 
	 * @return The String representation of the Adjacency List
	 */
	public String toString() {
		String result = "";
		
		//Loop through each list item
		for (LinkedList<Vertex> adjList : list) {
			//Check the list is not empty
			if (adjList.size() > 0) {
				//Loop through all Vertices in the list
				for (Vertex v : adjList) {
					result += v.toString();
				}
				result += "\n";
			}
		}
		
		//Remove the last trailing \n and return
		result = result.substring(0,  result.length()-1);
		return result;
	}
}
