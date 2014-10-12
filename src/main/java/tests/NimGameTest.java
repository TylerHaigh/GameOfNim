package tests;

import java.util.Arrays;

import nim.*;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NimGameTest {

	@Test
	public void testConstructNimMatrix_3Sticks() {
		int numberOfSticks = 3;
		int matrixSize = numberOfSticks + 1;
		NimVertex[][] expectedMatrix = new NimVertex[matrixSize][matrixSize];
		
		expectedMatrix[3][2] = new NimVertex(3,2);
		expectedMatrix[2][2] = new NimVertex(2,2);
		expectedMatrix[1][1] = new NimVertex(1,1);
		expectedMatrix[0][0] = new NimVertex(0,0);
		
		NimGame game = new NimGame(numberOfSticks);
		NimVertex[][] actualMatrix = game.constructNimMatrix();
		
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
