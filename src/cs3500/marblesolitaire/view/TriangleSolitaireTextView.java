package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Visual representation of a Triangle Solitaire Game in text format. Visualization is represented
 * as a string, with "O" being Marbles and "_" being Empty. Invalid slots are not visualized.
 * Slots in a row are separated by one space, and the next row begins after the last valid slot of
 * a row is displayed. A new line is not placed after the final valid slot. Every row is centered
 * in such a way to make the board a right isosceles triangle. Extends the common view to implement
 * some common methods.
 */
public class TriangleSolitaireTextView extends AMarbleSolitaireView {
  /**
   * Constructs a text representation of a Triangle Solitaire Game. Output destination defaults to
   * the console.
   *
   * @param model the game being played in its current state
   * @throws IllegalArgumentException provided model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model, System.out);
  }

  /**
   * Constructs a text representation of a Triangle Solitaire Game and specific location to transmit
   * the current state of the model.
   *
   * @param model the game being played in its current state
   * @param out   destination for the view to transmit the image of the model to
   * @throws IllegalArgumentException model or output destination are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable out) {
    super(model, out);
  }

  /**
   * Produces a single row of a model as text. Spaces are added at the beginning to properly align
   * the row based on its height along the triangle. Marbles are "O" and Empty slots are "_", with
   * each being separated by spaces. Row should end directly after the last valid slot of a row.
   *
   * @param row number of that will be output (from 0)
   */
  @Override
  protected String rowAsText(int row) {
    StringBuilder output = new StringBuilder();
    int spaces = this.model.getBoardSize() - row;
    int counter = 1;
    //adds the starting spaces to properly align a row with the rest on the display
    while (counter < spaces) {
      output.append(" ");
      counter = counter + 1;
    }
    //does all the marbles that there should be
    for (int col = 0; col <= row; col = col + 1) {
      if (this.model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
        output.append("O");
      } else {
        output.append("_");
      }
      //only adds spaces if it will separate two slots
      if (col < row) {
        output.append(" ");
      }
    }
    return output.toString();
  }
}
