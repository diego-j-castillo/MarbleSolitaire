import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the MarbleSolitaireTextView class, its constructor, and its methods.
 */
public class MarbleSolitaireTextViewTest {
  //exception should be thrown if we provide a null model
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullModel() {
    MarbleSolitaireView viewNull = new MarbleSolitaireTextView(null);
  }

  //exception should be thrown if we provide a null Appendable for a 2 args constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2ArgsExceptionNullModel() {
    MarbleSolitaireView modelNull = new MarbleSolitaireTextView(null, new StringBuilder());
  }

  //exception should be thrown if we provide a null Appendable for a 2 args constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2ArgsExceptionNullAppendable() {
    MarbleSolitaireView appNull = new MarbleSolitaireTextView(new EnglishSolitaireModel(), null);
  }

  //checks that the toString() method works for the English version of the Model
  @Test
  public void testToStringEnglishModel() {
    //test view for basic English model
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    MarbleSolitaireView basicView = new MarbleSolitaireTextView(basic);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", basicView.toString());
    //checks that the method works when starting out with the empty slot not in the center
    MarbleSolitaireView cCenterView = new MarbleSolitaireTextView(new EnglishSolitaireModel(2, 2));
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", cCenterView.toString());
    //checks that it can handle movement and multiple empty spaces
    basic.move(5, 3, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O", basicView.toString());
    //checks we can display for different sizes
    MarbleSolitaireView fiveView = new MarbleSolitaireTextView((new EnglishSolitaireModel(5)));
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", fiveView.toString());
    MarbleSolitaireModel custom5E = new EnglishSolitaireModel(5, 6, 12);
    //checks that empty slots on the right edge show properly
    MarbleSolitaireView fiveView2 = new MarbleSolitaireTextView(custom5E);
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O _\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", fiveView2.toString());
    custom5E.move(4, 12, 6, 12);
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O _\n"
            + "O O O O O O O O O O O O _\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", fiveView2.toString());
    //works for 7 armThickness board
    MarbleSolitaireView sevView = new MarbleSolitaireTextView((new EnglishSolitaireModel(7)));
    assertEquals("            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O _ O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O", sevView.toString());
  }

  //tests that the toString() works for European models
  @Test
  public void testToStringEuropeanModel() {
    MarbleSolitaireModel basic = new EuropeanSolitaireModel();
    MarbleSolitaireView basicView = new MarbleSolitaireTextView(basic);
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", basicView.toString());
    basic.move(3, 1, 3, 3);
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", basicView.toString());
    MarbleSolitaireModel fiver = new EuropeanSolitaireModel(5, 6, 12);
    MarbleSolitaireView fiveView = new MarbleSolitaireTextView(fiver);
    assertEquals("        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O _\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", fiveView.toString());
    fiver.move(4, 12, 6, 12);
    assertEquals("        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O _\n"
            + "O O O O O O O O O O O O _\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", fiveView.toString());
    assertEquals("            O O O O O O O\n"
                    + "          O O O O O O O O O\n"
                    + "        O O O O O O O O O O O\n"
                    + "      O O O O O O O O O O O O O\n"
                    + "    O O O O O O O O O O O O O O O\n"
                    + "  O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O _ O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O\n"
                    + "  O O O O O O O O O O O O O O O O O\n"
                    + "    O O O O O O O O O O O O O O O\n"
                    + "      O O O O O O O O O O O O O\n"
                    + "        O O O O O O O O O O O\n"
                    + "          O O O O O O O O O\n"
                    + "            O O O O O O O",
            new MarbleSolitaireTextView(new EuropeanSolitaireModel(7)).toString());
  }

  //tests that the renderBoard() method lines up with what toString should output
  @Test
  public void testRenderBoardEnglishModel() {
    //works for a model of size 3
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView test3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), out);
    try {
      test3.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", out.toString());
    //continues to work after a move has been made
    StringBuilder out3Move = new StringBuilder();
    MarbleSolitaireModel game = new EnglishSolitaireModel(2, 2);
    MarbleSolitaireView test3Move = new MarbleSolitaireTextView(game, out3Move);
    game.move(0, 2, 2, 2);
    try {
      test3Move.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("    _ O O\n"
            + "    _ O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", out3Move.toString());
    //works for a model of size 5
    StringBuilder out5 = new StringBuilder();
    MarbleSolitaireView test5 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5), out5);
    try {
      test5.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", out5.toString());
  }

  //tests the render board method for a European model
  @Test
  public void testRenderBoardEuropean() {
    //works for a model of size 3
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView test3 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(), out);
    try {
      test3.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", out.toString());
    //continues to work after a move has been made
    StringBuilder out3Move = new StringBuilder();
    MarbleSolitaireModel game = new EuropeanSolitaireModel(2, 2);
    MarbleSolitaireView test3Move = new MarbleSolitaireTextView(game, out3Move);
    game.move(0, 2, 2, 2);
    try {
      test3Move.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("    _ O O\n"
            + "  O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", out3Move.toString());
    //works for a model of size 5
    StringBuilder out5 = new StringBuilder();
    MarbleSolitaireView test5 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(5), out5);
    try {
      test5.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", out5.toString());
  }

  //tests that the renderMessage() method properly renders the given message
  @Test
  public void testRenderMessage() {
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView test = new MarbleSolitaireTextView(new EnglishSolitaireModel(), out);
    try {
      test.renderMessage("Hello");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("Hello", out.toString());
    try {
      test.renderMessage(", World");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("Hello, World", out.toString());
    try {
      test.renderMessage("\nSkim milk");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("Hello, World\n"
            + "Skim milk", out.toString());
  }

  //checks that an IOException is thrown for the renderBoard() method
  @Test
  public void testRenderBoardIOException() {
    MarbleSolitaireView corruptedApp = new MarbleSolitaireTextView(
            new EnglishSolitaireModel(), new CorruptedAppendable());
    try {
      corruptedApp.renderBoard();
      fail("Board was somehow rendered successfully");
    } catch (IOException e) {
      assertEquals("IOException thrown", e.getMessage());
    }
  }

  //checks that an IOException is thrown for the renderMessage() method
  @Test
  public void testRenderMessageIOException() {
    MarbleSolitaireView corruptedApp = new MarbleSolitaireTextView(
            new EnglishSolitaireModel(), new CorruptedAppendable());
    try {
      corruptedApp.renderMessage("Hello");
      fail("Message was somehow rendered successfully");
    } catch (IOException e) {
      assertEquals("IOException thrown", e.getMessage());
    }
  }
}