package cs3500.set.model.hw03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests for the GeneralSetGameModel class and its methods.
 */
public class GeneralSetGameModelTest {

  /**
   * Tests for the hasStarted method.
   */
  @Test
  public void hasStarted() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();
    assertEquals(false, m1.hasStarted());
    m1.startGameWithDeck(m1.getCompleteDeck(), 2, 4);
    assertEquals(true, m1.hasStarted());
  }

  /**
   * Tests for the claimSet method.
   */
  @Test
  public void claimSet() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();
    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(0, 1);
    Coord c3 = new Coord(1, 0);
    Coord c4 = new Coord(1, 1);
    Coord c5 = new Coord(2, 0);
    Coord c6 = new Coord(2, 1);
    Coord c7 = new Coord(3, 0);
    Coord c8 = new Coord(3, 1);

    ArrayList<Card> deck = new ArrayList<Card>();
    // set
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "O"));
    deck.add(new Card(3, "E", "O"));
    // deck after set
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "Q"));
    deck.add(new Card(3, "S", "Q"));
    // new row (still no set)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(2, "E", "O"));
    // second row (set present)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(3, "F", "D"));


    // tests for game start
    assertThrows(IllegalStateException.class, () -> {
      m1.claimSet(c1, c2, c3);
    });

    m1.startGameWithDeck(deck, 2, 2);

    // tests for invalid set
    assertThrows(IllegalArgumentException.class, () -> {
      m1.claimSet(c1, c2, c4);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      m1.claimSet(c1, c4, c3);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      m1.claimSet(c4, c2, c3);
    });

    // test claim valid set
    assertEquals("1EO", m1.getCardAtCoord(c1).toString());
    assertEquals("2EO", m1.getCardAtCoord(c2).toString());
    assertEquals("3EO", m1.getCardAtCoord(c3).toString());
    assertEquals(0, m1.getScore());
    assertEquals(2, m1.getHeight());
    m1.claimSet(c1, c2, c3);
    assertEquals("1EO", m1.getCardAtCoord(c1).toString());
    assertEquals("2EQ", m1.getCardAtCoord(c2).toString());
    assertEquals("3SQ", m1.getCardAtCoord(c3).toString());
    assertEquals(1, m1.getScore());

    // test for new rows (no sets present in first addition)
    assertEquals(4, m1.getHeight());
    // first row (still no set)
    assertEquals("1SO", m1.getCardAtCoord(c5).toString());
    assertEquals("2EO", m1.getCardAtCoord(c6).toString());
    // second row (set present)
    assertEquals("1SO", m1.getCardAtCoord(c7).toString());
    assertEquals("3FD", m1.getCardAtCoord(c8).toString());

    // final claim before game over
    assertEquals("1SO", m1.getCardAtCoord(c4).toString());
    assertEquals("1SO", m1.getCardAtCoord(c5).toString());
    assertEquals("1SO", m1.getCardAtCoord(c7).toString());
    m1.claimSet(c4, c5, c7);
    assertEquals(true, m1.isGameOver());
  }

  /**
   * Tests for the startGameWithDeck method.
   */
  @Test
  public void startGameWithDeck() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();

    // tests for exceptions
    assertThrows(IllegalArgumentException.class, () -> {
      m1.startGameWithDeck(m1.getCompleteDeck(), 1, 1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      m1.startGameWithDeck(m1.getCompleteDeck(), -1, 3);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      m1.startGameWithDeck(m1.getCompleteDeck(), 3, -1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      m1.startGameWithDeck(null, 3, 3);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      m1.startGameWithDeck(m1.getCompleteDeck(), 20, 20);
    });

    // start with valid set
    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals(3, m1.board.size());
    assertEquals(3, m1.board.get(0).size());
    assertEquals(3, m1.board.get(1).size());
    assertEquals(3, m1.board.get(2).size());
    assertEquals(false, m1.board.get(0).isEmpty());
    assertEquals(false, m1.board.get(1).isEmpty());
    assertEquals(false, m1.board.get(2).isEmpty());

    // test for adding row (no valid set)
    ArrayList<Card> deck = new ArrayList<Card>();
    // deck (no set)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "Q"));
    deck.add(new Card(3, "S", "Q"));
    // new row (still no set, game should end)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(2, "E", "O"));

    // row added because no sets, still no sets and deck out so game ends
    GeneralSetGameModel m2 = new GeneralSetGameModel();
    m2.startGameWithDeck(deck, 2, 2);
    assertEquals(3, m2.board.size());
    assertEquals(2, m2.board.get(0).size());
    assertEquals(2, m2.board.get(1).size());
    assertEquals(2, m2.board.get(2).size());
    assertEquals(false, m2.board.get(0).isEmpty());
    assertEquals(false, m2.board.get(1).isEmpty());
    assertEquals(false, m2.board.get(2).isEmpty());
    assertEquals(true, m2.isGameOver());
  }

  /**
   * Tests for the getWidth method.
   */
  @Test
  public void getWidth() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();
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
    GeneralSetGameModel m1 = new GeneralSetGameModel();
    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(0, 1);
    Coord c3 = new Coord(1, 0);

    ArrayList<Card> deck = new ArrayList<Card>();
    // set
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "O"));
    deck.add(new Card(3, "E", "O"));
    // deck after set
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "Q"));
    deck.add(new Card(3, "S", "Q"));
    // new row (still no set)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(2, "E", "O"));
    // second row (set present)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(3, "F", "D"));


    // tests for game start
    assertThrows(IllegalStateException.class, () -> {
      m1.getHeight();
    });

    m1.startGameWithDeck(deck, 2, 2);

    // test height
    assertEquals(2, m1.getHeight());

    m1.claimSet(c1, c2, c3);

    // test new height
    assertEquals(4, m1.getHeight());
  }

  /**
   * Tests for the getScore method.
   */
  @Test
  public void getScore() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();
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
  public void anySetsPresent() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();
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
    GeneralSetGameModel m1 = new GeneralSetGameModel();
    Coord c1 = new Coord(0, 1); // 2EO
    Coord c2 = new Coord(2, 1); // 2FO
    Coord c3 = new Coord(2, 2); // 3FO
    Coord c4 = new Coord(1, 0); // 1SO
    Coord c5 = new Coord(0, 0); // 1EO
    Coord c6 = new Coord(0, 2); // 3EO

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
    GeneralSetGameModel m1 = new GeneralSetGameModel();
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
    GeneralSetGameModel m1 = new GeneralSetGameModel();
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
  // TODO \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
  public void isGameOver() {
    GeneralSetGameModel m = new GeneralSetGameModel();
    assertThrows(IllegalStateException.class, () -> {
      m.isGameOver();
    });


    GeneralSetGameModel m1 = new GeneralSetGameModel();
    Coord c00 = new Coord(0, 0);
    Coord c01 = new Coord(0, 1);
    Coord c02 = new Coord(0, 2);
    Coord c10 = new Coord(1, 0);
    Coord c11 = new Coord(1, 1);
    Coord c12 = new Coord(1, 2);
    Coord c20 = new Coord(2, 0);
    Coord c21 = new Coord(2, 1);
    Coord c22 = new Coord(2, 2);
    Coord c30 = new Coord(3, 0);

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
    // game over
    assertEquals(true, m1.isGameOver());

    // new test for end on new row without enough remaining cards
    ArrayList<Card> deck2 = new ArrayList<Card>();
    // deck
    deck2.add(new Card(1, "S", "O"));
    deck2.add(new Card(1, "E", "O"));
    deck2.add(new Card(2, "E", "Q"));
    deck2.add(new Card(3, "S", "Q"));
    // new row (still no set, game should end)
    deck2.add(new Card(1, "S", "O"));
    deck2.add(new Card(2, "E", "O"));

    // game is made and row added because no sets, still no sets and deck is empty so game ends
    GeneralSetGameModel m2 = new GeneralSetGameModel();
    m2.startGameWithDeck(deck2, 2, 2);
    assertEquals(true, m2.isGameOver());


    // game ends after claim cant add new row
    GeneralSetGameModel m3 = new GeneralSetGameModel();
    ArrayList<Card> deck3 = new ArrayList<Card>();
    // set
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "O"));
    deck.add(new Card(3, "E", "O"));
    // deck after set
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "Q"));
    deck.add(new Card(3, "S", "Q"));
    // new row (still no set)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(2, "E", "O"));
    // second row (set present)
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(3, "F", "D"));


    m3.startGameWithDeck(deck, 2, 2);


    m3.claimSet(c00, c01, c10);
    m3.claimSet(c11, c20, c30);
    assertEquals(true, m3.isGameOver());

  }

  /**
   * Tests for the getCompleteDeck method.
   */
  @Test
  public void getCompleteDeck() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();
    assertEquals(27, m1.getCompleteDeck().size());
  }

  /**
   * Tests for isValidSetHelper method.
   */
  @Test
  public void isValidSetHelper() {
    GeneralSetGameModel m1 = new GeneralSetGameModel();
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
    GeneralSetGameModel m1 = new GeneralSetGameModel();
    m1.startGameWithDeck(m1.getCompleteDeck(), 4, 4);
    assertEquals(true, m1.isValidCoord(c1));
    assertEquals(false, m1.isValidCoord(c2));
  }
}