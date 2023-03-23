package cs3500.set;

import java.io.InputStreamReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * Main class.
 */
public class SetGame {

  /**
   * Main method.
   *
   * @param args arguments
   */
  public static void main(String[] args) {

    System.out.print("Enter your model type: ");
    for (String s : args) {
      if (s.equals("three")) {
        SetThreeGameModel model = new SetThreeGameModel();
        Appendable output = new StringBuilder();
        SetGameView view = new SetGameTextView(model, output);
        Readable input = new InputStreamReader(System.in);
        SetGameController controller = new SetGameControllerImpl(model, view, input);
        controller.playGame();
      } else if (s.equals("general")) {
        GeneralSetGameModel model = new GeneralSetGameModel();
        Appendable output = new StringBuilder();
        SetGameView view = new SetGameTextView(model, output);
        Readable input = new InputStreamReader(System.in);
        SetGameController controller = new SetGameControllerImpl(model, view, input);
        controller.playGame();
      }
    }
  }
}
