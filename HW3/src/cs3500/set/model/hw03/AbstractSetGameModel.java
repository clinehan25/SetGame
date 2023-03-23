package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;

/**
 * Abstract Set Game Model class and its methods.
 */
public abstract class AbstractSetGameModel implements SetGameModel<Card> {

  public List<List<Card>> board;
  protected List<Card> deck;
  protected int score;
  protected boolean started;

  /**
   * Default Constructor that makes a game simply as Set Three would.
   */
  public AbstractSetGameModel() {
    this.score = 0;
    this.deck = new ArrayList<>();
    this.board = new ArrayList<List<Card>>();
    this.started = false;
  }

  /**
   * Returns if the game has started or not.
   *
   * @return whether the game has started.
   */
  public boolean hasStarted() {
    return this.started;
  }

  @Override
  public int getWidth() throws IllegalStateException {
    if (this.board == null || !this.hasStarted()) {
      throw new IllegalStateException();
    }
    return this.board.get(0).size();
  }

  @Override
  public int getHeight() throws IllegalStateException {
    if (this.board == null || !this.hasStarted()) {
      throw new IllegalStateException();
    }
    return this.board.size();
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (this.board == null || !this.hasStarted()) {
      throw new IllegalStateException(String.valueOf(this.hasStarted()));
    }
    return this.score;
  }

  @Override
  public boolean anySetsPresent() {
    if (this.board == null || !this.hasStarted()) {
      throw new IllegalStateException();
    }

    List<Card> temp = new ArrayList<>();
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        temp.add(this.getCardAtCoord(i, j));
      }
    }
    //System.out.println(temp);

    for (int i = 0; i < temp.size(); i++) {
      for (int j = 1; j < temp.size(); j++) {
        for (int k = 2; k < temp.size(); k++) {
          //Coord c1 = new Coord((int)(i / 3), i % 3);
          //Coord c2 = new Coord((int)(j / 3), j % 3);
          //Coord c3 = new Coord((int)(k / 3), k % 3);
          if (this.isValidSetHelper(temp.get(i), temp.get(j), temp.get(k))
                  && i != j && j != k && i != k) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3)
          throws IllegalArgumentException {
    if (this.isValidCoord(coord1) && this.isValidCoord(coord2) && this.isValidCoord(coord3)) {
      Card c1 = this.getCardAtCoord(coord1);
      Card c2 = this.getCardAtCoord(coord2);
      Card c3 = this.getCardAtCoord(coord3);
      return this.isValidSetHelper(c1, c2, c3);
    }
    return false;
  }


  /**
   * Helper for isValidSet that checks if a coordinate is valid.
   */
  public boolean isValidCoord(Coord c1) {
    return (c1.row >= 0 && c1.row <= this.getHeight() && c1.col >= 0 && c1.col <= this.getWidth());
  }

  /**
   * Helper for isValidSet that checks if 3 cards are a set.
   *
   * @param c1 first card
   * @param c2 second card
   * @param c3 third card
   * @return whether the cards are a set.
   */
  public boolean isValidSetHelper(Card c1, Card c2, Card c3) {
    if (c1.getShape().equals(c2.getShape()) && c2.getShape().equals(c3.getShape())
            || (!c1.getShape().equals(c2.getShape()) && !c2.getShape().equals(c3.getShape())
            && !c3.getShape().equals(c1.getShape()))) {
      if ((c1.getFilling().equals(c2.getFilling()) && c2.getFilling().equals(c3.getFilling()))
              || (!c1.getFilling().equals(c2.getFilling())
              && !c2.getFilling().equals(c3.getFilling())
              && !c3.getFilling().equals(c1.getFilling()))) {
        if (c1.getCount() == c2.getCount() && c2.getCount() == c3.getCount()
                || (c1.getCount() != c2.getCount() && c2.getCount() != c3.getCount()
                && c3.getCount() != c1.getCount())) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public Card getCardAtCoord(int row, int col) {
    if (this.board == null || !this.hasStarted()) {
      throw new IllegalStateException();
    }

    return this.board.get(row).get(col);
  }

  @Override
  public Card getCardAtCoord(Coord coord) {
    if (this.board == null || !this.hasStarted()) {
      throw new IllegalStateException();
    }

    return this.board.get(coord.row).get(coord.col);
  }

  @Override
  public List<Card> getCompleteDeck() {
    List<Card> deck = new ArrayList<>();
    deck.add(new Card(1, "E", "O"));
    deck.add(new Card(2, "E", "O"));
    deck.add(new Card(3, "E", "O"));
    deck.add(new Card(1, "S", "O"));
    deck.add(new Card(2, "S", "O"));
    deck.add(new Card(3, "S", "O"));
    deck.add(new Card(1, "F", "O"));
    deck.add(new Card(2, "F", "O"));
    deck.add(new Card(3, "F", "O"));
    deck.add(new Card(1, "E", "Q"));
    deck.add(new Card(2, "E", "Q"));
    deck.add(new Card(3, "E", "Q"));
    deck.add(new Card(1, "S", "Q"));
    deck.add(new Card(2, "S", "Q"));
    deck.add(new Card(3, "S", "Q"));
    deck.add(new Card(1, "F", "Q"));
    deck.add(new Card(2, "F", "Q"));
    deck.add(new Card(3, "F", "Q"));
    deck.add(new Card(1, "E", "D"));
    deck.add(new Card(2, "E", "D"));
    deck.add(new Card(3, "E", "D"));
    deck.add(new Card(1, "S", "D"));
    deck.add(new Card(2, "S", "D"));
    deck.add(new Card(3, "S", "D"));
    deck.add(new Card(1, "F", "D"));
    deck.add(new Card(2, "F", "D"));
    deck.add(new Card(3, "F", "D"));
    return deck;
  }

  @Override
  public abstract void claimSet(Coord coord1, Coord coord2, Coord coord3);

  @Override
  public abstract void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException;

  @Override
  public abstract boolean isGameOver();
}
