import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Abstracts testing for tests that are common amongst two different testing methods.
 */
public class AbstractModelTesting {
  /**
   * Abstracts out testing for an individual movement.
   *
   * @param model model to make a move with
   * @param fR fromRow
   * @param fC fromCol
   * @param tR toRow
   * @param tC toCol
   */
  protected static void testMovement(MarbleSolitaireModel model, int fR, int fC, int tR, int tC) {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(fR, fC));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(
            (fR + tR) / 2, (fC + tC) / 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(tR, tC));
    model.move(fR, fC, tR, tC);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(fR, fC));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(
            (fR + tR) / 2, (fC + tC) / 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(tR, tC));
  }

  /**
   * Uses the int array to move the given model and to check if the series of movements leads
   * to the game being over and if we can check that it is over.
   *
   * @param model model to make a move with
   * @param inputs array of ints to make movements with
   */
  protected static void testUntilGameOver(MarbleSolitaireModel model, int[] inputs) {
    int index = 0;
    while (index < inputs.length) {
      assertFalse(model.isGameOver());
      AbstractModelTesting.testMovement(model, inputs[index], inputs[index + 1], inputs[index + 2],
              inputs[index + 3]);
      index = index + 4;
    }
    assertTrue(model.isGameOver());
  }
}
