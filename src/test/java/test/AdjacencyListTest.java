package test;

import java.util.LinkedList;

import junit.framework.Assert;
import nim.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyListTest {

	@Test
	public void testConstructor() {
		AdjacencyList list = new AdjacencyList(2);
		LinkedList<Vertex> ll = new LinkedList<Vertex>();
		
		ll.add(null);
		ll.add(null);
		
		Assert.assertEquals(ll, list.getList());
	}
	
	@Test
	public void testSize_UniqueVertices() {
		AdjacencyList adjList = new AdjacencyList(3);
		
		adjList.add(new Vertex(), 0);
		adjList.add(new Vertex(), 1);
		adjList.add(new Vertex(), 2);
		
		int expectedSize = 3;
		
		Assert.assertEquals(expectedSize, adjList.size());
	}
	
	@Test
	public void testSize_AppendedVertices() {
		AdjacencyList adjList = new AdjacencyList(3);
		
		adjList.add(new Vertex(), 0);
		adjList.add(new Vertex(), 0);
		adjList.add(new Vertex(), 1);
		
		int expectedSize = 2;
		
		Assert.assertEquals(expectedSize, adjList.size());
	}
	
	@Test
	public void testAdd_TestAddReference() {
		AdjacencyList adjList = new AdjacencyList(2);
		Vertex addVertex = new Vertex();
		adjList.add(addVertex, 0);
		
		Assert.assertEquals(addVertex, adjList.getVertex(0));
		
	}
	
	@Test
	public void testAdd_TestListModification() {
		Vertex v = new Vertex();
		AdjacencyList actualAdjList = new AdjacencyList(2);
		actualAdjList.add(v, 0);
		
		LinkedList<Vertex> expectedList = new LinkedList<Vertex>();
		expectedList.add(v);
		expectedList.add(null);
		
		Assert.assertEquals(expectedList, actualAdjList.getList());
	}
	
	@Test
	public void testAdjacentVertices() {
		AdjacencyList adjList = new AdjacencyList(2);
		Vertex addVertex = new Vertex();
		
		adjList.add(addVertex, 0);
		adjList.add(addVertex, 0);
		int expetedSize = 1;
		
		Assert.assertEquals(expetedSize, adjList.adjacentVertices(0));
	}
	
	@Test
	public void testGetVertex_HeadVertex() {
		AdjacencyList adjList = new AdjacencyList(2);
		Vertex v = new Vertex();
		adjList.add(v, 0);
		
		Assert.assertEquals(v, adjList.getVertex(0));
	}
	
	@Test
	public void testGetVertex_AppendedVertex() {
		AdjacencyList adjList = new AdjacencyList(2);
		Vertex head = new Vertex();
		Vertex appended = new Vertex();
		
		adjList.add(head, 0);
		adjList.add(appended, 0);
		
		Assert.assertEquals(appended, adjList.getVertex(0, 0));
	}
	
	@Test
	public void testPrintList_HeadVertex() {
		AdjacencyList adjList = new AdjacencyList(2);
		NimVertex head = new NimVertex(5,4);
		NimVertex appended = new NimVertex(1,1);
		NimVertex included = new NimVertex(0,0);
		
		adjList.add(head, 0);
		adjList.add(appended, 0);
		adjList.add(included, 1);
		
		String expectedOutput = "(0,0)";
		
		Assert.assertEquals(expectedOutput, adjList.printList(1));
	}
	
	@Test
	public void testPrintList_AppendedVertex() {
		AdjacencyList adjList = new AdjacencyList(2);
		NimVertex head = new NimVertex(5,4);
		NimVertex appended = new NimVertex(1,1);
		NimVertex notIncluded = new NimVertex(0,0);
		
		adjList.add(head, 0);
		adjList.add(appended, 0);
		adjList.add(notIncluded, 1);
		
		String expectedOutput = "(5,4):(1,1)";
		
		Assert.assertEquals(expectedOutput, adjList.printList(0));
	}
	
	@Test
	public void testToString() {
		AdjacencyList adjList = new AdjacencyList(2);
		NimVertex head = new NimVertex(5,4);
		NimVertex appended = new NimVertex(1,1);
		NimVertex included = new NimVertex(0,0);
		
		adjList.add(head, 0);
		adjList.add(appended, 0);
		adjList.add(included, 1);
		
		String expectedOutput = "(5,4):(1,1)\n(0,0)";
		String actualOutput = adjList.toString();
		
		Assert.assertEquals(expectedOutput, actualOutput);
	}
}
