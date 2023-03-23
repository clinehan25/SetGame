package cs3500.set.view;

import org.junit.Test;

import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests for the SetGameTextView class.
 */
public class SetGameTextViewTest {

  /**
   * Tests for the toString method for three.
   */
  @Test
  public void testToStringThree() {
    SetThreeGameModel m1 = new SetThreeGameModel();
    SetGameTextView v1 = new SetGameTextView(m1);
    assertThrows(IllegalStateException.class, () -> {
      v1.toString();
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals("1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO", v1.toString());
  }

  /**
   * Tests for the toString method for general.
   */
  @Test
  public void testToStringGeneralStandard() {
    SetGameModel m1 = new GeneralSetGameModel();
    SetGameTextView v1 = new SetGameTextView(m1);
    assertThrows(IllegalStateException.class, () -> {
      v1.toString();
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 3, 3);
    assertEquals("1EO 2EO 3EO\n" + "1SO 2SO 3SO\n" + "1FO 2FO 3FO", v1.toString());
  }

  /**
   * Tests for the toString method for general non 3x3.
   */
  @Test
  public void testToStringGeneralNonStandard() {
    SetGameModel m1 = new GeneralSetGameModel();
    SetGameTextView v1 = new SetGameTextView(m1);
    assertThrows(IllegalStateException.class, () -> {
      v1.toString();
    });

    m1.startGameWithDeck(m1.getCompleteDeck(), 5, 2);
    assertEquals("1EO 2EO\n"
            + "3EO 1SO\n"
            + "2SO 3SO\n"
            + "1FO 2FO\n"
            + "3FO 1EQ", v1.toString());
  }
}