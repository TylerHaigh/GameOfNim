package test;

import junit.framework.Assert;
import nim.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TopologySortTest {
  private AdjacencyList initList() {
    return NimAlgorithms.constructNimGraph(5);
  }

  @Test 
  public void testOrdering() {
    AdjacencyList list = initList();
    Assert.assertNotNull(list);

    LinkedList<NimVertex> sorted = NimAlgorithms.labelNimGraph(list);
    
    Assert.assertNotNull(sorted);
    Assert.assertEquals(7, sorted.size());

    /*
      Expected list
      (5,4): losing
      (3,3): winning
      (4,2): winning
      (3,2): losing
      (2,2): winning
      (1,1): winning
      (0,0): losing
     */

    System.out.println("AdjacencyList:");
    System.out.print(list);
    System.out.println();

    System.out.println("Sorted list:");
    for (int i = 0; i < sorted.size(); i++) {
      Vertex v = sorted.get(i);
      if (v != null)
        System.out.print(v.toString() + " ");
    }
    System.out.println();

    Assert.assertNotNull(sorted.get(0));
    Assert.assertEquals(5, sorted.get(0).getSticksRemaining());
    Assert.assertEquals(4, sorted.get(0).getSticksCanTake());
    Assert.assertFalse(sorted.get(0).isWinning());

    Assert.assertNotNull(sorted.get(1));
    Assert.assertEquals(3, sorted.get(1).getSticksRemaining());
    Assert.assertEquals(3, sorted.get(1).getSticksCanTake());
    Assert.assertTrue(sorted.get(1).isWinning());

    Assert.assertNotNull(sorted.get(2));
    Assert.assertEquals(4, sorted.get(2).getSticksRemaining());
    Assert.assertEquals(2, sorted.get(2).getSticksCanTake());
    Assert.assertTrue(sorted.get(2).isWinning());

    Assert.assertNotNull(sorted.get(3));
    Assert.assertEquals(3, sorted.get(3).getSticksRemaining());
    Assert.assertEquals(2, sorted.get(3).getSticksCanTake());
    Assert.assertFalse(sorted.get(3).isWinning());

    Assert.assertNotNull(sorted.get(4));
    Assert.assertEquals(2, sorted.get(4).getSticksRemaining());
    Assert.assertEquals(2, sorted.get(4).getSticksCanTake());
    Assert.assertTrue(sorted.get(4).isWinning());

    Assert.assertNotNull(sorted.get(5));
    Assert.assertEquals(1, sorted.get(5).getSticksRemaining());
    Assert.assertEquals(1, sorted.get(5).getSticksCanTake());
    Assert.assertTrue(sorted.get(5).isWinning());

    Assert.assertNotNull(sorted.get(6));
    Assert.assertEquals(0, sorted.get(6).getSticksRemaining());
    Assert.assertEquals(0, sorted.get(6).getSticksCanTake());
    Assert.assertFalse(sorted.get(6).isWinning());
  }
}