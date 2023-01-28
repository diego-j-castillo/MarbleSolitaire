package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a MarbleSolitaire game that is arranged in a triangular layout. Movements can be made
 * diagonally due to the shape of the board, but they still must jump over another Marble to
 * land on an empty slot. The playing area of the board is an equilateral triangle. Marbles in a
 * row are indexed from 0 in a row, with there being r Marble/Empty slots for Row r. The score is
 * represented by the Marbles remaining on the board. Overrides methods from the abstract model to
 * line up with its new guidelines for its grid and its movement.
 */
public class TriangleSolitaireModel extends AMarbleSolitaireModel {
  /**
   * Constructs a basic model with a sideLength of 5 and an empty slot at the top.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Constructs a model with the default size but a custom empty slot.
   *
   * @param sRow row where the empty slot is to be located
   * @param sCol colum where the empty slot is to be located
   * @throws IllegalArgumentException empty placed in invalid slot
   */
  public TriangleSolitaireModel(int sRow, int sCol) {
    super(5, sRow, sCol);
  }

  /**
   * Constructs a model with a custom row count but the empty slot at the top.
   *
   * @param dimensions number of rows for the game
   * @throws IllegalArgumentException given row count is not positive
   */
  public TriangleSolitaireModel(int dimensions) {
    super(dimensions, 0, 0);
  }

  /**
   * Constructs a model with a custom row count and a custom empty slot location.
   *
   * @param dimensions number of rows for the game
   * @param sRow       row where the empty slot is to be located
   * @param sCol       colum where the empty slot is to be located
   * @throws IllegalArgumentException given row count is not positive
   * @throws IllegalArgumentException empty placed in invalid slot
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol) {
    super(dimensions, sRow, sCol);
  }

  /**
   * Creates a board for the game with each Slot correctly given its starting state. Slots in the
   * triangle area of the board are marbles, minus the one designated Empty slot. All other slots
   * are set to an Invalid state.
   *
   * @param sRow row where empty slot is located (from 0 and top)
   * @param sCol column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException location for empty slot would not work
   */
  @Override
  protected SlotState[][] startBoard(int sRow, int sCol) {
    //checks if the empty slot is placed outside the playing triangle or out of bounds
    if (sRow < sCol || sRow >= this.edgeLength || sCol >= this.edgeLength) {
      throw new IllegalArgumentException("invalid location for an empty slot");
    }
    //starts creating the board since we know it is good
    int size = this.edgeLength;
    SlotState[][] starter = new SlotState[size][size];
    //should only assign marbles until there is r marbles for row r
    for (int cRow = 0; cRow < size; cRow = cRow + 1) {
      for (int cCol = 0; cCol < size; cCol = cCol + 1) {
        if (cCol <= cRow) {
          starter[cRow][cCol] = SlotState.Marble;
        } else {
          starter[cRow][cCol] = SlotState.Invalid;
        }
      }
    }
    //sets the desired empty slot as empty
    starter[sRow][sCol] = SlotState.Empty;
    return starter;
  }

  /**
   * Checks if the movement as specified by the coordinates is valid. In this triangular model,
   * movements can be diagonal, along with the option for horizontal movement. This allows for up to
   * 6 potential directions for a marble to move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException slots are not aligned for a horizontal move
   * @throws IllegalArgumentException slots are not aligned for a diagonal move
   */
  @Override
  protected void enforceMoveReqs(int fromRow, int fromCol, int toRow, int toCol) {
    //if this move is horizontal, it is blocked if it does not jump over exactly one marble
    if (fromRow == toRow) {
      if (Math.abs(toCol - fromCol) != 2) {
        throw new IllegalArgumentException("To and From slots must line up correctly");
      }
    } else if (Math.abs(toRow - fromRow) == 2) {
      //^ move is spaced correctly vertically to be diagonal
      if (!(fromCol == toCol || Math.abs(toCol - fromCol) == 2)) {
        //if we aren't either on the same column or two apart, the movement can't be made
        throw new IllegalArgumentException("To and From slots must line up correctly");
      } else if (Math.abs(toCol - fromCol) == 2) {
        //^ reaching here means that columns are either equal ot two apart. Equal cols will always
        //^ work, but 2-apart cols may not work for some directions
        if ((toRow > fromRow && fromCol > toCol) || (fromRow > toRow && toCol > fromCol)) {
          //for moves on the positive slope (down-left, up-right), they must be on the same column,
          //which we check for and block if it does not comply
          throw new IllegalArgumentException("To and From slots must line up correctly");
        }
      }
    } else {
      //means that we are not lined up by row in any good way, so we must have an invalid move
      throw new IllegalArgumentException("To and From slots must line up correctly");
    }
  }

  /**
   * Determines if the Marble at the given spot can jump over another marble. Assumes that
   * the currently selected slot is a Marble. For this triangular model, it not only judges
   * if a marble can move horizontally but diagonally. At most, there are 6 options for a Marble
   * to move.
   *
   * @param mRow row where marble is located
   * @param mCol column where marble is located
   * @return true if marble is jumped over to empty slot, false otherwise
   */
  @Override
  protected boolean canJump(int mRow, int mCol) {
    //left horizontal movement
    if (mCol > 1 && this.getSlotAt(mRow, mCol - 1) == SlotState.Marble
            && this.getSlotAt(mRow, mCol - 2) == SlotState.Empty) {
      return true;
    }
    //right horizontal movement
    if (mCol < this.edgeLength - 2 && this.getSlotAt(mRow, mCol + 1) == SlotState.Marble
            && this.getSlotAt(mRow, mCol + 2) == SlotState.Empty) {
      return true;
    }
    //up-right movement
    if (mRow > 1 && this.getSlotAt(mRow - 1, mCol) == SlotState.Marble
            && this.getSlotAt(mRow - 2, mCol) == SlotState.Empty) {
      return true;
    }
    //down-left movement
    if (mRow < this.edgeLength - 2 && this.getSlotAt(mRow + 1, mCol) == SlotState.Marble
            && this.getSlotAt(mRow + 2, mCol) == SlotState.Empty) {
      return true;
    }
    //up-left movement
    if (mRow > 1 && mCol > 1 && this.getSlotAt(mRow - 1, mCol - 1) == SlotState.Marble
            && this.getSlotAt(mRow - 2, mCol - 2) == SlotState.Empty) {
      return true;
    }
    //down-right movement
    return mRow < this.edgeLength - 2 && mCol < this.edgeLength - 2
            && this.getSlotAt(mRow + 1, mCol + 1) == SlotState.Marble
            && this.getSlotAt(mRow + 2, mCol + 2) == SlotState.Empty;
  }

  /**
   * Returns the longest horizontal line of marbles, which is equal to the edgeLength for a
   * Triangle model.
   */
  @Override
  public int getBoardSize() {
    return this.edgeLength;
  }
}
