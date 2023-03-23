package cs3500.set.model.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests for the SetThreeGameModel class.
 */
public class SetThreeGameModelTest {

  /**
   * Tests for the claimSet method.
   */
  @Test
  public void claimSet() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(0, 1);
    Coord c3 = new Coord(0, 2);
    assertThrows(IllegalStateException.class, () -> {
      m1.claimSet(c1, c2, c3);
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);

    assertEquals("1EO", m1.getCardAtCoord(c1).toString());
    assertEquals("2EO", m1.getCardAtCoord(c2).toString());
    assertEquals("3EO", m1.getCardAtCoord(c3).toString());
    assertEquals(0, m1.getScore());
    m1.claimSet(c1, c2, c3);
    assertEquals("1EQ", m1.getCardAtCoord(c1).toString());
    assertEquals("2EQ", m1.getCardAtCoord(c2).toString());
    assertEquals("3EQ", m1.getCardAtCoord(c3).toString());
    assertEquals(1, m1.getScore());
  }

  /**
   * Tests for the startGameWithDeck method.
   */
  @Test
  public void startGameWithDeck() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(3, m1.board.size());
    assertEquals(3, m1.board.get(0).size());
    assertEquals(3, m1.board.get(1).size());
    assertEquals(3, m1.board.get(2).size());
    assertEquals(false, m1.board.get(0).isEmpty());
    assertEquals(false, m1.board.get(1).isEmpty());
    assertEquals(false, m1.board.get(2).isEmpty());
  }

  /**
   * Tests for the getWidth method.
   */
  @Test
  public void getWidth() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m1.getWidth();
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(3, m1.getWidth());
  }

  /**
   * Tests for the getHeight method.
   */
  @Test
  public void getHeight() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m1.getHeight();
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(3, m1.getHeight());
  }

  /**
   * Tests for the getScore method.
   */
  @Test
  public void getScore() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m1.getScore();
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(0, m1.getScore());
  }

  /**
   * Tests for the anySetsPresent method.
   */
  @Test
  public void anySetsPresentTest() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m1.anySetsPresent();
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(true, m1.anySetsPresent());
  }

  /**
   * Tests for the isValidSet method.
   */
  @Test
  public void isValidSet() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    Coord c1 = new Coord(0, 1); // 2EO
    Coord c2 = new Coord(2, 1); // 2FO
    Coord c3 = new Coord(2, 2); // 3FO
    Coord c4 = new Coord(1, 0); // 1SO
    Coord c5 = new Coord(0, 0); // 1EO
    Coord c6 = new Coord(0, 2); // 3EO
    Coord invalid = new Coord(-1, 0);

    assertThrows(IllegalStateException.class, () -> {
      m1.isValidSet(c1, c2, c3);
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);

    assertEquals(false, m1.isValidSet(c1, c2, c3));
    assertEquals(false, m1.isValidSet(c1, c2, c4));
    assertEquals(true, m1.isValidSet(c1, c4, c3));
    assertEquals(false, m1.isValidSet(c4, c2, c3));
    assertEquals(true, m1.isValidSet(c1, c5, c6));
  }

  /**
   * Tests for the getCardAtCoord method (int).
   */
  @Test
  public void getCardAtCoord() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m1.getCardAtCoord(0, 1);
    });
    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals("2EO", m1.getCardAtCoord(0, 1).toString());
    assertEquals("2FO", m1.getCardAtCoord(2, 1).toString());
  }

  /**
   * Tests for the getCardAtCoord method (Coord).
   */
  @Test
  public void testGetCardAtCoord() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m1.getCardAtCoord(new Coord(0, 1));
    });
    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals("2EO", m1.getCardAtCoord(new Coord(0, 1)).toString());
    assertEquals("2FO", m1.getCardAtCoord(new Coord(2, 1)).toString());
  }

  /**
   * Tests for the isGameOver method.
   */
  @Test
  public void isGameOver() {
    SetThreeGameModel m = new SetThreeGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m.isGameOver();
    });


    SetThreeGameModel m1 = new SetThreeGameModel();
    Coord c00 = new Coord(0, 0);
    Coord c01 = new Coord(0, 1);
    Coord c02 = new Coord(0, 2);
    Coord c10 = new Coord(1, 0);
    Coord c11 = new Coord(1, 1);
    Coord c12 = new Coord(1, 2);
    Coord c20 = new Coord(2, 0);
    Coord c21 = new Coord(2, 1);
    Coord c22 = new Coord(2, 2);

    List<Card> deck = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
      deck.add(new Card(1, "E", "O"));
    }

    m1.startGameWithDeck(deck, 3, 3);
    assertEquals(false, m1.isGameOver());


    m1.claimSet(c00, c01, c02);

    assertEquals(false, m1.isGameOver());

    // deck empty but still available set
    m1.claimSet(c21, c12, c01);
    System.out.println(m1.board + "!");
    // game over
    assertEquals(true, m1.isGameOver());


  }

  /**
   * Tests for the getCompleteDeck method.
   */
  @Test
  public void getCompleteDeck() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertEquals(27, m1.getCompleteDeck().size());
  }

  /**
   * Tests for isValidSetHelper method.
   */
  @Test
  public void isValidSetHelper() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    Card c1 = new Card(1, "E", "D");
    Card c2 = new Card(3, "F", "O");
    Card c3 = new Card(2, "F", "D");
    Card c4 = new Card(1, "F", "Q");
    assertEquals(true, m1.isValidSetHelper(c2, c3, c4));
    assertEquals(false, m1.isValidSetHelper(c1, c2, c3));

    Card e = new Card(3, "E", "D");
    Card f = new Card(2, "S", "Q");
    Card g = new Card(1, "F", "O");
    assertEquals(true, m1.isValidSetHelper(e, f, g));
  }

  /**
   * Tests for isValidCoord method.
   */
  @Test
  public void isValidCoord() {
    Coord c1 = new Coord(0, 1);
    Coord c2 = new Coord(3, -1);
    SetThreeGameModel m1 = new SetThreeGameModel();
    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(true, m1.isValidCoord(c1));
    assertEquals(false, m1.isValidCoord(c2));
  }

  /**
   * Tests for the hasStarted method.
   */
  @Test
  public void hasStarted() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    assertEquals(false, m1.hasStarted());
    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(true, m1.hasStarted());
  }
}