package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Visual representation of a Marble Solitaire Game in text format. Visualization is represented as
 * a string, with "O" being Marbles, "_" being Empty, and " " being Invalid. Slots in a row are
 * separated by one space, and the next row begins after the last valid slot of a row is displayed.
 * A new line is not placed after the final valid slot. Extend the abstract view to implement some
 * common methods.
 */
public class MarbleSolitaireTextView extends AMarbleSolitaireView {
  /**
   * Constructs a text representation of a Marble Solitaire Game. Output destination defaults to
   * the console.
   *
   * @param model the game being played in its current state
   * @throws IllegalArgumentException provided model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model, System.out);
  }

  /**
   * Constructs a text representation of a Marble Solitaire Game and specific location to transmit
   * the current state of the model.
   *
   * @param model the game being played in its current state
   * @param out   destination for the view to transmit the image of the model to
   * @throws IllegalArgumentException model or output destination are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable out) {
    super(model, out);
  }

  /**
   * Produces one row of the model as a String. Marbles are "O", Empty is "_", and Invalid is
   * " ". The row ends once we have reached the last marble or empty of the row.
   *
   * @param row from 0, the row of the model to output
   * @return specified row of the model as a string
   */
  @Override
  protected String rowAsText(int row) {
    StringBuilder output = new StringBuilder();
    int lastValid = 0;
    //finds which slot in the row is the last one to not be invalid
    for (int c = 0; c < this.model.getBoardSize(); c = c + 1) {
      if (this.model.getSlotAt(row, c) != MarbleSolitaireModelState.SlotState.Invalid) {
        lastValid = c;
      }
    }
    //prints all slots up to the last valid one, inclusive
    for (int depth = 0; depth <= lastValid; depth = depth + 1) {
      switch (this.model.getSlotAt(row, depth)) {
        case Marble:
          output.append("O");
          break;
        case Empty:
          output.append("_");
          break;
        default:
          //for invalid
          output.append(" ");
          break;
      }
      //checks to only add a space when not on the last slot
      if (depth < lastValid) {
        output.append(" ");
      }
    }
    //returns the entire row
    return output.toString();
  }


}
