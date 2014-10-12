package nim;

public class NimAlgorithms {

	public static NimVertex[][] constructNimMatrix(int initialNumMatchsticks) {
		NimVertex[][] matrix = new NimVertex[(initialNumMatchsticks+1)][(initialNumMatchsticks+1)];

		int startX = initialNumMatchsticks;
		int startY = startX - 1;
		
		matrix[startX][startY] = new NimVertex(startX, startY);
		
		makeNeighbours(matrix, startX, startY);
		
		for (int row = 1; row < matrix.length; row++) {
			for (int column= 1; column < matrix[row].length; column++) {
				if (matrix[row][column] != null) {
					makeNeighbours(matrix, row, column);
				}
			}
		}
		
		return matrix;
	}
	
	private static void makeNeighbours(NimVertex[][] matrix, int remaining, int canTake) {
		for (int i = 1; i <= canTake; i++) {
			int neighbourX = remaining - i;
			int neighbourY = Math.min(neighbourX, 2 * i);
			matrix[neighbourX][neighbourY] = new NimVertex(neighbourX, neighbourY);
		}
	}
	
	private static void makeNeighbours(NimVertex[][] matrix, int remaining, int canTake,
			AdjacencyList adjList, int adjListIndex) {
		for (int i = 1; i <= canTake; i++) {
			int neighbourX = remaining - i;
			int neighbourY = Math.min(neighbourX, 2 * i);
			
			NimVertex vert = new NimVertex(neighbourX, neighbourY);
			matrix[neighbourX][neighbourY] = vert;
			adjList.add(vert, adjListIndex);
		}
	}
	
	public static AdjacencyList constructNimGraph(int initialNumMatchsticks) {
		
		int matrixSize = initialNumMatchsticks + 1;
		int listSize = (int)Math.ceil(0.5 * (matrixSize * matrixSize));
		AdjacencyList nimGraph = new AdjacencyList(listSize);
		NimVertex[][] matrix = new NimVertex[matrixSize][matrixSize];

		int startX = initialNumMatchsticks;
		int startY = startX - 1;
		
		NimVertex vert = new NimVertex(startX, startY);
		matrix[startX][startY] = vert;

		int adjListIndex = 0;
		nimGraph.add(vert, adjListIndex);
		makeNeighbours(matrix, startX, startY, nimGraph, adjListIndex);
		
		for (int row = (matrix.length - 2); row >= 0 ; row--) {
			for (int column= (matrix[row].length - 2); column >= 0 ; column--) {
				if (matrix[row][column] != null) {
					adjListIndex++;
					NimVertex addVert = matrix[row][column];
					
					nimGraph.add(addVert, adjListIndex);
					makeNeighbours(matrix, row, column, nimGraph, adjListIndex);
				}
			}
		}
		
		return nimGraph;
		
	}
	
	public static NimVertex[] labelNimGraph(int initialNumMatchsticks) {
		AdjacencyList adjList = constructNimGraph(initialNumMatchsticks);
		return labelNimGraph(adjList);
	}
	
	public static NimVertex[] labelNimGraph(AdjacencyList adjList) {
		return null;
	}
}
