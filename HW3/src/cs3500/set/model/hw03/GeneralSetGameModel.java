package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;

/**
 * Class implementation of a General Game of set not limited to a 3x3.
 */
public class GeneralSetGameModel extends AbstractSetGameModel {

  /**
   * Default Constructor that makes a game simply as Set Three would.
   */
  public GeneralSetGameModel() {
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
      //this.board.get(coord1.row).remove(coord1.col);
      if (!this.deck.isEmpty()) {
        this.board.get(coord1.row).set(coord1.col, this.deck.remove(0));
      } else {
        this.board.get(coord1.row).set(coord1.col, null);
      }

      //this.board.get(coord2.row).set(coord2.col, this.deck.remove(0));
      //this.board.get(coord2.row).remove(coord2.col);
      if (!this.deck.isEmpty()) {
        this.board.get(coord2.row).set(coord2.col, this.deck.remove(0));
      } else {
        this.board.get(coord2.row).set(coord2.col, null);
      }

      //this.board.get(coord3.row).set(coord3.col, this.deck.remove(0));
      //this.board.get(coord3.row).remove(coord3.col);
      if (!this.deck.isEmpty()) {
        this.board.get(coord3.row).set(coord3.col, this.deck.remove(0));
      } else {
        this.board.get(coord3.row).set(coord3.col, null);
      }

      this.score += 1;

      while (this.deck.size() >= this.board.get(0).size() && !this.anySetsPresent()) {
        this.board.add(new ArrayList<Card>());

        while (this.board.get(this.board.size() - 1).size()
                < this.board.get(this.board.size() - 2).size()) {
          this.board.get(this.board.size() - 1).add(this.deck.remove(0));
        }
      }
    } else {
      throw new IllegalArgumentException("Invalid Set.");
    }
  }

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException {
    if (height * width < 3 || deck == null || height <= 0
            || width <= 0 || deck.size() < width * height) {
      throw new IllegalArgumentException("Invalid Parameters");
    }

    this.deck = deck;
    this.board = new ArrayList<List<Card>>(height);
    for (int i = 0; i < height; i++) {
      ArrayList<Card> temp = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        temp.add(deck.remove(0));
      }
      this.board.add(temp);
    }

    this.started = true;

    while (!this.anySetsPresent() && this.deck.size() >= width) {
      ArrayList<Card> temp = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        temp.add(deck.remove(0));
      }
      this.board.add(temp);
    }
  }

  // TODO \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
  @Override
  public boolean isGameOver() {
    if (this.hasStarted()) {
      if ((this.deck.size() == 0
              && (this.board.get(0).contains(null)
              || this.board.get(1).contains(null)
              || this.board.get(2).contains(null)))
              || (this.deck.size() < this.getWidth()
              && !this.anySetsPresent())) {
        return true;
      }
    } else {
      throw new IllegalStateException();
    }
    return false;
  }
}
