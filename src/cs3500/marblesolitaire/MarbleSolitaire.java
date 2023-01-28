package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * The final program for a game of MarbleSolitaire. Allow the user to play a game.
 */
public final class MarbleSolitaire {
  /**
   * Main method for the program.
   *
   * @param args inputs taken into the program as command-line arguments
   */
  public static void main(String[] args) {
    //determines the type of the model (english, european, or triangle)
    MarbleSolitaireModel model;
    TriangleSolitaireModel tModel;
    MarbleSolitaireView view;
    //reads the inputs
    InputStreamReader in = new InputStreamReader(System.in);
    //determines if the game has any custom parameters
    boolean customEmpty = false;
    boolean customSize = false;
    //actual values of the custom parameters
    int size = 0;
    //sizes start from 1, like the controller, and not from 0, like the model and view
    int sRow = 1;
    int sCol = 1;
    //sets any custom specs for the game
    int input = 1;
    while (input < args.length) {
      switch (args[input]) {
        case "-size":
          input = input + 1;
          size = Integer.valueOf(args[input]);
          customSize = true;
          break;
        case "-hole":
          input = input + 1;
          sRow = Integer.valueOf(args[input]) - 1;
          input = input + 1;
          sCol = Integer.valueOf(args[input]) - 1;
          customEmpty = true;
          break;
        default:
          break;
      }
      input = input + 1;
    }
    //now constructs the actual game
    switch (args[0]) {
      case "english":
        if (!customSize && !customEmpty) {
          model = new EnglishSolitaireModel();
        }
        else if (customSize && !customEmpty) {
          model = new EnglishSolitaireModel(size);
        }
        else if (!customSize) {
          model = new EnglishSolitaireModel(sRow, sCol);
        }
        else {
          model = new EnglishSolitaireModel(size, sRow, sCol);
        }
        view = new MarbleSolitaireTextView(model);
        new MarbleSolitaireControllerImpl(model, view, in).playGame();
        break;
      case "european":
        if (!customSize && !customEmpty) {
          model = new EuropeanSolitaireModel();
        }
        else if (customSize && !customEmpty) {
          model = new EuropeanSolitaireModel(size);
        }
        else if (!customSize) {
          model = new EuropeanSolitaireModel(sRow, sCol);
        }
        else {
          model = new EuropeanSolitaireModel(size, sRow, sCol);
        }
        view = new MarbleSolitaireTextView(model);
        new MarbleSolitaireControllerImpl(model, view, in).playGame();
        break;
      case "triangular":
        if (!customSize && !customEmpty) {
          tModel = new TriangleSolitaireModel();
        }
        else if (customSize && !customEmpty) {
          tModel = new TriangleSolitaireModel(size);
        }
        else if (!customSize) {
          tModel = new TriangleSolitaireModel(sRow, sCol);
        }
        else {
          tModel = new TriangleSolitaireModel(size, sRow, sCol);
        }
        view = new TriangleSolitaireTextView(tModel);
        new MarbleSolitaireControllerImpl(tModel, view, in).playGame();
        break;
      default:
        throw new IllegalArgumentException("Must start with a model type");
    }
  }
}
