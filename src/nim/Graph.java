package nim;

import java.util.LinkedList;

public class Graph {

	private LinkedList<Vertex> vertices;
	private LinkedList<Edge> edges;
	
	public Graph() {
		vertices = new LinkedList<Vertex>();
		edges = new LinkedList<Edge>();
	}
	
	/**
	 * Adds a disconnected Vertex
	 */
	public void add() {
		Vertex loneVertex = new Vertex();
		vertices.add(loneVertex);
	}

	/**
	 *	Adds a connected Vertex
	 */
	public void add(int existingVertexIndex) {
		//Create the new Vertex
		Vertex newVertex = new Vertex();

		//Append it to the connected Vertex
		Vertex existingVertex = vertices.get(existingVertexIndex);
		existingVertex.append(newVertex);

		newVertex.append(existingVertex);

		//Connect the two vertices via an edge
		Edge connectingEdge = new Edge();
		connectingEdge.setV(existingVertex);
		connectingEdge.setW(newVertex);

		this.vertices.add(newVertex);
		this.edges.add(connectingEdge);

	}

	public void connect(int vertexIndex1, int vertexIndex2) {
		//Get the respective Vertices
		Vertex v = vertices.get(vertexIndex1);
		Vertex w = vertices.get(vertexIndex2);

		//Create and Edge between the two
		Edge newEdge = new Edge();
		newEdge.setV(v);
		newEdge.setW(w);

		this.edges.add(newEdge);
	}
	
	public String degrees() {
		String result = "";
		for (Vertex v : vertices) {
			int count = v.getAdjacentVertices().size();			
			result += "Vertex " + v.getIndex() + " has degree " + count + "\n";
		}
		
		return result;
	}
}
