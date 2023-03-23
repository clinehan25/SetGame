package cs3500.set.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Card class.
 */
public class CardTest {

  /**
   * Tests for the getCount method.
   */
  @Test
  public void getCount() {
    Card c1 = new Card(1, "E", "D");
    Card c2 = new Card(3, "F", "O");
    assertEquals(1, c1.getCount());
    assertEquals(3, c2.getCount());
  }

  /**
   * Tests for the getFilling method.
   */
  @Test
  public void getFilling() {
    Card c1 = new Card(1, "E", "D");
    Card c2 = new Card(3, "F", "O");
    assertEquals("E", c1.getFilling());
    assertEquals("F", c2.getFilling());
  }

  /**
   * Tests for the getShape method.
   */
  @Test
  public void getShape() {
    Card c1 = new Card(1, "E", "D");
    Card c2 = new Card(3, "F", "O");
    assertEquals("D", c1.getShape());
    assertEquals("O", c2.getShape());
  }

  /**
   * Tests for the toString method.
   */
  @Test
  public void testToString() {
    Card c1 = new Card(1, "E", "D");
    Card c2 = new Card(3, "F", "O");
    assertEquals("1ED", c1.toString());
    assertEquals("3FO", c2.toString());
  }
}