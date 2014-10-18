package test;

import java.util.LinkedList;

import nim.*;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class VertexTest {

	@Test
	public void testAppend() {
		Vertex v = new Vertex();
		Vertex a = new Vertex();
		v.append(a);
		
		LinkedList<Vertex> expectedList = new LinkedList<Vertex>();
		expectedList.add(a);
		
		LinkedList<Vertex> actualList = v.getAdjacentVertices();
		
		Assert.assertEquals(expectedList, actualList);
	}
	
	@Test
	public void testNumEdgesOut() {
		Vertex v = new Vertex();
		Vertex a = new Vertex();
		v.append(a);
		
		int expectedNumEdges = 1;
		int actualNumEdges = v.numEdgesOut();
		
		Assert.assertEquals(expectedNumEdges, actualNumEdges);
	}
	
	@Test
	public void testGetAdjacentertexAt() {
		Vertex v = new Vertex();
		Vertex a = new Vertex();
		v.append(a);
		
		Vertex actualAdjacent = v.getAdjacentVertexAt(0);
		Assert.assertEquals(a, actualAdjacent);
	}
	
	@Test
	public void testEquals_IsolatedVertices() {
		Vertex v = new Vertex();
		Vertex w = new Vertex();
		
		Assert.assertEquals(v, w);
	}
	
	@Test
	public void testEquals_AppendedVertices() {
		Vertex v = new Vertex();
		Vertex a = new Vertex();
		v.append(a);
		
		Vertex w = new Vertex();
		Vertex b = new Vertex();
		w.append(b);
		
		Assert.assertEquals(v, w);
	}
	
	@Test
	public void testEquals_MarkedVertices() {
		Vertex v = new Vertex();
		Vertex a = new Vertex();
		a.setMarked(true);
		v.append(a);
		
		Vertex w = new Vertex();
		Vertex b = new Vertex();
		b.setMarked(true);
		w.append(b);
		
		Assert.assertEquals(v, w);
	}
}
