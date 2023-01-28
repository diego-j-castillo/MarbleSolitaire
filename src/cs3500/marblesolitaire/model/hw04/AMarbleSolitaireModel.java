package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstraction for a MarbleSolitaireModel interface. Methods implemented in this abstract class
 * are primarily for models that use a standard rectangular grid. Any models that do not use this
 * grid type should override the methods provided as needed.
 */
public abstract class AMarbleSolitaireModel implements MarbleSolitaireModel {
  protected final int edgeLength;
  protected SlotState[][] board;

  /**
   * Constructs an abstract solitaire model with an edgeLength and a board as a 2D SlotState array.
   *
   * @param eLength length of the outer edges of the board
   * @param sRow    row selected for the empty slot
   * @param sCol    column selected for the empty slot
   * @throws IllegalArgumentException given edgeLength is negative
   */
  public AMarbleSolitaireModel(int eLength, int sRow, int sCol) {
    if (eLength < 1) {
      throw new IllegalArgumentException("non-positive edge length is not possible for game");
    }
    this.edgeLength = eLength;
    this.board = this.startBoard(sRow, sCol);
  }

  /**
   * Creates a board for the game with each Slot correctly given its starting state. Slots in the
   * playing area of the board are marbles, minus the one designated Empty slot. All other slots
   * are set to an Invalid state. This method also checks if the parameters for the board do not
   * align with the criteria for the specific implementation.
   *
   * @param sRow row where empty slot is located (from 0 and top)
   * @param sCol column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException board does not follow the restriction of the model
   */
  protected abstract SlotState[][] startBoard(int sRow, int sCol) throws IllegalArgumentException;

  /**
   * Checks if movement is valid first and then makes the move. Validity of the move depends on the
   * specific implementation. Only widespread rules are that the move must begin from a marble, end
   * on an empty slot, and jump over a marble. The total amount of marbles after a successful
   * movement is one less than before the movement was made.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException board does not meet implementation requirements
   * @throws IllegalArgumentException move does not start at a Marble
   * @throws IllegalArgumentException move does not jump over a Marble
   * @throws IllegalArgumentException move does not end on an Empty slot
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;
    //now checks if the movement can be made
    this.enforceMoveReqs(fromRow, fromCol, toRow, toCol);
    //now enforces the general requirements
    if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Movement must start from a marble");
    }
    if (this.getSlotAt(midRow, midCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Movement must jump over a marble");
    }
    if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("Movement must end on an empty slot");
    }
    //move must be valid if it passed all the previous lines without an exception being thrown
    //empties the starting and middle slot
    this.board[fromRow][fromCol] = SlotState.Empty;
    this.board[midRow][midCol] = SlotState.Empty;
    //fills the jumped-to slot with a marble
    this.board[toRow][toCol] = SlotState.Marble;
  }

  /**
   * Checks if the movement as specified by the coordinates is valid. In a rectangular and standard
   * board type, a move is valid if it aligns on only one axis, with one slot in between.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException movement does not abide by implementation restrictions
   */
  protected void enforceMoveReqs(int fromRow, int fromCol, int toRow, int toCol) {
    if ((Math.abs(fromRow - toRow) != 2 && Math.abs(fromCol - toCol) != 2)
            || (fromCol != toCol && fromRow != toRow)) {
      throw new IllegalArgumentException("To and From slots must align with a slot in between");
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made. A move needs a Marble to jump over another Marble to land
   * on an Empty slot in a rectangular grid. Should a rectangular grid not be used,
   * the implementation must specify the new guidelines for which a Marble can jump.
   *
   * @return true if no moves can be made, false otherwise
   */
  @Override
  public boolean isGameOver() {
    for (int cRow = 0; cRow < this.getBoardSize(); cRow = cRow + 1) {
      for (int cCol = 0; cCol < this.getBoardSize(); cCol = cCol + 1) {
        if (this.getSlotAt(cRow, cCol) == SlotState.Marble) {
          if (this.canJump(cRow, cCol)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Determines if the Marble at the given spot can jump over another marble. Assumes that
   * the currently selected slot is a Marble. Further/fewer restrictions on movement based on
   * specific implementations. Default restrictions are for models that employ a standard
   * rectangular grid.
   *
   * @param mRow row where marble is located
   * @param mCol column where marble is located
   * @return true if marble is jumped over to empty slot, false otherwise
   */
  protected boolean canJump(int mRow, int mCol) {
    //down movement
    if (mRow < this.getBoardSize() - 2 && this.getSlotAt(mRow + 1, mCol) == SlotState.Marble
            && this.getSlotAt(mRow + 2, mCol) == SlotState.Empty) {
      return true;
    }
    //up movement
    if (mRow > 1 && this.getSlotAt(mRow - 1, mCol) == SlotState.Marble
            && this.getSlotAt(mRow - 2, mCol) == SlotState.Empty) {
      return true;
    }
    //right movement
    if (mCol < this.getBoardSize() - 2 && this.getSlotAt(mRow, mCol + 1) == SlotState.Marble
            && this.getSlotAt(mRow, mCol + 2) == SlotState.Empty) {
      return true;
    }
    //left movement
    return mCol > 1 && this.getSlotAt(mRow, mCol - 1) == SlotState.Marble
            && this.getSlotAt(mRow, mCol - 2) == SlotState.Empty;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board. The
   * specific calculation of the length depends on the specific implementation of the model. Default
   * is 2 less than triple the edge length.
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.edgeLength * 3 - 2;
  }

  /**
   * Returns the state of the slot at the given position. Can be Marble, Empty, or Invalid.
   * Numbering of rows and columns begins at 0. Rows go from top to bottom. Columns go from left
   * to right.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return state of the slot at the row and column
   * @throws IllegalArgumentException Slot selected is out of bounds
   */
  @Override
  public SlotState getSlotAt(int row, int col) {
    if (row < 0 || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("selected slot is outside the board");
    }
    return this.board[row][col];
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int score = 0;
    for (SlotState[] stateRow : this.board) {
      for (SlotState ss : stateRow) {
        if (ss == SlotState.Marble) {
          score = score + 1;
        }
      }
    }
    return score;
  }
}
