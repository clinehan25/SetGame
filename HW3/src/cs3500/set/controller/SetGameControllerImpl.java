package cs3500.set.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * Implementation of Set Game Controller interface.
 */
public class SetGameControllerImpl implements SetGameController {

  private SetGameModel model;
  private SetGameView view;
  private Readable input;

  /**
   * Default constructor for Set Game Controller.
   *
   * @param model the games model
   * @param view the games view
   * @param input th games inputs
   * @throws IllegalArgumentException if any null inputs
   */
  public SetGameControllerImpl(SetGameModel model, SetGameView view, Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Null error.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner s = new Scanner(this.input);

    List<String> inputs = new ArrayList<>();
    boolean hasntStarted = true;

    // while the game hasn't started
    while (hasntStarted) {

      // check for 'q' or 'Q'
      if (s.hasNext("q") || s.hasNext("Q")) {
        try {
          this.view.renderMessage("Game quit!");
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
        try {
          this.view.renderMessage("Score: 0");
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
        return;
      }

      // get first input
      if (s.hasNext()) {
        inputs.add(s.next());
      }

      // get second input
      if (s.hasNext()) {
        inputs.add(s.next());
      }

      if (inputs.size() == 2) {
        try {
          this.model.startGameWithDeck(this.model.getCompleteDeck(),
                  Integer.parseInt(inputs.get(0)), Integer.parseInt(inputs.get(1)));
          hasntStarted = false;
        } catch (NumberFormatException e) {
          try {
            this.view.renderMessage("Invalid height/width. Try again.");
          } catch (IOException e1) {
            throw new IllegalStateException(e1);
          }
          hasntStarted = true;
        } catch (IllegalArgumentException e) {
          try {
            this.view.renderMessage("Invalid height/width. Try again.");
          } catch (IOException e1) {
            throw new IllegalStateException(e1);
          }
          hasntStarted = true;
        }

        // check if game has started
        try {
          this.model.getScore();
          hasntStarted = false;
        } catch (IllegalStateException e) {
          hasntStarted = true;
        }
        inputs.clear();
      }
    }










    while (!this.model.isGameOver()) {
      // 4. render current game state
      try {
        this.view.renderGrid();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }

      // 5. render Score message
      try {
        this.view.renderMessage("Score: " + this.model.getScore());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }

      List<String> temp = new ArrayList<String>();
      boolean validSet = false;

      // until a valid set is found
      while (!validSet) {
        // check for 'q' or 'Q'
        if (s.hasNext("q") || s.hasNext("Q")) {
          try {
            this.view.renderMessage("Game quit!");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          try {
            this.view.renderMessage("State of game when quit:");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          try {
            this.view.renderGrid();
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          try {
            this.view.renderMessage("Score: " + this.model.getScore());
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          return;
        }

        // scan for inputs
        for (int i = 0; i < 6; i++) {
          if (s.hasNext()) {
            temp.add(s.next());
          }
        }

        if (temp.size() == 6) {
          try {
            this.model.claimSet(
                    new Coord(Integer.parseInt(temp.get(0)) - 1,
                            Integer.parseInt(temp.get(1)) - 1),
                    new Coord(Integer.parseInt(temp.get(2)) - 1,
                            Integer.parseInt(temp.get(3)) - 1),
                    new Coord(Integer.parseInt(temp.get(4)) - 1,
                            Integer.parseInt(temp.get(5)) - 1));
            validSet = true;
          } catch (NumberFormatException e) {
            try {
              this.view.renderMessage("Invalid Claim. Try again. Non number inputs.");
            } catch (IOException ex) {
              throw new IllegalStateException(ex);
            }
            validSet = false;
          } catch (IllegalArgumentException ex) {
            try {
              this.view.renderMessage("Invalid Claim. Try again. Invalid Set.");
              System.out.println(temp);
              System.out.println(this.model.getCardAtCoord(1, 0));
              System.out.println(this.model.getCardAtCoord(2, 1));
              System.out.println(this.model.getCardAtCoord(0, 2));
            } catch (IOException exception) {
              throw new IllegalStateException(exception);
            }
            validSet = false;
          }
        }
      }
    }

    // 8. if the game ends (Game over -> score)
    try {
      this.view.renderMessage("Game over!");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    try {
      this.view.renderMessage("Score: " + this.model.getScore());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
