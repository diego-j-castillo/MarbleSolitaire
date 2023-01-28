import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for hte methods of the TriangleSolitaireModel class.
 */
public class TriangleSolitaireModelTest extends AbstractModelTesting {
  //TSM Examples:
  MarbleSolitaireModel basic;
  MarbleSolitaireModel customCenter;
  MarbleSolitaireModel customSize;
  MarbleSolitaireModel customFull;

  //sets the initial conditions for the example models for testing
  @Before
  public void initial() {
    this.basic = new TriangleSolitaireModel();
    this.customCenter = new TriangleSolitaireModel(2, 2);
    this.customSize = new TriangleSolitaireModel(7);
    this.customFull = new TriangleSolitaireModel(6, 4, 3);
  }

  //tests that the model allows movement in all 6 possible directions
  @Test
  public void testMove() {
    //up-right movement
    AbstractModelTesting.testMovement(this.basic, 2, 0, 0, 0);
    //down left movement
    MarbleSolitaireModel testDL = new TriangleSolitaireModel(2, 0);
    AbstractModelTesting.testMovement(testDL, 0, 0, 2, 0);
    //down-right movement
    AbstractModelTesting.testMovement(this.customCenter, 0, 0, 2, 2);
    //up-left movement
    AbstractModelTesting.testMovement(this.customCenter, 3, 3, 1, 1);
    //right movement
    AbstractModelTesting.testMovement(this.customFull, 4, 1, 4, 3);
    //left movement
    AbstractModelTesting.testMovement(this.customFull, 4, 4, 4, 2);
    assertEquals(13, this.basic.getScore());
    assertEquals(13, testDL.getScore());
    assertEquals(12, this.customCenter.getScore());
    assertEquals(18, this.customFull.getScore());
  }

  //tests that the game can be played until completion and will properly track progress
  @Test
  public void testIsGameOver() {
    //examples should start with some movements possible
    assertFalse(this.basic.isGameOver());
    assertFalse(this.customCenter.isGameOver());
    assertFalse(this.customSize.isGameOver());
    assertFalse(this.customFull.isGameOver());
    //size 2 has no movements
    assertTrue(new TriangleSolitaireModel(2).isGameOver());
    //goes through the moves to see if the method can keep correct through movements
    MarbleSolitaireModel loser = new TriangleSolitaireModel(3);
    int[] shortInputs = {2, 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2};
    AbstractModelTesting.testUntilGameOver(loser, shortInputs);
    //goes through a size 5 game just to be sure
    int[] longerInputs = {2, 2, 0, 0, 2, 0, 2, 2, 3, 3, 1, 1,
        4, 3, 2, 1, 4, 1, 4, 3, 4, 4, 4, 2,
        4, 2, 2, 0, 2, 0, 2, 2, 1, 1, 3, 3,
        0, 0, 2, 0, 3, 0, 1, 0};
    AbstractModelTesting.testUntilGameOver(this.basic, longerInputs);
  }

  //tests that getBoardSize() returns the right length, which is the size of the triangle base
  @Test
  public void testGetBoardSize() {
    assertEquals(5, this.basic.getBoardSize());
    assertEquals(5, this.customCenter.getBoardSize());
    assertEquals(7, this.customSize.getBoardSize());
    assertEquals(6, this.customFull.getBoardSize());
    assertEquals(15, new TriangleSolitaireModel(15).getBoardSize());
  }

  //tests that boards correctly construct and getSlotAt() can correctly find the state of each
  @Test
  public void testGetSlotAt() {
    //for a default board
    for (int r = 0; r < 5; r = r + 1) {
      for (int c = 0; c < 5; c = c + 1) {
        if (c <= r) {
          if (r == 0 && c == 0) {
            assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.basic.getSlotAt(r, c));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.basic.getSlotAt(r, c));
          }
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.basic.getSlotAt(r, c));
        }
      }
    }
    //for a board of a custom empty slot
    for (int r = 0; r < 5; r = r + 1) {
      for (int c = 0; c < 5; c = c + 1) {
        if (c <= r) {
          if (r == 2 && c == 2) {
            assertEquals(MarbleSolitaireModelState.SlotState.Empty,
                this.customCenter.getSlotAt(r, c));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                this.customCenter.getSlotAt(r, c));
          }
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customCenter.getSlotAt(r, c));
        }
      }
    }
    //for a board of size 7
    for (int r = 0; r < 7; r = r + 1) {
      for (int c = 0; c < 7; c = c + 1) {
        if (c <= r) {
          if (r == 0 && c == 0) {
            assertEquals(MarbleSolitaireModelState.SlotState.Empty,
                this.customSize.getSlotAt(r, c));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                this.customSize.getSlotAt(r, c));
          }
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customSize.getSlotAt(r, c));
        }
      }
    }
    //for a board of size 6 and a custom center at 4, 3
    for (int r = 0; r < 6; r = r + 1) {
      for (int c = 0; c < 6; c = c + 1) {
        if (c <= r) {
          if (r == 4 && c == 3) {
            assertEquals(MarbleSolitaireModelState.SlotState.Empty,
                this.customFull.getSlotAt(r, c));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                this.customFull.getSlotAt(r, c));
          }
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
              this.customFull.getSlotAt(r, c));
        }
      }
    }
  }

  //tests the getScore() method and its ability to keep up as the model moves
  @Test
  public void testGetScore() {
    assertEquals(14, this.basic.getScore());
    assertEquals(14, this.customCenter.getScore());
    assertEquals(27, this.customSize.getScore());
    assertEquals(20, this.customFull.getScore());
    //checks to see if it keeps track as it moves
    this.basic.move(2, 0, 0, 0);
    assertEquals(13, this.basic.getScore());
    this.basic.move(4, 0, 2, 0);
    assertEquals(12, this.basic.getScore());
  }
}
