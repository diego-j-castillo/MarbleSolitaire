package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a Marble Solitaire game in the European Style. The board is a square, with slots that
 * are on the outside of the center octagon being Invalid. The size of an octagon is defined by the
 * size of each of its sides, which must be odd for this model. Slots on the inside octagon
 * either contain a Marble or are Empty. Movements must jump over a Marble and to an Empty slot,
 * which removes the jumped-over Marble. Game ends when no more movements can be made,
 * and the score is the current number of Marbles on the board. Extends the abstract model to use
 * methods that are common amongst rectangular-grid-type models.
 */
public class EuropeanSolitaireModel extends AMarbleSolitaireModel {

  /**
   * Constructs a default game with a sideLength 3 and an empty center slot.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs a basic game of sideLength 3, but chooses a certain place for the empty slot.
   *
   * @param sRow row where empty slot is located (from 0 and top)
   * @param sCol column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException empty placed in invalid slot
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(3, sRow, sCol);
  }

  /**
   * Constructs a game of custom sideLength, with the center as the empty slot.
   *
   * @param sLength thickness of each of the eight side
   * @throws IllegalArgumentException thickness not positive odd number
   */
  public EuropeanSolitaireModel(int sLength) {
    super(sLength, sLength * 3 / 2 - 1, sLength * 3 / 2 - 1);
  }

  /**
   * Constructs a game with a custom sideLength and empty slot location.
   *
   * @param sLength size of each of the eight sides
   * @param sRow    row where empty slot is located (from 0 and top)
   * @param sCol    column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException thickness not positive odd number
   * @throws IllegalArgumentException empty placed in invalid slot
   */
  public EuropeanSolitaireModel(int sLength, int sRow, int sCol) {
    super(sLength, sRow, sCol);
  }

  /**
   * Creates a board for the game with each Slot correctly given its starting state. Slots in the
   * octagonal area of the board are marbles, minus the one designated Empty slot. All other slots
   * are set to an Invalid state. Does not allow for construction of the board if the size length
   * is even or if the empty is not placed in the center octagon.
   *
   * @param sRow row where empty slot is located (from 0 and top)
   * @param sCol column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException side length of the board is even
   * @throws IllegalArgumentException place for the empty slot is not in the octagon
   */
  @Override
  protected SlotState[][] startBoard(int sRow, int sCol) {
    //side length is even, so a proper board can't be made
    if (this.edgeLength % 2 == 0) {
      throw new IllegalArgumentException("side length must be even for this model");
    }
    //begins forming the board since the side lengths are valid
    int size = this.getBoardSize();
    SlotState[][] starter = new SlotState[size][size];
    //represent the current row and slot we are on
    int cRow = 0;
    int cCol;
    //build the top part of the octagon
    while (cRow < this.edgeLength - 1) {
      for (cCol = 0; cCol < size; cCol = cCol + 1) {
        if (cCol >= this.edgeLength - 1 - cRow && cCol <= size - this.edgeLength + cRow) {
          starter[cRow][cCol] = SlotState.Marble;
        } else {
          starter[cRow][cCol] = SlotState.Invalid;
        }
      }
      cRow = cRow + 1;
    }
    //builds the center rectangle of the octagon
    while (cRow <= size - this.edgeLength) {
      for (cCol = 0; cCol < size; cCol = cCol + 1) {
        starter[cRow][cCol] = SlotState.Marble;
      }
      cRow = cRow + 1;
    }
    //builds the bottom part of the octagon
    while (cRow < size) {
      for (cCol = 0; cCol < size; cCol = cCol + 1) {
        if (cCol >= this.edgeLength - size + cRow && cCol < (2 * size) - this.edgeLength - cRow) {
          starter[cRow][cCol] = SlotState.Marble;
        } else {
          starter[cRow][cCol] = SlotState.Invalid;
        }
      }
      cRow = cRow + 1;
    }
    //checks to make sure that we are not trying to place the empty slot outside the octagon
    if (starter[sRow][sCol] == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      starter[sRow][sCol] = SlotState.Empty;
    }
    return starter;
  }
}
