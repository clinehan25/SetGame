package cs3500.set.model.hw02;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw03.AbstractSetGameModel;

/**
 * Implementation of the SetGameModel and its methods.
 */
public class SetThreeGameModel extends AbstractSetGameModel {

  /**
   * Default Constructor for SetThreeGameModel class.
   */
  public SetThreeGameModel() {
    super();
    //this.score = 0;
    //this.deck = new ArrayList<>();
    //this.board = new ArrayList<List<Card>>();
    //this.started = false;
  }

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {
    if (this.board == null || !this.hasStarted()) {
      throw new IllegalStateException();
    }

    if (this.isValidSet(coord1, coord2, coord3)) {
      //this.board.get(coord1.row).set(coord1.col, this.deck.remove(0));
      if (!this.deck.isEmpty()) {
        this.board.get(coord1.row).set(coord1.col, this.deck.remove(0));
      } else {
        this.board.get(coord1.row).set(coord1.col, null);
      }

      //this.board.get(coord2.row).set(coord2.col, this.deck.remove(0));
      if (!this.deck.isEmpty()) {
        this.board.get(coord2.row).set(coord2.col, this.deck.remove(0));
      } else {
        this.board.get(coord2.row).set(coord2.col, null);
      }

      //this.board.get(coord3.row).set(coord3.col, this.deck.remove(0));
      if (!this.deck.isEmpty()) {
        this.board.get(coord3.row).set(coord3.col, this.deck.remove(0));
      } else {
        this.board.get(coord3.row).set(coord3.col, null);
      }

      this.score += 1;
    } else {
      throw new IllegalArgumentException("Invalid Set.");
    }
  }

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException {
    if (deck == null || deck.size() < 9 || height != 3 || width != 3) {
      throw new IllegalArgumentException("Invalid Parameters");
    }

    this.deck = deck;
    this.board = new ArrayList<List<Card>>(height);
    for (int i = 0; i < height; i++) {
      ArrayList<Card> temp = new ArrayList<>();
      temp.add(deck.remove(0));
      temp.add(deck.remove(0));
      temp.add(deck.remove(0));
      this.board.add(temp);
    }
    this.started = true;
  }

  @Override
  public boolean isGameOver() {
    if (this.hasStarted()) {
      if (this.deck.size() == 0
              && (this.board.get(0).contains(null)
              || this.board.get(1).contains(null)
              || this.board.get(2).contains(null))) {
        return true;
      }
    } else {
      throw new IllegalStateException();
    }
    return false;
  }
}
