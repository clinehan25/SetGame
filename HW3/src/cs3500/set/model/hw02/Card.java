package cs3500.set.model.hw02;

/**
 * Class representing a Card in the game of Set and their functions.
 *
 */
public class Card {
  public final int count;
  public final String filling;
  public final String shape;

  /**
   * Defualt constructor for Card.
   *
   * @param count count of the card (1, 2, 3)
   * @param filling filling of the card (E, S, F)
   * @param shape shape of the card (O, Q, D)
   * @throws IllegalArgumentException on invalid input
   */
  public Card(int count, String filling, String shape) {
    if (count > 3 || count < 0) {
      throw new IllegalArgumentException("Invalid count");
    }
    this.count = count;

    if (filling.equals("E") || filling.equals("S") || filling.equals("F")) {
      this.filling = filling;
    } else {
      throw new IllegalArgumentException("Invalid filling");
    }

    if (shape.equals("O") || shape.equals("Q") || shape.equals("D")) {
      this.shape = shape;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  /**
   * returns the cards count.
   *
   * @return the cards count
   */
  public int getCount() {
    return this.count;
  }

  /**
   * returns the cards filling.
   *
   * @return the cards filling
   */
  public String getFilling() {
    return this.filling;
  }

  /**
   * returns the cards shape.
   *
   * @return the cards shape
   */
  public String getShape() {
    return this.shape;
  }

  /**
   * Returns the card in String form.
   *
   * @return the card as a String
   */
  public String toString() {
    return this.count + this.filling + this.shape;
  }
}
