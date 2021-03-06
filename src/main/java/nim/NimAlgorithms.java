package nim;

import java.util.*;

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
	 * @param adjList An Adjacency List representing the NIM Graph
	 *
	 * @return An array of Vertices sorted topologically and marked as either
	 * 			"Winning" or "Losing"
	 * 
	 * @runningTime Oh(n)
	 * - Theta(n) to un-mark Vertices
	 * - Oh(n) to label Vertices
	 */
	public static LinkedList<NimVertex> labelNimGraph(AdjacencyList adjList) {
		//Set all as unmarked ( Theta(n) )
		for (int i = 0; i < adjList.size(); i++) {
			NimVertex v = (NimVertex)adjList.getVertex(i);
			v.setMarked(false);
		}

		//Label Graph ( Oh(n) )
		return labelNimGraph((NimVertex)adjList.getVertex(0));
	}
	
	/**
	 * Labels each node in the NIM graph as either "Winning" or "Losing"
	 * 
	 * @param initialNumMatchsticks The number of matchsticks to generate the
	 * 								Graph
	 * @return An array of Vertices sorted topologically and marked as either
	 * 			"Winning" or "Losing"
	 * 
	 * @runningTime Oh(n^3)
	 * - Oh(n^3) - Construct Graph
	 * - Oh(n) - Label graph
	 */
	public static LinkedList<NimVertex> labelNimGraph(int initialNumMatchsticks) {
		AdjacencyList adjList = constructNimGraph(initialNumMatchsticks);
		return labelNimGraph(adjList);
	}
	
	/**
	 * Labels each node in the NIM graph as either "Winning" or "Losing"
	 * 
	 * @param v Vertex to start from
	 *
	 * @return An array of Vertices sorted topologically and marked as either
	 * 			"Winning" or "Losing"
	 * 
	 * @runningTime Oh(n)
	 */
	public static LinkedList<NimVertex> labelNimGraph(NimVertex v) {
		
		//Sort the NIM Graph ( Oh(n) )
		LinkedList<NimVertex> sortedList = topSortRecursive(v);

		//winning/losing ( Oh(n) )
		for (NimVertex n: sortedList) {

			if (n.getAdjacentVertices().size() == 0) {
				//game is over at this vertex
				n.setWinning(false);
			}
			else {
				for (Vertex m: n.getAdjacentVertices()) {
					//if any adjacent vertices are losing
					if (!((NimVertex)m).isWinning()) {
						//set n to winning and break
						n.setWinning(true);
						break;
					}
				}
			}
		}

		//Reverse ( Oh(n) )
		Collections.reverse(sortedList);
		return sortedList;
	}

	/**
	 * Performs a recursive topological sort on the NIM Graph
	 * 
	 * @param v The Vertex the function is using as a point of reference 
	 * @return A sorted list of NIM Vertices
	 * @runningTime Oh(n)
	 */
	private static LinkedList<NimVertex> topSortRecursive(NimVertex v) {
		LinkedList<NimVertex> list = new LinkedList<NimVertex>();

		//if marked do nothing
		if (v.isMarked()) return list;

		//otherwise mark v
		v.setMarked(true);

		//then traverse children
		for (Vertex n: v.getAdjacentVertices()) {
			list.addAll(topSortRecursive((NimVertex)n));
		}

		//add to list 
		list.add(v);

		return list;
	}
}
