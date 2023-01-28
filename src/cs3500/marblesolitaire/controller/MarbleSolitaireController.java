package cs3500.marblesolitaire.controller;

/**
 * Interface that represents the operations that should be offered by a
 * controller of a Marble Solitaire game.
 */
public interface MarbleSolitaireController {
  /**
   * Plays a newly created game of MarbleSolitaire. Implementations should use a model and view
   * to modify the game state and display its current state as the game goes on.
   *
   * @throws IllegalStateException input/output could not properly be read/transmitted
   */
  void playGame();
}
