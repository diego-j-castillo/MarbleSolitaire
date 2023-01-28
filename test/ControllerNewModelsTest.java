import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests MarbleSolitaireControllerImpl for the new European and Triangle Solitaire models.
 */
public class ControllerNewModelsTest {
  //tests that "q" will quit the game for each model
  @Test
  public void testQuitLowercase() {
    //for the european model
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    StringBuilder outE = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inE = new StringReader("q 1 2 3 4");
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    String[] linesE = outE.toString().split("\n");
    assertEquals("Game quit!", linesE[linesE.length - 10]);
    assertEquals("State of game when quit:", linesE[linesE.length - 9]);
    assertEquals("    O O O", linesE[linesE.length - 8]);
    assertEquals("  O O O O O", linesE[linesE.length - 7]);
    assertEquals("O O O O O O O", linesE[linesE.length - 6]);
    assertEquals("O O O _ O O O", linesE[linesE.length - 5]);
    assertEquals("O O O O O O O", linesE[linesE.length - 4]);
    assertEquals("  O O O O O", linesE[linesE.length - 3]);
    assertEquals("    O O O", linesE[linesE.length - 2]);
    assertEquals("Score: 36", linesE[linesE.length - 1]);
    //for the triangle model
    MarbleSolitaireModel basicT = new TriangleSolitaireModel();
    StringBuilder outT = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inTr = new StringReader("q 5 6 7 8");
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    String[] linesT = outT.toString().split("\n");
    assertEquals("Game quit!", linesT[linesT.length - 8]);
    assertEquals("State of game when quit:", linesT[linesT.length - 7]);
    assertEquals("    _", linesT[linesT.length - 6]);
    assertEquals("   O O", linesT[linesT.length - 5]);
    assertEquals("  O O O", linesT[linesT.length - 4]);
    assertEquals(" O O O O", linesT[linesT.length - 3]);
    assertEquals("O O O O O", linesT[linesT.length - 2]);
    assertEquals("Score: 14", linesT[linesT.length - 1]);
  }

  //tests that "Q" will quit the game for each model
  @Test
  public void testQuitUppercase() {
    //for the european model
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    StringBuilder outE = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inE = new StringReader("Q 1 2 3 4");
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    String[] linesE = outE.toString().split("\n");
    assertEquals("Game quit!", linesE[linesE.length - 10]);
    assertEquals("State of game when quit:", linesE[linesE.length - 9]);
    assertEquals("    O O O", linesE[linesE.length - 8]);
    assertEquals("  O O O O O", linesE[linesE.length - 7]);
    assertEquals("O O O O O O O", linesE[linesE.length - 6]);
    assertEquals("O O O _ O O O", linesE[linesE.length - 5]);
    assertEquals("O O O O O O O", linesE[linesE.length - 4]);
    assertEquals("  O O O O O", linesE[linesE.length - 3]);
    assertEquals("    O O O", linesE[linesE.length - 2]);
    assertEquals("Score: 36", linesE[linesE.length - 1]);
    //for the triangle model
    MarbleSolitaireModel basicT = new TriangleSolitaireModel();
    StringBuilder outT = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inTr = new StringReader("Q 5 6 7 8");
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    String[] linesT = outT.toString().split("\n");
    assertEquals("Game quit!", linesT[linesT.length - 8]);
    assertEquals("State of game when quit:", linesT[linesT.length - 7]);
    assertEquals("    _", linesT[linesT.length - 6]);
    assertEquals("   O O", linesT[linesT.length - 5]);
    assertEquals("  O O O", linesT[linesT.length - 4]);
    assertEquals(" O O O O", linesT[linesT.length - 3]);
    assertEquals("O O O O O", linesT[linesT.length - 2]);
    assertEquals("Score: 14", linesT[linesT.length - 1]);
  }

  //tests we can quit at the second input
  @Test
  public void testQuit2nd() {
    //for the european model
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    StringBuilder outE = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inE = new StringReader("1 q");
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    String[] linesE = outE.toString().split("\n");
    assertEquals("Game quit!", linesE[linesE.length - 10]);
    assertEquals("State of game when quit:", linesE[linesE.length - 9]);
    assertEquals("    O O O", linesE[linesE.length - 8]);
    assertEquals("  O O O O O", linesE[linesE.length - 7]);
    assertEquals("O O O O O O O", linesE[linesE.length - 6]);
    assertEquals("O O O _ O O O", linesE[linesE.length - 5]);
    assertEquals("O O O O O O O", linesE[linesE.length - 4]);
    assertEquals("  O O O O O", linesE[linesE.length - 3]);
    assertEquals("    O O O", linesE[linesE.length - 2]);
    assertEquals("Score: 36", linesE[linesE.length - 1]);
    //for the triangle model
    MarbleSolitaireModel basicT = new TriangleSolitaireModel();
    StringBuilder outT = new StringBuilder();
    Reader inTr = new StringReader("1 Q");
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    String[] linesT = outT.toString().split("\n");
    assertEquals("Game quit!", linesT[linesT.length - 8]);
    assertEquals("State of game when quit:", linesT[linesT.length - 7]);
    assertEquals("    _", linesT[linesT.length - 6]);
    assertEquals("   O O", linesT[linesT.length - 5]);
    assertEquals("  O O O", linesT[linesT.length - 4]);
    assertEquals(" O O O O", linesT[linesT.length - 3]);
    assertEquals("O O O O O", linesT[linesT.length - 2]);
    assertEquals("Score: 14", linesT[linesT.length - 1]);
  }

  //tests we can quit at the third input
  @Test
  public void testQuit3rd() {
    //for the european model
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    StringBuilder outE = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inE = new StringReader("1 2 Q ");
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    String[] linesE = outE.toString().split("\n");
    assertEquals("Game quit!", linesE[linesE.length - 10]);
    assertEquals("State of game when quit:", linesE[linesE.length - 9]);
    assertEquals("    O O O", linesE[linesE.length - 8]);
    assertEquals("  O O O O O", linesE[linesE.length - 7]);
    assertEquals("O O O O O O O", linesE[linesE.length - 6]);
    assertEquals("O O O _ O O O", linesE[linesE.length - 5]);
    assertEquals("O O O O O O O", linesE[linesE.length - 4]);
    assertEquals("  O O O O O", linesE[linesE.length - 3]);
    assertEquals("    O O O", linesE[linesE.length - 2]);
    assertEquals("Score: 36", linesE[linesE.length - 1]);
    //for the triangle model
    MarbleSolitaireModel basicT = new TriangleSolitaireModel();
    StringBuilder outT = new StringBuilder();
    Reader inTr = new StringReader("1 2 q");
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    String[] linesT = outT.toString().split("\n");
    assertEquals("Game quit!", linesT[linesT.length - 8]);
    assertEquals("State of game when quit:", linesT[linesT.length - 7]);
    assertEquals("    _", linesT[linesT.length - 6]);
    assertEquals("   O O", linesT[linesT.length - 5]);
    assertEquals("  O O O", linesT[linesT.length - 4]);
    assertEquals(" O O O O", linesT[linesT.length - 3]);
    assertEquals("O O O O O", linesT[linesT.length - 2]);
    assertEquals("Score: 14", linesT[linesT.length - 1]);
  }

  //tests we can quit at the fourth input
  @Test
  public void testQuit4th() {
    //for the european model
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    StringBuilder outE = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inE = new StringReader("1 2 3 q");
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    String[] linesE = outE.toString().split("\n");
    assertEquals("Game quit!", linesE[linesE.length - 10]);
    assertEquals("State of game when quit:", linesE[linesE.length - 9]);
    assertEquals("    O O O", linesE[linesE.length - 8]);
    assertEquals("  O O O O O", linesE[linesE.length - 7]);
    assertEquals("O O O O O O O", linesE[linesE.length - 6]);
    assertEquals("O O O _ O O O", linesE[linesE.length - 5]);
    assertEquals("O O O O O O O", linesE[linesE.length - 4]);
    assertEquals("  O O O O O", linesE[linesE.length - 3]);
    assertEquals("    O O O", linesE[linesE.length - 2]);
    assertEquals("Score: 36", linesE[linesE.length - 1]);
    //for the triangle model
    MarbleSolitaireModel basicT = new TriangleSolitaireModel();
    StringBuilder outT = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader inTr = new StringReader("1 2 3 Q");
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    String[] linesT = outT.toString().split("\n");
    assertEquals("Game quit!", linesT[linesT.length - 8]);
    assertEquals("State of game when quit:", linesT[linesT.length - 7]);
    assertEquals("    _", linesT[linesT.length - 6]);
    assertEquals("   O O", linesT[linesT.length - 5]);
    assertEquals("  O O O", linesT[linesT.length - 4]);
    assertEquals(" O O O O", linesT[linesT.length - 3]);
    assertEquals("O O O O O", linesT[linesT.length - 2]);
    assertEquals("Score: 14", linesT[linesT.length - 1]);
  }

  //tests that movement works for the European board, properly translating each input number down
  //by one to match the numbering system of the model and view
  @Test
  public void testEuropeanMovement() {
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    StringBuilder outE = new StringBuilder();
    Reader inE = new StringReader("4 2 4 4 q");
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\nScore: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\nScore: 35\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\nScore: 35\n", outE.toString());
  }

  //tests that movement works for the Triangle board, properly translating each input number down
  //by one to match the numbering system of the model and view
  @Test
  public void testTriangleMovement() {
    MarbleSolitaireModel basicT = new TriangleSolitaireModel();
    StringBuilder outT = new StringBuilder();
    Reader inTr = new StringReader("3 1 1 1 q");
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\nScore: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\nScore: 13\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\nScore: 13\n", outT.toString());
  }

  //tests that the invalid moves for a european model are properly detected and the right message
  //shows to tell more about the error with the input
  @Test
  public void testEuropeanInvalidMoves() {
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    StringBuilder outE = new StringBuilder();
    StringBuilder input = new StringBuilder();
    //alignment and spacing issues:
    input.append("8 6 4 4 ");
    input.append("4 10 4 4 ");
    input.append("4 6 9 4 ");
    input.append("4 6 4 13 ");
    input.append("4 7 4 4 ");
    input.append("4 5 4 4 ");
    //starting slot issues:
    input.append("1 1 1 3 ");
    input.append("4 4 4 6 ");
    //middle slot issues:
    input.append("4 5 4 3 ");
    //end slot issues:
    input.append("4 6 6 6 ");
    input.append("4 5 4 7 q");
    Reader inE = new StringReader(input.toString());
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\nScore: 36\n"
            + "Invalid move. Play again. To and From slots must align with a slot in between\n"
            + "Invalid move. Play again. To and From slots must align with a slot in between\n"
            + "Invalid move. Play again. To and From slots must align with a slot in between\n"
            + "Invalid move. Play again. To and From slots must align with a slot in between\n"
            + "Invalid move. Play again. To and From slots must align with a slot in between\n"
            + "Invalid move. Play again. To and From slots must align with a slot in between\n"
            + "Invalid move. Play again. Movement must start from a marble\n"
            + "Invalid move. Play again. Movement must start from a marble\n"
            + "Invalid move. Play again. Movement must jump over a marble\n"
            + "Invalid move. Play again. Movement must end on an empty slot\n"
            + "Invalid move. Play again. Movement must end on an empty slot\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\nScore: 36\n", outE.toString());
  }

  //tests that the invalid moves for a triangle model are properly detected and the right message
  //shows to tell more about the error with the input
  @Test
  public void testTriangleInvalidMoves() {
    StringBuilder input = new StringBuilder();
    MarbleSolitaireModel basicT = new TriangleSolitaireModel(2, 1);
    StringBuilder outT = new StringBuilder();
    //bad diagonal movements
    input.append("2 1 3 2 ");
    input.append("1 1 3 2 ");
    input.append("5 1 3 2 ");
    input.append("5 5 3 2 ");
    input.append("5 1 1 1 ");
    input.append("5 5 1 1 ");
    input.append("2 1 5 5 ");
    input.append("2 2 5 1 ");
    //bad horizontal movements
    input.append("5 1 5 5 ");
    input.append("5 1 5 2 ");
    input.append("5 4 5 3 ");
    input.append("5 5 5 1 ");
    //starts not marble
    input.append("3 2 5 4 ");
    input.append("1 3 1 1 ");
    //jumps over an empty
    input.append("3 1 3 3 ");
    //lands on not an empty
    input.append("1 1 3 3 ");
    input.append("4 3 4 5 Q");
    Reader inTr = new StringReader(input.toString());
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    assertEquals("    O\n"
            + "   O O\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O\nScore: 14\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. To and From slots must line up correctly\n"
            + "Invalid move. Play again. Movement must start from a marble\n"
            + "Invalid move. Play again. Movement must start from a marble\n"
            + "Invalid move. Play again. Movement must jump over a marble\n"
            + "Invalid move. Play again. Movement must end on an empty slot\n"
            + "Invalid move. Play again. Movement must end on an empty slot\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O\n"
            + "   O O\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O\nScore: 14\n", outT.toString());
  }

  //tests that we can properly detect when a european game is over and produce the right output
  @Test
  public void testEuropeanGameOver() {
    MarbleSolitaireModel basic = new EuropeanSolitaireModel(0, 3);
    StringBuilder outE = new StringBuilder();
    StringBuilder input = new StringBuilder();
    //alignment and spacing issues:
    input.append("3 4 1 4 ");
    input.append("2 2 2 4 ");
    input.append("2 5 2 3 ");
    input.append("3 2 3 4 ");
    input.append("3 5 3 3 ");
    input.append("3 7 3 5 ");
    input.append("5 7 3 7 ");
    input.append("4 5 4 7 ");
    input.append("3 7 5 7 ");
    input.append("6 6 4 6 ");
    input.append("6 4 6 6 ");
    input.append("4 4 6 4 ");
    input.append("7 4 5 4 ");
    input.append("6 2 6 4 ");
    input.append("5 4 7 4 ");
    input.append("4 2 6 2 ");
    input.append("4 3 6 3 ");
    input.append("2 3 4 3 ");
    input.append("7 3 5 3 ");
    input.append("5 3 3 3 ");
    input.append("7 5 7 3");
    Reader inE = new StringReader(input.toString());
    MarbleSolitaireView textE = new MarbleSolitaireTextView(basic, outE);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, textE, inE);
    controller.playGame();
    String[] linesE = outE.toString().split("\n");
    assertEquals("Game over!", linesE[linesE.length - 9]);
    assertEquals("    O O O", linesE[linesE.length - 8]);
    assertEquals("  _ _ _ _ O", linesE[linesE.length - 7]);
    assertEquals("O _ O _ O _ _", linesE[linesE.length - 6]);
    assertEquals("O _ _ _ _ O _", linesE[linesE.length - 5]);
    assertEquals("O _ _ _ O _ O", linesE[linesE.length - 4]);
    assertEquals("  O _ _ _ O", linesE[linesE.length - 3]);
    assertEquals("    O _ _", linesE[linesE.length - 2]);
    assertEquals("Score: 15", linesE[linesE.length - 1]);
  }

  //tests that we can properly detect when a triangle game is done and have the right output
  @Test
  public void testTriangleGameOver() {
    StringBuilder input = new StringBuilder();
    MarbleSolitaireModel basicT = new TriangleSolitaireModel(3);
    StringBuilder outT = new StringBuilder();
    input.append("3 1 1 1 ");
    input.append("3 3 3 1 ");
    input.append("1 1 3 3 ");
    Reader inTr = new StringReader(input.toString());
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    String[] linesT = outT.toString().split("\n");
    assertEquals("Game over!", linesT[linesT.length - 5]);
    assertEquals("  _", linesT[linesT.length - 4]);
    assertEquals(" _ _", linesT[linesT.length - 3]);
    assertEquals("O _ O", linesT[linesT.length - 2]);
    assertEquals("Score: 2", linesT[linesT.length - 1]);
  }

  //tests that we can properly play a default board to the end
  @Test
  public void testTriangleGameOverDefault() {
    StringBuilder input = new StringBuilder();
    MarbleSolitaireModel basicT = new TriangleSolitaireModel(5);
    StringBuilder outT = new StringBuilder();
    input.append("3 3 1 1 ");
    input.append("3 1 3 3 ");
    input.append("4 4 2 2 ");
    input.append("5 4 3 2 ");
    input.append("5 2 5 4 ");
    input.append("5 5 5 3 ");
    input.append("5 3 3 1 ");
    input.append("3 1 3 3 ");
    input.append("2 2 4 4 ");
    input.append("1 1 3 1 ");
    input.append("4 1 2 1 ");
    Reader inTr = new StringReader(input.toString());
    MarbleSolitaireView textT = new TriangleSolitaireTextView(basicT, outT);
    MarbleSolitaireController controllerT = new MarbleSolitaireControllerImpl(basicT, textT, inTr);
    controllerT.playGame();
    String[] linesT = outT.toString().split("\n");
    assertEquals("Game over!", linesT[linesT.length - 7]);
    assertEquals("    _", linesT[linesT.length - 6]);
    assertEquals("   O _", linesT[linesT.length - 5]);
    assertEquals("  _ _ _", linesT[linesT.length - 4]);
    assertEquals(" _ _ _ O", linesT[linesT.length - 3]);
    assertEquals("O _ _ _ _", linesT[linesT.length - 2]);
    assertEquals("Score: 3", linesT[linesT.length - 1]);
  }
}
