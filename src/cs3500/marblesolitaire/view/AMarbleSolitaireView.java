package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstraction for the MarbleSolitaireView interface. This abstract class is primarily for
 * implementations that output the model input as text, and can append it to a given Appendable
 * object.
 */
public abstract class AMarbleSolitaireView implements MarbleSolitaireView {
  // A model for a game of MarbleSolitaire to be displayed as text.
  protected final MarbleSolitaireModelState model;
  //object to which the view writes the representation of the model to.
  protected final Appendable out;

  /**
   * Constructs the abstract view with a model and an object to append to.
   *
   * @param model marble solitaire model that is to be viewed
   * @param out   destination to which board and messages are appended to
   * @throws IllegalArgumentException model or output destination are null
   */
  public AMarbleSolitaireView(MarbleSolitaireModelState model, Appendable out) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("given fields can not be null");
    }
    this.model = model;
    this.out = out;
  }

  /**
   * Puts out the model in text format. "O" represents a marble, "_" represents an
   * empty slot, and " " represents an invalid space. Each line ends when the last marble
   * or empty slot for that row is displayed. Each slot of any type is separated by a space.
   * Last Marble/Empty slot of the bottom row is not followed by spaces or a new line.
   *
   * @return current state of the game as a string
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    //goes down each row and does the output for it
    for (int cRow = 0; cRow < this.model.getBoardSize(); cRow = cRow + 1) {
      output.append(this.rowAsText(cRow));
      //checks that we are not on the last row to add a new line "\n"
      if (cRow < this.model.getBoardSize() - 1) {
        output.append("\n");
      }
    }
    //returns final board
    return output.toString();
  }

  /**
   * Produces a single row of a model as text. The specifics as to how the row will be formatted
   * depends on the actual implementation.
   *
   * @param row number of that will be output (from 0)
   */
  protected abstract String rowAsText(int row);

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    this.out.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
