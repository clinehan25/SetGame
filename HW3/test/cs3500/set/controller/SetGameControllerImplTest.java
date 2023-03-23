package cs3500.set.controller;

import org.junit.Test;

import java.io.StringReader;

import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Set Game Controller and its methods.
 */
public class SetGameControllerImplTest {

  /**
   * Tests quitting a default game immediately.
   */
  @Test
  public void playGameQuitImmediatelyThree() {
    SetGameModel model = new SetThreeGameModel();
    Appendable output = new StringBuilder();
    SetGameView view = new SetGameTextView(model, output);
    Readable input1 = new StringReader("q");
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("Game quit!\n" + "Score: 0\n", output.toString());
  }

  /**
   * Tests quitting a default game immediately.
   */
  @Test
  public void playGameQuitImmediatelyGeneral() {
    Readable input1 = new StringReader("q");
    Appendable output = new StringBuilder();
    SetGameModel model = new GeneralSetGameModel();
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("Game quit!\n" + "Score: 0\n", output.toString());
  }



  /**
   * Tests quitting a default game after start.
   */
  @Test
  public void playGameQuitAfterStartGeneral() {
    Appendable output = new StringBuilder();
    Readable input1 = new StringReader("3 3 q");
    SetGameModel model = new GeneralSetGameModel();
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n" + "Game quit!\n" + "State of game when quit:\n"
            + "1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n", output.toString());
  }

  /**
   * Tests quitting a default game after start.
   */
  @Test
  public void playGameQuitAfterStartThree() {
    Appendable output = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    Readable input1 = new StringReader("3 3 q");
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n" + "Game quit!\n" + "State of game when quit:\n"
            + "1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n", output.toString());
  }



  /**
   * Tests quitting a default game after an invalid start.
   */
  @Test
  public void playGameQuitAfterInvalidStartThree() {
    Appendable output = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    Readable input1 = new StringReader("3 5 q");
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("Invalid height/width. Try again.\n"
            + "Game quit!\n" + "Score: 0\n", output.toString());
  }

  /**
   * Tests quitting a default game after an invalid start.
   */
  @Test
  public void playGameQuitAfterInvalidStartGeneral() {
    Appendable output = new StringBuilder();
    SetGameModel model = new GeneralSetGameModel();
    Readable input1 = new StringReader("9 7 q");
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("Invalid height/width. Try again.\n"
            + "Game quit!\n" + "Score: 0\n", output.toString());
  }



  /**
   * Tests quitting a default game after move.
   */
  @Test
  public void playGameQuitAfterMoveThree() {
    Appendable output = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    Readable input1 = new StringReader("3 3 2 1 3 2 1 3 q");
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("1EO 2EO 3EO\n"
            + "1SO 2SO 3SO\n"
            + "1FO 2FO 3FO\n"
            + "Score: 0\n"
            + "1EO 2EO 3EQ\n"
            + "1EQ 2SO 3SO\n"
            + "1FO 2EQ 3FO\n"
            + "Score: 1\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "1EO 2EO 3EQ\n"
            + "1EQ 2SO 3SO\n"
            + "1FO 2EQ 3FO\n"
            + "Score: 1\n", output.toString());
  }

  /**
   * Tests quitting a default game after move.
   */
  @Test
  public void playGameQuitAfterMoveGeneral() {
    Appendable output = new StringBuilder();
    SetGameModel model = new GeneralSetGameModel();
    Readable input1 = new StringReader("3 3 2 1 3 2 1 3 q");
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("1EO 2EO 3EO\n"
            + "1SO 2SO 3SO\n"
            + "1FO 2FO 3FO\n"
            + "Score: 0\n"
            + "1EO 2EO 3EQ\n"
            + "1EQ 2SO 3SO\n"
            + "1FO 2EQ 3FO\n"
            + "Score: 1\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "1EO 2EO 3EQ\n"
            + "1EQ 2SO 3SO\n"
            + "1FO 2EQ 3FO\n"
            + "Score: 1\n", output.toString());
  }



  /**
   * Tests quitting a default game after invalid move.
   */
  @Test
  public void playGameQuitAfterInvalidMoveThree() {
    Appendable output = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    Readable input1 = new StringReader("3 3 1 1 3 2 1 3 q");
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n" + "Invalid Claim. Try again. Invalid Set.\n"
            + "Game quit!\n" + "State of game when quit:\n"
            + "1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n", output.toString());
  }

  /**
   * Tests quitting a default game after invalid move.
   */
  @Test
  public void playGameQuitAfterInvalidMoveGeneral() {
    Appendable output = new StringBuilder();
    SetGameModel model = new GeneralSetGameModel();
    Readable input1 = new StringReader("3 3 1 1 3 2 1 3 q");
    SetGameView view = new SetGameTextView(model, output);
    SetGameController game1 = new SetGameControllerImpl(model, view, input1);

    game1.playGame();
    assertEquals("1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n" + "Invalid Claim. Try again. Invalid Set.\n"
            + "Game quit!\n" + "State of game when quit:\n"
            + "1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO\n"
            + "Score: 0\n", output.toString());
  }
}