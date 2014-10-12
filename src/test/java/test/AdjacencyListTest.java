package tests;

import junit.framework.Assert;
import nim.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyListTest {

	@Test
	public void testGetSize() {
		AdjacencyList adjList = new AdjacencyList(3);
		int expectedSize = 3;
		
		Assert.assertEquals(expectedSize, adjList.size());
	}
	
	@Test
	public void testAdd() {
		AdjacencyList adjList = new AdjacencyList(2);
		Vertex addVertex = new Vertex();
		adjList.add(addVertex, 0);
		
		Assert.assertEquals(addVertex, adjList.getVertex(0, 0));
		
	}
	
	@Test
	public void testAdjacentVertices() {
		AdjacencyList adjList = new AdjacencyList(2);
		Vertex addVertex = new Vertex();
		adjList.add(addVertex, 0);
		adjList.add(addVertex, 0);
		int expetedSize = 2;
		
		Assert.assertEquals(expetedSize, adjList.adjacentVertices(0));
	}
}
