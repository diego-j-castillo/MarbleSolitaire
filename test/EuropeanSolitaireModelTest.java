import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the EuropeanSolitaireModelClass methods.
 */
public class EuropeanSolitaireModelTest extends AbstractModelTesting {
  //ESM Examples (all different constructors):
  private MarbleSolitaireModel basic;
  private MarbleSolitaireModel customCenter;
  private MarbleSolitaireModel customSize;
  private MarbleSolitaireModel customFull;

  @Before
  public void initial() {
    this.basic = new EuropeanSolitaireModel();
    this.customCenter = new EuropeanSolitaireModel(2, 2);
    this.customSize = new EuropeanSolitaireModel(5);
    this.customFull = new EuropeanSolitaireModel(5, 4, 4);
  }

  //tests how the move() method handles movements in every direction, properly changing the state
  //in the proper spots
  @Test
  public void testMove() {
    //move downwards
    AbstractModelTesting.testMovement(this.basic, 1, 3, 3, 3);
    //move again, but right
    AbstractModelTesting.testMovement(this.basic, 2, 1, 2, 3);
    //different size and left
    AbstractModelTesting.testMovement(this.customFull, 4, 6, 4, 4);
    //upwards move
    AbstractModelTesting.testMovement(this.customFull, 6, 5, 4, 5);
    assertEquals(34, this.basic.getScore());
    assertEquals(126, this.customFull.getScore());
  }

  //tests that the game can accurately report when the game is over
  @Test
  public void testIsGameOver() {
    //starting should not be over
    assertFalse(this.basic.isGameOver());
    assertFalse(this.customCenter.isGameOver());
    assertFalse(this.customSize.isGameOver());
    assertFalse(this.customFull.isGameOver());
    //side length of 1 should immediately be over
    assertTrue(new EuropeanSolitaireModel(1).isGameOver());
    MarbleSolitaireModel loser = new EuropeanSolitaireModel(0, 3);
    //doing movements on a custom center board to see if we can detect the game being over when
    //as the game progresses to its final state
    int[] inputs = {2, 3, 0, 3, 1, 1, 1, 3, 1, 4, 1, 2,
        2, 1, 2, 3, 2, 4, 2, 2, 2, 6, 2, 4,
        4, 6, 2, 6, 3, 4, 3, 6, 2, 6, 4, 6,
        5, 5, 3, 5, 5, 3, 5, 5, 3, 3, 5, 3,
        6, 3, 4, 3, 5, 1, 5, 3, 4, 3, 6, 3,
        3, 1, 5, 1, 3, 2, 5, 2, 1, 2, 3, 2,
        6, 2, 4, 2, 4, 2, 2, 2, 6, 4, 6, 2};
    AbstractModelTesting.testUntilGameOver(loser, inputs);
  }

  //getBoardSize should return the proper length of the largest row/column
  @Test
  public void testGetBoardSize() {
    //7 should be for the most basic game size
    assertEquals(7, this.basic.getBoardSize());
    assertEquals(7, this.customCenter.getBoardSize());
    //13 since 5 + 2 (4) is the longest width/height
    assertEquals(13, this.customSize.getBoardSize());
    assertEquals(13, this.customFull.getBoardSize());
    //19 since 7 + 2 (6) is the longest width/height
    assertEquals(19, new EuropeanSolitaireModel(7).getBoardSize());
  }

  //test getSlotAt(), which should be able to return the proper state of every slot in a board for
  //a default type board
  @Test
  public void testGetSlotAtDefault() {
    for (int r = 0; r < 2; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if ((c > 1 - r && c < 5 + r)) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.basic.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.basic.getSlotAt(r, c));
        }
      }
    }
    for (int r = 2; r < 5; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if (r == 3 && c == 3) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.basic.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.basic.getSlotAt(r, c));
        }
      }
    }
    for (int r = 5; r < 7; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if (c >= r - 4 && c < 11 - r) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.basic.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.basic.getSlotAt(r, c));
        }
      }
    }
  }

  //test getSlotAt(), which should be able to return the proper state of every slot in a board for
  //oen with a sideLength 3 and a custom center at (2, 2)
  @Test
  public void testGetSlotAtCustomCenter() {
    for (int r = 0; r < 2; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if ((c > 1 - r && c < 5 + r)) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customCenter.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customCenter.getSlotAt(r, c));
        }
      }
    }
    for (int r = 2; r < 5; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if (r == 2 && c == 2) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty,
              this.customCenter.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customCenter.getSlotAt(r, c));
        }
      }
    }
    for (int r = 5; r < 7; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if (c >= r - 4 && c < 11 - r) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customCenter.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customCenter.getSlotAt(r, c));
        }
      }
    }
  }

  //test getSlotAt(), which should be able to return the proper state of every slot in a board for
  //one with a center empty slot but a side length of 5
  @Test
  public void testGetSlotAtCustomSize() {
    for (int r = 0; r < 4; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (c > 3 - r && c < 9 + r) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customSize.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customSize.getSlotAt(r, c));
        }
      }
    }
    for (int r = 4; r < 9; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (r == 6 && c == 6) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty,
              this.customSize.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customSize.getSlotAt(r, c));
        }
      }
    }
    for (int r = 9; r < 13; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (c >= r - 8 && c < 21 - r) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customSize.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customSize.getSlotAt(r, c));
        }
      }
    }
  }

  //test getSlotAt(), which should be able to return the proper state of every slot in a board for
  //one with an empty slot at (4, 4) but a side length of 5
  @Test
  public void testGetSlotAtCustomFull() {
    for (int r = 0; r < 4; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (c > 3 - r && c < 9 + r) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customFull.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customFull.getSlotAt(r, c));
        }
      }
    }
    for (int r = 4; r < 9; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (r == 4 && c == 4) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty,
              this.customFull.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customFull.getSlotAt(r, c));
        }
      }
    }
    for (int r = 9; r < 13; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (c >= r - 8 && c < 21 - r) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
              this.customFull.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customFull.getSlotAt(r, c));
        }
      }
    }
  }

  //tests that the score of a game is accurately reported at the start and as the game progresses
  //by the getScore() method
  @Test
  public void testGetScore() {
    //starting scores tested
    assertEquals(36, this.basic.getScore());
    assertEquals(36, this.customCenter.getScore());
    assertEquals(128, this.customSize.getScore());
    assertEquals(128, this.customFull.getScore());
    assertEquals(276, new EuropeanSolitaireModel(7).getScore());
    //tests that moving works to decrease the score
    //moving successfully should reduce the score by one
    this.basic.move(5, 3, 3, 3);
    assertEquals(35, this.basic.getScore());
    //moving again to make sure it keeps counting down correctly
    this.basic.move(4, 1, 4, 3);
    assertEquals(34, this.basic.getScore());
    //keeps working properly as size changes from the default 3 armThickness
    this.customFull.move(2, 4, 4, 4);
    assertEquals(127, this.customFull.getScore());
    //moving again
    this.customFull.move(3, 6, 3, 4);
    assertEquals(126, this.customFull.getScore());
  }
}
