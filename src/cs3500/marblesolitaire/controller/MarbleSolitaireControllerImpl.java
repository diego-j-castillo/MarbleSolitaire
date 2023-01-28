package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Controller for a game of Marble Solitaire. Takes input from the specified source to play the
 * game and output changes made by the user through its specified view type. Transmit messages to
 * the Appendable field of the given MarbleSolitaireView. Only inputs that should be considered are
 * positive integers for inputting slot locations and "q"/"Q" to quit the game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  //model for the game that is controlled
  private final MarbleSolitaireModel model;
  //view for the game as it is being controlled
  private final MarbleSolitaireView view;
  //object to which the controller takes input from
  private final Readable in;
  //true if the user has not yet quit. Should change to false when the player quits.
  private boolean isPlaying;

  /**
   * Constructs a controller with a model, a view for the model, and an object from which to read
   * user input from.
   *
   * @param model model for the Marble Solitaire game to be played
   * @param view  visual representation for the game as it is being played
   * @param in    object from which the controller detects user input
   * @throws IllegalArgumentException model, view, and/or input object are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable in) {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("model, view, and input should not be null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
    //set to false for now since the game hasn't started yet.
    this.isPlaying = false;
  }

  /**
   * Plays the given game of Marble Solitaire and displays it as specified by this controllers
   * given view type. Model and score are displayed through the view at the beginning,
   * whenever a move is successfully made (minus the final one), and when the game is over.
   * Numbering of rows and columns starts from 1 (model and view start from 0). Non-positive inputs
   * should be blocked and re-entering of that row/column value should be prompted. If a move is
   * invalid, the move should be blocked, but the game should still be allowed to be played.
   * At any point, inputting "q" or "Q" will end the game and display the board and the score.
   *
   * @throws IllegalStateException input/output could not properly be read/transmitted
   */
  @Override
  public void playGame() {
    Scanner scan = new Scanner(this.in);
    this.isPlaying = true;
    //represents the ints that model.move() uses in order:
    //fromRow, fromCol, toRow, toCol
    int[] slots = new int[4];
    while (!this.model.isGameOver() && this.isPlaying) {
      this.transmitGameAndScore();
      this.attemptMovement(scan, slots);
    }
    //only runs once the game is actually over (by losing or winning)
    if (isPlaying) {
      this.transmitMessage("Game over!\n");
      this.transmitGameAndScore();
    } else {
      //assumes that the game was quit due to isPlaying now being false
      this.transmitMessage("Game quit!\nState of game when quit:\n");
      this.transmitGameAndScore();
    }
  }

  /**
   * Transmits the current model as it is, with the current score in a new line below.
   *
   * @throws IllegalStateException output could not be properly transmitted to the view
   */
  private void transmitGameAndScore() {
    try {
      this.view.renderBoard();
      this.view.renderMessage(String.format("\nScore: %d\n", this.model.getScore()));
    } catch (IOException e) {
      throw new IllegalStateException("output could not be transmitted");
    }
  }

  /**
   * Transmits message given to the view's output destination.
   *
   * @param message what should be transmitted to the view's output
   * @throws IllegalStateException output could not be properly transmitted
   */
  private void transmitMessage(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("output could not be transmitted");
    }
  }

  /**
   * Tries to read inputs to determine the coordinates for move. If "q" or "Q" are entered at any
   * point, we stop playing the game and end this method. If the input is not a positive integer,
   * "q", or "Q", the input is attempted again.
   *
   * @param scan  where the inputs are taken from
   * @param slots array of integers that is used by the model to move
   * @param curr  current slot that we are trying to overwrite with a number
   * @throws IllegalStateException input/output could not properly be read/transmitted
   */
  private void takeMoveInput(Scanner scan, int[] slots, int curr) {
    String nextInput;
    try {
      nextInput = scan.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("input could not be read");
    }
    try {
      slots[curr] = Integer.valueOf(nextInput);
      if (slots[curr] < 1) {
        this.transmitMessage("Please re-enter a valid input.\n");
        this.takeMoveInput(scan, slots, curr);
      }
    } catch (NumberFormatException e) {
      if (nextInput.equals("q") || nextInput.equals("Q")) {
        this.isPlaying = false;
      } else {
        this.transmitMessage("Please re-enter a valid input.\n");
        this.takeMoveInput(scan, slots, curr);
      }
    }
  }

  /**
   * Reads the necessary inputs to make a movement and then attempts a movement. If the movement is
   * not valid, the method will be called again to take in more inputs.
   *
   * @param scan  where the inputs are taken from
   * @param slots array of integers that is used by the model to move
   * @throws IllegalStateException input/output could not properly be read/transmitted
   */
  private void attemptMovement(Scanner scan, int[] slots) {
    //the number of the input that is being taken:
    //fromRow, fromCol, toRow, toCol (0, 1, 2, 3)
    int inCount = 0;
    //takes the input for the inputs for moving a marble
    while (inCount < slots.length && this.isPlaying) {
      this.takeMoveInput(scan, slots, inCount);
      //keeps going through the array of input slots
      inCount = inCount + 1;
    }
    //subtracts each input by 1 to match how the model starts numbering from 0
    try {
      if (this.isPlaying) {
        this.model.move(slots[0] - 1, slots[1] - 1, slots[2] - 1, slots[3] - 1);
      }
    } catch (IllegalArgumentException e) {
      //tries again to take valid inputs
      this.transmitMessage("Invalid move. Play again. " + e.getMessage() + "\n");
      this.attemptMovement(scan, slots);
    }
  }
}
