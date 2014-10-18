package nim;

import java.util.Stack;

/**
 * The underlying infrastructure that defines how the NIM Game works.
 * Provides a collection of functions that represent the NIM game
 *  
 * @author Tyler Haigh - C3182929
 * @author Simon Hartcher - C3185790
 *
 */
public class NimAlgorithms {

	/**
	 * Constructs a 2D matrix of NIM Vertices that represents the entire set of
	 * possible moves in the NIM Game for a particular number of matchsticks. <br>
	 * 
	 * @param initialNumMatchsticks The number of matchsticks to generate for
	 * 
	 * @return A 2D matrix of NIM Vertices representing the possible states of
	 * 			NIM moves
	 * 
	 * @runningtime Oh(n^3) <br>
	 * - Theta(n^2) for the matrix traversal <br>
	 * - Oh(n) for the neighbour generation
	 */
	public static NimVertex[][] constructNimMatrix(int initialNumMatchsticks) {
		NimVertex[][] matrix = new NimVertex[(initialNumMatchsticks+1)][(initialNumMatchsticks+1)];

		//Define the start Vertex position
		int startX = initialNumMatchsticks;
		int startY = startX - 1;
		
		//Make the Vertex and its immediate neighbours
		matrix[startX][startY] = new NimVertex(startX, startY);
		makeNeighbours(matrix, startX, startY);
		
		//Loop through the entire matrix ( Theta(n^2) )
		for (int row = 1; row < matrix.length; row++) {
			for (int column= 1; column < matrix[row].length; column++) {
				
				//Create all the neighbours for Vertices as they are found
				if (matrix[row][column] != null) {
					makeNeighbours(matrix, row, column);
				}
			}
		}
		
		return matrix;
	}
	
	/**
	 * Creates the immediate neighbours for a given NIM Vertex
	 * 
	 * @param matrix The matrix containing the NIM Vertices
	 * @param remaining The number of sticks remaining on the table
	 * @param canTake The number of sticks the player can take
	 * 
	 * @runningtime Oh(n) - Dependent on canTake
	 */
	private static void makeNeighbours(NimVertex[][] matrix, int remaining, int canTake) {
		//Count up to canTake ( Oh(n) ) 
		for (int i = 1; i <= canTake; i++) {
			//Calculate a neighbour position
			int neighbourX = remaining - i;
			int neighbourY = Math.min(neighbourX, 2 * i);
			
			//Only add a new vertex if the matrix does not contain it
			if (matrix[neighbourX][neighbourY] ==  null)
				matrix[neighbourX][neighbourY] = new NimVertex(neighbourX, neighbourY);
		}
	}
	
	/**
	 * Constructs an Adjacency List of all Vertices and their adjacent vertices
	 * that exist within the NIM Graph for a given number of matchsticks
	 *  
	 * @param initialNumMatchsticks The number of matchsticks to generate for
	 * @return An Adjacency List containing all connecting Vertices
	 * 
	 * @runningtime Oh(n^3)
	 * - Theta(n^2) for matrix traversal
	 * - Oh(n) for neighbour generation
	 */
	public static AdjacencyList constructNimGraph(int initialNumMatchsticks) {
		//Set up structures to be used
		int matrixSize = initialNumMatchsticks + 1;
		int listSize = (int)Math.ceil(0.5 * (matrixSize * matrixSize));
		AdjacencyList nimGraph = new AdjacencyList(listSize);
		NimVertex[][] matrix = new NimVertex[matrixSize][matrixSize];

		//Calculate start Vertex
		int startX = initialNumMatchsticks;
		int startY = startX - 1;
		
		//Add it to the matrix
		NimVertex vert = new NimVertex(startX, startY);
		matrix[startX][startY] = vert;

		//Add it to the Adjacency List
		int adjListIndex = 0;
		nimGraph.add(vert, adjListIndex);
		
		//Make the neighbours for the start Vertex ( Oh(n) )
		makeNeighbours(matrix, startX, startY, nimGraph, adjListIndex);
		
		//Loop through the entire matrix ( Theta(n^2) )
		for (int row = (matrix.length - 2); row >= 0 ; row--) {
			for (int column= (matrix[row].length - 2); column >= 0 ; column--) {
				
				//Create all the neighbours for Vertices as they are found
				if (matrix[row][column] != null) {
					NimVertex addVert = matrix[row][column];
					
					//Add it to the List as a head Vertex
					adjListIndex++;
					nimGraph.add(addVert, adjListIndex);
					
					//Make the neighbours for the Vertex ( Oh(n) )
					makeNeighbours(matrix, row, column, nimGraph, adjListIndex);
				}
			}
		}
		
		return nimGraph;
		
	}
	
	/**
	 * Creates the immediate neighbours for a given NIM Vertex
	 * 
	 * @param matrix The matrix containing the NIM Vertices
	 * @param remaining The number of sticks remaining on the table
	 * @param canTake The number of sticks the player can take
	 * @param adjList The Adjacency List containing NIM Vertices
	 * @param adjListIndex the list index of the Adjacency List to add to
	 * 
	 * @runningtime Oh(n) - Dependent on canTake
	 */
	private static void makeNeighbours(NimVertex[][] matrix, int remaining, int canTake,
			AdjacencyList adjList, int adjListIndex) {
		
		//Count up to canTake ( Oh(n) ) 
		for (int i = 1; i <= canTake; i++) {
			//Calculate a neighbour position
			int neighbourX = remaining - i;
			int neighbourY = Math.min(neighbourX, 2 * i);
			
			//Only add a new vertex to the matrix if it does not contain it
			NimVertex vert;
			if (matrix[neighbourX][neighbourY] == null) {
				vert = new NimVertex(neighbourX, neighbourY);
				matrix[neighbourX][neighbourY] = vert;
			} else {
				vert = matrix[neighbourX][neighbourY];
			}
			
			//Add the Vertex to the Adjacency List at the target list index
			adjList.add(vert, adjListIndex);
		}
	}
	
	/**
	 * Labels each node in the NIM graph as either "Winning" or "Losing"
	 * 
	 * @param initialNumMatchsticks The number of matchsticks to generate the
	 * 								Graph
	 * @return An array of Vertices sorted topologically and marked as either
	 * 			"Winning" or "Losing"
	 */
	public static NimVertex[] labelNimGraph(int initialNumMatchsticks) {
		AdjacencyList adjList = constructNimGraph(initialNumMatchsticks);
		return labelNimGraph(adjList);
	}
	
	/**
	 * Labels each node in the NIM graph as either "Winning" or "Losing"
	 * 
	 * @param adjList An Adjacency List representing the NIM Graph
	 *
	 * @return An array of Vertices sorted topologically and marked as either
	 * 			"Winning" or "Losing"
	 */
	public static NimVertex[] labelNimGraph(AdjacencyList adjList) {
		NimVertex[] sortedList = new NimVertex[adjList.size()];
		int arrayIndex = 0;
		
		//Set all as unmarked ( Theta(n) )
		for (int i = 0; i < adjList.size(); i++) {
			NimVertex v = (NimVertex)adjList.getVertex(i);
			v.setMarked(false);
		}
		
		Vertex vert = adjList.getVertex(0);
		Stack<Vertex> s = new Stack<Vertex>();
		s.push(vert);
		
		//Sort all Vertices ( Oh(n) )
		while (!s.isEmpty()) {
			Vertex v = s.pop();
			
			sortedList[arrayIndex] = (NimVertex)v;
			arrayIndex++;
			
			v.setMarked(true);
			for (Vertex w : v.getAdjacentVertices()) {
				if (!w.isMarked()) {
					w.setMarked(true);
					s.push(w);
				}
			}
		}
		
		return sortedList;
	}
}
