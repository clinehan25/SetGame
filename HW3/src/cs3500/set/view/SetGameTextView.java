package cs3500.set.view;

import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;

/**
 * Implementation of the SetGameView and its methods.
 */
public class SetGameTextView implements SetGameView {

  private SetGameModelState model;

  private Appendable out;

  /**
   * Defualt constructor for SetGameTextView.
   *
   * @param model the views model
   * @throws IllegalArgumentException if model is null
   */
  public SetGameTextView(SetGameModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Null model.");
    }
    this.model = model;
    this.out = System.out;
  }

  /**
   * Constructor that takes in an appendable with the model.
   *
   * @param model the views model
   * @param out the views input
   * @throws IllegalArgumentException if inputs are null
   */
  public SetGameTextView(SetGameModelState model, Appendable out) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Null model.");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {
    String ret = "";
    for (int i = 0; i < this.model.getHeight(); i++) {
      for (int j = 0; j < this.model.getWidth(); j++) {
        ret += this.model.getCardAtCoord(i, j).toString();
        if (j < this.model.getWidth() - 1) {
          ret += " ";
        }
      }
      if (i < this.model.getHeight() - 1) {
        ret += "\n";
      }
    }
    return ret;
  }

  @Override
  public void renderGrid() throws IOException {
    try {
      this.out.append(this.toString() + "\n");
    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.out.append(message + "\n");
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}
