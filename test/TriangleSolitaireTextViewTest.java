import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the TriangleSolitaireTextView class, its constructor, and its methods.
 */
public class TriangleSolitaireTextViewTest {
  //exception should be thrown if we provide a null model
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullModel() {
    MarbleSolitaireView viewNull = new TriangleSolitaireTextView(null);
  }

  //exception should be thrown if we provide a null Appendable for a 2 args constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2ArgsExceptionNullModel() {
    MarbleSolitaireView modelNull = new TriangleSolitaireTextView(null, new StringBuilder());
  }

  //exception should be thrown if we provide a null Appendable for a 2 args constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2ArgsExceptionNullAppendable() {
    MarbleSolitaireView appNull = new TriangleSolitaireTextView(new TriangleSolitaireModel(), null);
  }

  //checks that the toString() method works for the TriangleSolitaireModel
  @Test
  public void testToString() {
    MarbleSolitaireModel basic = new TriangleSolitaireModel();
    MarbleSolitaireView basicView = new TriangleSolitaireTextView(basic);
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", basicView.toString());
    basic.move(2, 2, 0, 0);
    assertEquals("    O\n"
            + "   O _\n"
            + "  O O _\n"
            + " O O O O\n"
            + "O O O O O", basicView.toString());
    MarbleSolitaireView sevenView = new TriangleSolitaireTextView(new TriangleSolitaireModel(7));
    assertEquals("      _\n"
            + "     O O\n"
            + "    O O O\n"
            + "   O O O O\n"
            + "  O O O O O\n"
            + " O O O O O O\n"
            + "O O O O O O O", sevenView.toString());
    MarbleSolitaireView fourteenView = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(14, 13, 7));
    assertEquals("             O\n"
            + "            O O\n"
            + "           O O O\n"
            + "          O O O O\n"
            + "         O O O O O\n"
            + "        O O O O O O\n"
            + "       O O O O O O O\n"
            + "      O O O O O O O O\n"
            + "     O O O O O O O O O\n"
            + "    O O O O O O O O O O\n"
            + "   O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O O\n"
            + " O O O O O O O O O O O O O\n"
            + "O O O O O O O _ O O O O O O", fourteenView.toString());
  }

  //tests that the renderBoard() method lines up with what toString should output
  @Test
  public void testRenderBoard() {
    //works for a model of size 3
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView test3 = new TriangleSolitaireTextView(new TriangleSolitaireModel(), out);
    try {
      test3.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", out.toString());
    //continues to work after a move has been made
    StringBuilder out3Move = new StringBuilder();
    MarbleSolitaireModel game = new TriangleSolitaireModel(2, 2);
    MarbleSolitaireView test3Move = new TriangleSolitaireTextView(game, out3Move);
    game.move(0, 0, 2, 2);
    try {
      test3Move.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("    _\n"
            + "   O _\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", out3Move.toString());
    //works for a model of size 5
    StringBuilder out5 = new StringBuilder();
    MarbleSolitaireView test5 = new TriangleSolitaireTextView(new TriangleSolitaireModel(7), out5);
    try {
      test5.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("      _\n"
            + "     O O\n"
            + "    O O O\n"
            + "   O O O O\n"
            + "  O O O O O\n"
            + " O O O O O O\n"
            + "O O O O O O O", out5.toString());
  }

  //tests that the renderMessage() method properly renders the given message
  @Test
  public void testRenderMessage() {
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView test = new TriangleSolitaireTextView(new TriangleSolitaireModel(), out);
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
      test.renderMessage("\nTriangle milk");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("Hello, World\n"
            + "Triangle milk", out.toString());
  }

  //checks that an IOException is thrown for the renderBoard() method
  @Test
  public void testRenderBoardIOException() {
    MarbleSolitaireView corruptedApp = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(), new CorruptedAppendable());
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
    MarbleSolitaireView corruptedApp = new TriangleSolitaireTextView(
            new TriangleSolitaireModel(), new CorruptedAppendable());
    try {
      corruptedApp.renderMessage("Hello");
      fail("Message was somehow rendered successfully");
    } catch (IOException e) {
      assertEquals("IOException thrown", e.getMessage());
    }
  }
}
