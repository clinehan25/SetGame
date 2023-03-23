package cs3500.set.controller;

/**
 * Interface for the Set Game Controller and its methods.
 */
public interface SetGameController {

  /**
   * Starts the game of Set through the controller.
   *
   * @throws IllegalStateException if invalid input/output
   */
  void playGame() throws IllegalStateException;
}
