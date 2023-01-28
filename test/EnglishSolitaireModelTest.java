import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the EnglishSolitaireModel constructors and methods.
 */
public class EnglishSolitaireModelTest extends AbstractModelTesting {
  //ESM Examples (all different constructors):
  private MarbleSolitaireModel basic;
  private MarbleSolitaireModel customCenter;
  private MarbleSolitaireModel customSize;
  private MarbleSolitaireModel customFull;

  //sets the created examples to their initial states for testing
  @Before
  public void initial() {
    this.basic = new EnglishSolitaireModel();
    this.customCenter = new EnglishSolitaireModel(2, 2);
    this.customSize = new EnglishSolitaireModel(5);
    this.customFull = new EnglishSolitaireModel(5, 4, 4);
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
    assertEquals(30, this.basic.getScore());
    assertEquals(102, this.customFull.getScore());
  }

  //checks that we can successfully determine hte game to be over with isGameOver()
  @Test
  public void testIsGameOver() {
    //should never be over from the start for armThickness >= 3
    assertFalse(this.basic.isGameOver());
    assertFalse(this.customCenter.isGameOver());
    assertFalse(this.customSize.isGameOver());
    assertFalse(this.customFull.isGameOver());
    //too small to have any valid moves from the get go
    assertTrue(new EnglishSolitaireModel(1).isGameOver());
    //moving until the game should end, which should make method return true. Should not end any
    //time earlier.
    int[] inputs = {3, 5, 3, 3, 3, 2, 3, 4, 3, 0, 3, 2, 5, 3, 3, 3, 2, 3, 4, 3, 0, 3, 2, 3};
    AbstractModelTesting.testUntilGameOver(this.basic, inputs);
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
    assertEquals(19, new EnglishSolitaireModel(7).getBoardSize());
  }

  //test getSlotAt(), which should be able to return the proper state of every slot in a board
  @Test
  public void testGetSlotAt() {
    //test for default board. We know the shape, so we can test for every slot
    for (int r = 0; r < 7; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if (r == 3 && c == 3) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.basic.getSlotAt(r, c));
        } else if ((r > 1 && r < 5) || (c > 1 && c < 5)) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.basic.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.basic.getSlotAt(r, c));
        }
      }
    }
    //test for customCenter board (see if the shape overall stays the same)
    for (int r = 0; r < 7; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if (r == 2 && c == 2) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty,
                  this.customCenter.getSlotAt(r, c));
        } else if ((r > 1 && r < 5) || (c > 1 && c < 5)) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                  this.customCenter.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
                  this.customCenter.getSlotAt(r, c));
        }
      }
    }
    //test for 5 armThick board (see if cross-shape still holds up at larger size)
    for (int r = 0; r < 13; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (r == 6 && c == 6) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty,
                  this.customSize.getSlotAt(r, c));
        } else if ((r > 3 && r < 9) || (c > 3 && c < 9)) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                  this.customSize.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
                  this.customSize.getSlotAt(r, c));
        }
      }
    }
    //test for a fully custom board
    for (int r = 0; r < 13; r = r + 1) {
      for (int c = 0; c < 13; c = c + 1) {
        if (r == 4 && c == 4) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty,
                  this.customFull.getSlotAt(r, c));
        } else if ((r > 3 && r < 9) || (c > 3 && c < 9)) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                  this.customFull.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
                  this.customFull.getSlotAt(r, c));
        }
      }
    }
  }

  //tests that getScore() works and updates the score as movements are made
  @Test
  public void testGetScore() {
    //score is right at the start for boards of different sizes
    assertEquals(32, this.basic.getScore());
    assertEquals(32, this.customCenter.getScore());
    assertEquals(104, this.customSize.getScore());
    assertEquals(104, this.customFull.getScore());
    assertEquals(216, new EnglishSolitaireModel(7).getScore());
    //moving successfully should reduce the score by one
    this.basic.move(5, 3, 3, 3);
    assertEquals(31, this.basic.getScore());
    //moving again to make sure it keeps counting down correctly
    this.basic.move(4, 1, 4, 3);
    assertEquals(30, this.basic.getScore());
    //keeps working properly as size changes from the default 3 armThickness
    this.customFull.move(2, 4, 4, 4);
    assertEquals(103, this.customFull.getScore());
    //moving again
    this.customFull.move(3, 6, 3, 4);
    assertEquals(102, this.customFull.getScore());
  }
}