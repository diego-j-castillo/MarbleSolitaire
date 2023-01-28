import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Tests for exceptions to be thrown by TriangleSolitaire constructors and methods.
 */
public class TriangleSolitaireExceptions {
  //TSM Examples:
  MarbleSolitaireModel basic = new TriangleSolitaireModel();
  MarbleSolitaireModel customCenter = new TriangleSolitaireModel(2, 2);
  MarbleSolitaireModel customSize = new TriangleSolitaireModel(7);
  MarbleSolitaireModel customFull = new TriangleSolitaireModel(6, 4, 3);

  //tests that we can't put a negative length for 1 arg
  @Test(expected = IllegalArgumentException.class)
  public void tests1ArgNegative() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(-2);
  }

  //tests that we can't put a 0 length for 1 arg
  @Test(expected = IllegalArgumentException.class)
  public void test1ArgZero() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(0);
  }

  //exception should be thrown if we put the center in an invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2ArgsInvalid() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(2, 4);
  }

  //exception should be thrown if we put the center out of bounds vertically
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2ArgsOOB() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(6, 1);
  }

  //exception should be thrown if we put the center out of bonds horizontally
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2ArgsTopRight() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(1, 6);
  }

  //exception should be thrown for negative arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgNegative() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(-3, 3, 3);
  }

  //exception should be thrown for zero arm thickness
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgZero() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(0, 2, 2);
  }

  //exception should be thrown if we put the empty in the invalid region
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgInvalid() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(9, 1, 6);
  }

  //exception should be thrown if we put the center OOB vertically
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgOOBVertically() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(6, 9, 0);
  }

  //exception should be thrown if we put the center OOB horizontally
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3ArgOOBHorizontally() {
    MarbleSolitaireModel errBoard = new TriangleSolitaireModel(7, 0, 25);
  }

  //test is aligned on a row, but spots are adjacent (without a marble in between)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooCloseHorizontal() {
    this.customFull.move(3, 2, 3, 3);
  }

  //movement is aligned on a row, but it is too far horizontally
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooFarHorizontal() {
    this.customFull.move(5, 1, 5, 5);
  }

  //movement is vertically apart right but are straight vertically
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionStraightVertical() {
    this.basic.move(2, 1, 0, 0);
  }

  //movement is too close diagonally for a movement on the negative slope
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooCloseNegDiagonal() {
    this.basic.move(0, 0, 4, 4);
  }

  //movement is too close diagonally for a movement on the negative slope
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooFarNegDiagonal() {
    this.basic.move(1, 1, 0, 0);
  }

  //movement is too close diagonally and on the same column
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionTooCloseDiagonalSameCol() {
    this.basic.move(1, 0, 0, 0);
  }

  //movement is two columns apart but is too close by row
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionColAlignedTooClose() {
    this.basic.move(5, 0, 4, 2);
  }

  //movement is two columns apart but is too far by row
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionColAlignedTooFar() {
    this.basic.move(5, 0, 3, 2);
  }

  //diagonal movement is same column but is too close by row
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionColSameTooClose() {
    this.basic.move(5, 0, 4, 0);
  }

  //diagonal movement is same column but is too far by row
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionColSameTooFar() {
    this.basic.move(5, 0, 3, 0);
  }

  //trying to do an up-right movement, but the columns are not equal
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionDiffColUpRight() {
    MarbleSolitaireModel tester = new TriangleSolitaireModel(2, 2);
    tester.move(4, 0, 2, 2);
  }

  //trying to do a down-left movement, but the columns are not equal
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionDiffColDownLeft() {
    MarbleSolitaireModel tester = new TriangleSolitaireModel(4, 0);
    tester.move(2, 2, 4, 0);
  }

  //FROM slot is invalid, so movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionStartsInvalid() {
    this.customFull.move(1, 3, 1, 1);
  }

  //FROM slot is empty, so movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionStartsEmpty() {
    this.basic.move(0, 0, 2, 0);
  }

  //TO slot is invalid, so movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionEndsInvalid() {
    this.basic.move(1, 0, 1, 2);
  }

  //TO slot has a marble, so the movement should throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionEndsMarble() {
    this.customSize.move(5, 0, 5, 2);
  }

  //there is no marble to jump over, so the movement should throw an exception
  //NOTE: no way to test for only invalid middle since no such movement exists in triangle model
  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionMiddleEmpty() {
    this.customCenter.move(3, 3, 1, 1);
  }

  //since we try to start from an out-of-bounds slot, an exception should be thrown
  @Test(expected = IllegalArgumentException.class)
  public void testStartOOB() {
    this.basic.move(-1, 0, 1, 0);
  }

  //since we try to jump to an out-of-bounds slot, an exception should be thrown
  @Test(expected = IllegalArgumentException.class)
  public void testEndOOB() {
    this.basic.move(4, 4, 4, 6);
  }
}
