import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the MarbleSolitaireControllerImpl constructors and methods.
 */
public class MarbleSolitaireControllerImplTest {
  //should throw an exception since model is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    MarbleSolitaireController noModel = new MarbleSolitaireControllerImpl(null,
            new MarbleSolitaireTextView(new EnglishSolitaireModel()), new StringReader("q"));
  }

  //should throw an exception since view is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    MarbleSolitaireController noView = new MarbleSolitaireControllerImpl(
            new EnglishSolitaireModel(), null, new StringReader("q"));
  }

  //should throw an exception since in (Readable) is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullReadable() {
    MarbleSolitaireController noIn = new MarbleSolitaireControllerImpl(
            new EnglishSolitaireModel(), new MarbleSolitaireTextView(new EnglishSolitaireModel()),
            null);
  }

  //should throw an exception since there is an incomplete amount of inputs to end the game
  @Test(expected = IllegalStateException.class)
  public void testInputRanOut() {
    MarbleSolitaireController badInputs = new MarbleSolitaireControllerImpl(
            new EnglishSolitaireModel(),
            new MarbleSolitaireTextView(new EnglishSolitaireModel(), new StringBuilder()),
            new StringReader("1 2"));
    badInputs.playGame();
  }

  //tests that the playGame method will throw an exception when fed a corrupted Readable
  @Test
  public void testPlayGameStateExceptionReadable() {
    MarbleSolitaireController corruptedGame = new MarbleSolitaireControllerImpl(
            new EnglishSolitaireModel(),
            new MarbleSolitaireTextView(new EnglishSolitaireModel(), new StringBuilder()),
            new CorruptedReadable());
    try {
      corruptedGame.playGame();
      fail("Game did not throw the right exception");
    } catch (IllegalStateException e) {
      assertEquals("input could not be read", e.getMessage());
    }
  }

  //tests that the playGame method will throw an exception when fed a corrupted Appendable
  @Test
  public void testPlayGameStateExceptionAppendable() {
    MarbleSolitaireController corruptedGame = new MarbleSolitaireControllerImpl(
            new EnglishSolitaireModel(),
            new MarbleSolitaireTextView(new EnglishSolitaireModel(), new CorruptedAppendable()),
            new StringReader("q"));
    try {
      corruptedGame.playGame();
      fail("Game did not throw the right exception");
    } catch (IllegalStateException e) {
      assertEquals("output could not be transmitted", e.getMessage());
    }
  }

  //tests that "q" will quit the game
  @Test
  public void testQuitFunctionLowercase() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader in = new StringReader("q 1 2 3 4");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //tests that "Q" will quit the game
  @Test
  public void testQuitFunctionUppercase() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    //inputs even after the quit should not be detected
    Reader in = new StringReader("Q 2 4 4 4");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //tests that quiting works for the fromCol input
  @Test
  public void testQuit2ndInput() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("1 Q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //tests that quiting works for the toRow input
  @Test
  public void testQuit3rdInput() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("1 1 q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //tests that quiting works for the toCol input
  @Test
  public void testQuit4thInput() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("1 1 1 Q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //runs through a single complete movement to see if it will work
  @Test
  public void testMovement() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    //should translate to (1, 3) -> (3, 3) on the model
    Reader in = new StringReader("2 4 4 4 q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 31\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 31\n", out.toString());
  }

  //checks that inputting a number less than 1 will block the movement and ask for re-entering
  @Test
  public void testBlockNonPositiveInputs() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    //adds an invalid input to each number to check that controller blocks at all points
    Reader in = new StringReader("0 6 -1 4 -5 4 -100 q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Please re-enter a valid input.\nPlease re-enter a valid input.\n"
            + "Please re-enter a valid input.\nPlease re-enter a valid input.\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //checks that inputting a non-number (minus "q" and "Q") will block the movement
  // and ask for re-entering
  @Test
  public void testBlockSomeNonNumberInputs() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    //adds an invalid input to each number to check that controller blocks at all points
    Reader in = new StringReader("+ 6 s 4 lettuce 4 $ q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Please re-enter a valid input.\nPlease re-enter a valid input.\n"
            + "Please re-enter a valid input.\nPlease re-enter a valid input.\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //tests that putting a wrong input does not cause the controller to restart its input of numbers
  //for example, inputting an invalid toRow should prompt again for toRow. Controller should not go
  //back to fromRow or ahead to toCol.
  @Test
  public void testInvalidInputsDoNotReset() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder out = new StringBuilder();
    //goes through and makes 4 mistakes, completes a movement, then quits
    //movement should be (3, 1) -> (3, 3)
    Reader in = new StringReader("0 4 -3 2 s 4 W 4 q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "Please re-enter a valid input.\nPlease re-enter a valid input.\n"
            + "Please re-enter a valid input.\nPlease re-enter a valid input.\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 31\n"
            + "Game quit!\nState of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 31\n", out.toString());
  }

  //checks that if a movement goes past the input stage but ends up being invalid, the controller
  //will send a message that the move is invalid, and then will begin looking again for inputs
  @Test
  public void testInvalidMoveDetection() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder input = new StringBuilder();
    //in order of moves: fRow too big, fCol too big, tRow too big, tCol too big, too far apart,
    //too close, starts Invalid, starts Empty, middle Empty, ends Invalid, ends Marble
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
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader(input.toString());
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
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
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n", out.toString());
  }

  //tests playing the game until the game is over
  @Test
  public void testPlayUntilGameOver() {
    MarbleSolitaireModel basic = new EnglishSolitaireModel();
    StringBuilder input = new StringBuilder();
    //combination of moves that will end the game, also testing that we can move properly
    // in all directions through controller inputs
    input.append("4 6 4 4 ");
    input.append("4 3 4 5 ");
    input.append("4 1 4 3 ");
    input.append("6 4 4 4 ");
    input.append("3 4 5 4 ");
    input.append("1 4 3 4");
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader(input.toString());
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O _ _ O _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 30\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "_ _ O _ O _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\nScore: 29\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "_ _ O O O _ O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\nScore: 28\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O _ O O O\n"
            + "_ _ O _ O _ O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\nScore: 27\n"
            + "Game over!\n"
            + "    O _ O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "_ _ O _ O _ O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\nScore: 26\n", out.toString());
  }

  //testing that the inputs fed by the controller to the model are correct by the use of a mock.
  @Test
  public void testInputsThroughMock() {
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel basic = new MockSolitaireModel(log);
    //mock should take 1 less than the user input for the controller
    Reader in = new StringReader("1 2 4 8 3 7 6 5 q");
    MarbleSolitaireView text = new MarbleSolitaireTextView(basic, new StringBuilder());
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(basic, text, in);
    controller.playGame();
    String[] lines = log.toString().split("\n");
    //first checks if game is over. It then displays the board, which calls on getBoardSize() once
    //in the TextView. Then, it gets the score.
    assertEquals("Checked if game was over.", lines[0]);
    assertEquals("Got board size.", lines[1]);
    assertEquals("Got score.", lines[2]);
    //makes a move, which works since the mock does not check for validity.
    assertEquals("fromRow = 0 fromCol = 1 toRow = 3 toCol = 7", lines[3]);
    assertEquals("Checked if game was over.", lines[4]);
    assertEquals("Got board size.", lines[5]);
    assertEquals("Got score.", lines[6]);
    assertEquals("fromRow = 2 fromCol = 6 toRow = 5 toCol = 4", lines[7]);
    //move was successful, so we check again for the game being over, and display the board/score.
    assertEquals("Checked if game was over.", lines[8]);
    assertEquals("Got board size.", lines[9]);
    assertEquals("Got score.", lines[10]);
    //quit the game. We check if the game is over to see what final message to transmit to the
    //view's log. Then, we display the final board and score.
    assertEquals("Checked if game was over.", lines[11]);
    assertEquals("Got board size.", lines[12]);
    assertEquals("Got score.", lines[13]);
  }
}