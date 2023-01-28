import java.io.IOException;
import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Mock model to test that the inputs passed into the controller are being passed correctly
 * into the model. Methods simply write to an Appendable object and return a default value if
 * needed.
 */
public class MockSolitaireModel implements MarbleSolitaireModel {
  //appendable to which we write out to and read values from in testing.
  private final Appendable log;

  /**
   * Constructs a MockSolitaireModel with a given Appendable.
   *
   * @param log object to which information about input is written to
   */
  public MockSolitaireModel(Appendable log) {
    this.log = Objects.requireNonNull(log);
  }

  //handles appending to the log
  private void logTransmit(String msg) {
    try {
      this.log.append(msg);
    } catch (IOException e) {
      throw new RuntimeException("Could not properly append to log.");
    }
  }

  /**
   * Appends the parameters of the function to the appendable object.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    this.logTransmit(String.format("fromRow = %d fromCol = %d toRow = %d toCol = %d\n",
            fromRow, fromCol, toRow, toCol));
  }

  /**
   * Should make it so controller only quits game by manually inputting "q" or "Q". Writes to the
   * log that the status of the game was checked.
   *
   * @return false in any case and at any point
   */
  @Override
  public boolean isGameOver() {
    this.logTransmit("Checked if game was over.\n");
    return false;
  }

  /**
   * Since there is no board, there is size. Writes to the log that the size of hte board was
   * checked.
   *
   * @return 0 since there is no board in the mock.
   */
  @Override
  public int getBoardSize() {
    this.logTransmit("Got board size.\n");
    return 0;
  }

  /**
   * Returns Invalid since we have no board. Writes to log that the state for a row was meant to
   * be returned, including the row and column of the slot.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return Invalid state as a placeholder
   */
  @Override
  public SlotState getSlotAt(int row, int col) {
    this.logTransmit(String.format("Got SlotState at (%d, %d)\n", row, col));
    return SlotState.Empty;
  }

  /**
   * Returns a blank value since this is a stub method. Appends to log that the score was checked.
   *
   * @return 0 since there are no marbles
   */
  @Override
  public int getScore() {
    this.logTransmit("Got score.\n");
    return 0;
  }
}
