package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AMarbleSolitaireModel;

/**
 * Represents a Marble Solitaire game in the English Style. The board is a square,
 * with slots that are on the outside of the center cross being Invalid. Cross is made up of one
 * square of slots (size a * a), with four rectangles on each side of it (size a * (a - 1)).
 * Slots on the inside cross either contain a Marble or are Empty. Movements must jump over
 * a Marble and to an Empty slot, which removes the jumped-over Marble. Game ends when no
 * more movements can be made, and the score is the current number of Marbles on the board. Extends
 * the abstract model to use methods that are common amongst rectangular-grid-type models.
 */
public class EnglishSolitaireModel extends AMarbleSolitaireModel {
  /**
   * Constructs the basic game with armThickness of 3 and an empty slot at the center.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs a basic game of armThickness 3, but chooses a certain place for the empty slot.
   *
   * @param sRow row where empty slot is located (from 0 and top)
   * @param sCol column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException empty placed in invalid slot
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(3, sRow, sCol);
  }

  /**
   * Constructs a game of custom armThickness, with the center as the empty slot.
   *
   * @param aThick thickness of each of the four arms
   * @throws IllegalArgumentException thickness not positive odd number
   */
  public EnglishSolitaireModel(int aThick) {
    super(aThick, aThick * 3 / 2 - 1, aThick * 3 / 2 - 1);
  }

  /**
   * Constructs a game with a custom armThickness and empty slot location.
   *
   * @param aThick thickness of each of the four arms
   * @param sRow   row where empty slot is located (from 0 and top)
   * @param sCol   column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException thickness not positive odd number
   * @throws IllegalArgumentException empty placed in invalid slot
   */
  public EnglishSolitaireModel(int aThick, int sRow, int sCol) {
    super(aThick, sRow, sCol);
  }

  /**
   * Creates a board for the game with each Slot correctly given its starting state. Slots in the
   * cross area of the board are marbles, minus the one designated Empty slot. All other slots are
   * set to an Invalid state. Should the arm thickness be even or should the starting empty slot be
   * placed outside the center cross, the board should not be constructed.
   *
   * @param sRow row where empty slot is located (from 0 and top)
   * @param sCol column where empty slot is located (from 0 and left)
   * @throws IllegalArgumentException arm thickness is even
   * @throws IllegalArgumentException place for the empty slot is not in the playing cross
   */
  @Override
  protected SlotState[][] startBoard(int sRow, int sCol) {
    //checks if the arm thickness is even
    if (this.edgeLength % 2 == 0) {
      throw new IllegalArgumentException("arm thickness must be even for this model");
    }
    //checks if the empty slot is placed outside the center cross
    if ((sRow < this.edgeLength - 1 || sRow >= this.edgeLength * 2 - 1)
            && (sCol < this.edgeLength - 1 || sCol >= this.edgeLength * 2 - 1)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    //forms the board since the inputs are valid
    int size = this.getBoardSize();
    SlotState[][] starter = new SlotState[size][size];
    for (int cRow = 0; cRow < size; cRow = cRow + 1) {
      for (int cCol = 0; cCol < size; cCol = cCol + 1) {
        //checks if the slot should be valid
        if ((cRow >= this.edgeLength - 1 && cRow < this.edgeLength * 2 - 1)
                || (cCol >= this.edgeLength - 1 && cCol < this.edgeLength * 2 - 1)) {
          starter[cRow][cCol] = SlotState.Marble;
        } else {
          //sets slot to invalid should it be out of the playing field
          starter[cRow][cCol] = SlotState.Invalid;
        }
      }
    }
    //places the empty slot in the given position
    starter[sRow][sCol] = SlotState.Empty;
    return starter;
  }
}
