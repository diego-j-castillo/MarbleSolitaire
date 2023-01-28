import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Tests for exceptions to be thrown by EnglishSolitaireModel constructors and methods.
 */
public class EnglishSolitaireExceptions {
  //ESM Examples:
  private MarbleSolitaireModel basic = new EnglishSolitaireModel();
  private MarbleSolitaireModel customCenter = new EnglishSolitaireModel(2, 2);
  private MarbleSolitaireModel customSize = new EnglishSolitaireModel(5);
  private MarbleSolitaireModel customFull = new EnglishSolitaireModel(5, 4, 4);

  //exception should be thrown for a negative arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException1ArgNegativeThickness() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(-1);
  }

  //exception should be thrown for a zero arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException1ArgZeroThickness() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(0);
  }

  //exception should be thrown for an even arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException1ArgEvenThickness() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(2);
  }

  //exception should be thrown if we put hte center in the top-left invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2ArgsTopLeft() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(0, 0);
  }

  //exception should be thrown if we put the center in the bottom-left invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2ArgsBottomLeft() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(5, 1);
  }

  //exception should be thrown if we put the center in the top-right invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2ArgsTopRight() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(1, 6);
  }

  //exceoption should be thrown if we put the center in the bottom-right invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2ArgsBottomRight() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(5, 6);
  }

  //exception should be thrown for negative arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgNegativeThickness() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(-3, 3, 3);
  }

  //exception should be thrown for zero arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgZeroThickness() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(0, 2, 2);
  }

  //exception should be thrown for even arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgEvenThickness() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(6, 2, 2);
  }

  //exception should be thrown if we put the center in the top-left invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgTopLeft() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(3, 1, 1);
  }

  //exception should be thrown if we put the center in the top-right invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgTopRight() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(5, 0, 9);
  }

  //exception should be thrown if we put the center in the bottom-left invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgBottomLeft() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(7, 13, 0);
  }

  //exception should be thrown if we put the center in the bottom-right invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3BottomRight() {
    MarbleSolitaireModel errBoard = new EnglishSolitaireModel(3, 6, 5);
  }

  //movement is aligned on a column, but it is too far vertically
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooFarVertical() {
    this.customSize.move(1, 5, 5, 5);
  }

  //test is aligned on a row, but spots are adjacent (without a marble in between)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooCloseHorizontal() {
    this.basic.move(3, 2, 3, 3);
  }

  //movement is aligned on a row, but it is too far horizontally
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooFarHorizontal() {
    this.customSize.move(5, 9, 5, 5);
  }

  //movement is aligned on a column, but slots are adjacent (without a marble in between)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooCloseVertical() {
    this.customCenter.move(3, 2, 2, 2);
  }

  //diagonal movement should not work, even though there is a marble in between
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionDiagonallySeparated() {
    this.customSize.move(7, 7, 5, 5);
  }

  //FROM slot is invalid, so movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionStartsInvalid() {
    this.customFull.move(1, 1, 1, 3);
  }

  //FROM slot is empty, so movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionStartsEmpty() {
    this.customFull.move(4, 4, 6, 4);
  }

  //TO slot is invalid, so movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionEndsInvalid() {
    this.basic.move(0, 3, 0, 1);
  }

  //TO slot has a marble, so the movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionEndsMarble() {
    this.customSize.move(5, 0, 5, 2);
  }

  //there is no marble to jump over, so the movement should throw an exception
  //NOTE: no way to test for only invalid middle since no such movement exists in English model
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionMiddleEmpty() {
    this.basic.move(2, 3, 4, 3);
  }

  //since we try to start from an out-of-bounds slot, an exception should be thrown
  @Test(expected = IllegalArgumentException.class)
  public void testStartOOB() {
    this.basic.move(-1, 3, 1, 3);
  }

  //since we try to jump to an out-of-bounds slot, an exception should be thrown
  @Test(expected = IllegalArgumentException.class)
  public void testEndOOB() {
    this.basic.move(4, 5, 4, 7);
  }

  //should throw an exception is trying to find a slot at a negative row
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionNegRow() {
    this.basic.getSlotAt(-1, 0);
  }

  //should throw an exception if trying to find a slot at too large a row/too far down
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionLargeRow() {
    this.customCenter.getSlotAt(7, 3);
  }

  //should throw an exception if trying to find a slot at a negative column
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionNegCol() {
    this.basic.getSlotAt(5, -2);
  }

  //should throw an exception if trying to find a slot at too large a column/too far right
  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionLargeCol() {
    this.basic.getSlotAt(9, 14);
  }
}
