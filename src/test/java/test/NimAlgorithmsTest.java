package test;

import java.util.Arrays;

import nim.*;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NimAlgorithmsTest {

	@Test
	public void testConstructNimMatrix_3Sticks() {
		int numberOfSticks = 3;
		int matrixSize = numberOfSticks + 1;
		NimVertex[][] expectedMatrix = new NimVertex[matrixSize][matrixSize];
		
		expectedMatrix[3][2] = new NimVertex(3,2);
		expectedMatrix[2][2] = new NimVertex(2,2);
		expectedMatrix[1][1] = new NimVertex(1,1);
		expectedMatrix[0][0] = new NimVertex(0,0);
		
		NimVertex[][] actualMatrix = NimAlgorithms.constructNimMatrix(numberOfSticks);
		
		for (int i = 0; i < expectedMatrix.length; i++) {
			for (int j = 0; j < expectedMatrix[i].length; j++) {
				Vertex v = expectedMatrix[i][j];
				Vertex w = actualMatrix[i][j];
				if (v != null && w != null) {
					assertTrue(v.equals(w));
				} else if (v == null && w != null) {
					fail("Matrices to not match");
				} else if (v != null && w == null) {
					fail("Matrices to not match");
				}
				
			}
		}
	}

	@Test
	public void testConstructNimMatrix_7Sticks() {
		int numberOfSticks = 7;
		int matrixSize = numberOfSticks + 1;
		NimVertex[][] expectedMatrix = new NimVertex[matrixSize][matrixSize];
		
		expectedMatrix[7][6] = new NimVertex(7,6);
		expectedMatrix[6][2] = new NimVertex(6,2);
		expectedMatrix[5][4] = new NimVertex(5,4);
		expectedMatrix[5][2] = new NimVertex(5,2);
		expectedMatrix[4][4] = new NimVertex(4,4);
		expectedMatrix[4][2] = new NimVertex(4,2);
		expectedMatrix[3][3] = new NimVertex(3,3);
		
		expectedMatrix[3][2] = new NimVertex(3,2);
		expectedMatrix[2][2] = new NimVertex(2,2);
		expectedMatrix[1][1] = new NimVertex(1,1);
		expectedMatrix[0][0] = new NimVertex(0,0);
		
		NimVertex[][] actualMatrix = NimAlgorithms.constructNimMatrix(numberOfSticks);
		
		for (int i = 0; i < expectedMatrix.length; i++) {
			for (int j = 0; j < expectedMatrix[i].length; j++) {
				Vertex v = expectedMatrix[i][j];
				Vertex w = actualMatrix[i][j];
				if (v != null && w != null) {
					assertTrue(v.equals(w));
				} else if (v == null && w != null) {
					fail("Matrices to not match");
				} else if (v != null && w == null) {
					fail("Matrices to not match");
				}
				
			}
		}
	}
	
	@Test
	public void testConstructNimMatrix_10Sticks() {
		int numberOfSticks = 10;
		int matrixSize = numberOfSticks + 1;
		NimVertex[][] expectedMatrix = new NimVertex[matrixSize][matrixSize];
		
		expectedMatrix[10][9] = new NimVertex(10,9);
		expectedMatrix[9][2] = new NimVertex(9,2);
		expectedMatrix[8][4] = new NimVertex(8,4);
		expectedMatrix[8][2] = new NimVertex(8,2);
		expectedMatrix[7][4] = new NimVertex(7,4);
		expectedMatrix[7][2] = new NimVertex(7,2);
		expectedMatrix[6][6] = new NimVertex(6,6);
		expectedMatrix[6][4] = new NimVertex(6,4);
		expectedMatrix[5][5] = new NimVertex(5,5);
		
		expectedMatrix[7][6] = new NimVertex(7,6);
		expectedMatrix[6][2] = new NimVertex(6,2);
		expectedMatrix[5][4] = new NimVertex(5,4);
		expectedMatrix[5][2] = new NimVertex(5,2);
		expectedMatrix[4][4] = new NimVertex(4,4);
		expectedMatrix[4][2] = new NimVertex(4,2);
		expectedMatrix[3][3] = new NimVertex(3,3);
		
		expectedMatrix[3][2] = new NimVertex(3,2);
		expectedMatrix[2][2] = new NimVertex(2,2);
		expectedMatrix[1][1] = new NimVertex(1,1);
		expectedMatrix[0][0] = new NimVertex(0,0);
		
		NimVertex[][] actualMatrix = NimAlgorithms.constructNimMatrix(numberOfSticks);
		
		for (int i = 0; i < expectedMatrix.length; i++) {
			for (int j = 0; j < expectedMatrix[i].length; j++) {
				Vertex v = expectedMatrix[i][j];
				Vertex w = actualMatrix[i][j];
				if (v != null && w != null) {
					assertTrue(v.equals(w));
				} else if (v == null && w != null) {
					fail("Matrices to not match");
				} else if (v != null && w == null) {
					fail("Matrices to not match");
				}
				
			}
		}
	}
}