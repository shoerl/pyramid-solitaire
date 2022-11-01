
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksPyramidSolitaire;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for controller. Tests that the game can be
 * played properly. Many permutations of the game are
 * tested within this class.
 */
public class ControllerTests {
  BasicPyramidSolitaire s = new BasicPyramidSolitaire();
  TriPeaksPyramidSolitaire triPeak = new TriPeaksPyramidSolitaire();
  RelaxedPyramidSolitaire relax = new RelaxedPyramidSolitaire();
  Readable in = new StringReader("");
  Appendable out = new StringBuilder();
  PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
  List<Card> dec = s.getDeck();

  /**
   * main method so i can play the solitaire game.
   *
   * @param args none.
   **/

  public static void main(String[] args) {

    //RelaxedPyramidSolitaire s = new RelaxedPyramidSolitaire();
    TriPeaksPyramidSolitaire s = new TriPeaksPyramidSolitaire();
    Appendable out = new PrintStream(System.out);
    Readable in = new InputStreamReader(System.in);
    //Readable in = new StringReader("");
    //Appendable out = new StringBuilder();
    List<Card> dec = s.getDeck();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(s, dec, true, 7, 5);
  }


  // Test that tests the constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    new PyramidSolitaireTextualController(null, null);
  }

  // Test that tests the constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new PyramidSolitaireTextualController(new StringReader(""), null);
  }

  // Test that tests the constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new PyramidSolitaireTextualController(null, new StringBuilder(""));
  }

  // Test that the controller recieves input properly
  @Test
  public void testInputs() throws IOException {
    Reader in = new StringReader("3 4");
    StringBuilder dontCareOutput = new StringBuilder();
    MockController controller5 = new MockController(in, dontCareOutput);

    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel calc = new MockModel(log);

    controller5.goGetCard(calc);
    assertEquals("row = 2, card = 3\n", log.toString());
  }

  // Test that the controller recieves input properly
  @Test
  public void testInput2s() throws IOException {
    Reader in = new StringReader("3 4 4 6");
    StringBuilder dontCareOutput = new StringBuilder();
    MockController controller5 = new MockController(in, dontCareOutput);

    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel calc = new MockModel(log);

    controller5.goRemove(calc);
    assertEquals("row1 = 2, card1 = 3, row2 = 3, card2 = 5\n", log.toString());
  }

  //test that the game draws correctly when game is quit
  @Test
  public void testDrawCorrectlyWhenQuit() throws IOException {
    testRun(s,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"));
  }

  //test that it draws correctly when bad input
  @Test
  public void testDrawCorrectlyWhenBadInput() throws IOException {
    testRun(s,
              Interaction.prints("      2♦\n"
                  + "    3♦  4♦\n"
                  + "  5♦  6♦  7♦\n"
                  + "8♦  9♦  10♦ J♦\n"
                  + "Draw: Q♦, K♦\n"
                  + "Score: 65"),
              Interaction.inputs("rm1 3 7\n"),
              Interaction.prints("Invalid move. Play again. Move is invalid"),
              Interaction.inputs("q\n"),
              Interaction.prints("Game quit!\n"
                  + "State of game when quit:\n"
                  + "      2♦\n"
                  + "    3♦  4♦\n"
                  + "  5♦  6♦  7♦\n"
                  + "8♦  9♦  10♦ J♦\n"
                  + "Draw: Q♦, K♦\n"
                  + "Score: 65"));
  }

  //test that it draws correctly when another bad input
  @Test
  public void testDrawCorrectlyWhenBadInput2() throws IOException {
    testRun(s,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("rm1 3 -50\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"));
  }

  //test that you can play and finish a game correctly (with correct drawings)
  @Test
  public void testDrawCorrectlyWhenFinishGame() throws IOException {
    testRun(s,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, A♦\n"
            + "Score: 65"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, 2♠\n"
            + "Score: 65"),
        Interaction.inputs("rmwd 2 4 4\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦\n"
            + "Draw: Q♦, 3♠\n"
            + "Score: 54"),
        Interaction.inputs("rmwd 2 4 3\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦\n"
            + "Draw: Q♦, 4♠\n"
            + "Score: 44"),
        Interaction.inputs("rmwd 2 4 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦\n"
            + "Draw: Q♦, 5♠\n"
            + "Score: 35"),
        Interaction.inputs("rmwd 2 4 1\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "\n"
            + "Draw: Q♦, 6♠\n"
            + "Score: 27"),
        Interaction.inputs("rmwd 2 3 3\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦\n"
            + "\n"
            + "Draw: Q♦, 7♠\n"
            + "Score: 20"),
        Interaction.inputs("rmwd 2 3 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦\n"
            + "\n"
            + "Draw: Q♦, 8♠\n"
            + "Score: 14"),
        Interaction.inputs("rmwd 2 3 1\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  \n"
            + "\n"
            + "Draw: Q♦, 9♠\n"
            + "Score: 9"),
        Interaction.inputs("rmwd 2 2 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦\n"
            + "  \n"
            + "\n"
            + "Draw: Q♦, 10♠\n"
            + "Score: 5"),
        Interaction.inputs("rmwd 2 2 1\n"),
        Interaction.prints("      2♦\n"
            + "    \n"
            + "  \n"
            + "\n"
            + "Draw: Q♦, J♠\n"
            + "Score: 2"),
        Interaction.inputs("rmwd 2 1 1\n"),
        Interaction.prints("You win!"));
  }

  //test that the game draws correctly when you lose
  @Test
  public void testDrawCorrectlyLose() throws IOException {
    testRun2(s,
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, K♥, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, 4♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, 5♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, 6♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, 7♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, 8♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, 9♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, 10♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, J♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, Q♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, K♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, A♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw:   , A♣, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 3\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw:   , A♣,   , 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 4\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw:   , A♣,   ,   , 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 5\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw:   , A♣,   ,   ,   \n"
            + "Score: 247"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw:   ,   ,   ,   ,   \n"
            + "Score: 247"),
        Interaction.inputs("rm2 8 2 8 5\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥      6♥  7♥      9♥  10♥ J♥\n"
            + "Draw:   ,   ,   ,   ,   \n"
            + "Score: 234"),
        Interaction.inputs("rm2 8 1 8 6\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "        6♥  7♥          10♥ J♥\n"
            + "Draw:   ,   ,   ,   ,   \n"
            + "Score: 221"),
        Interaction.inputs("rm2 8 3 8 4\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw:   ,   ,   ,   ,   \n"
            + "Score: 208"),
        Interaction.inputs("rm1 7 4\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠      A♠  2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw:   ,   ,   ,   ,   \n"
            + "Score: 195"),
        Interaction.inputs("rm2 7 3 7 5\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠              2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw:   ,   ,   ,   ,   \n"
            + "Score: 182"),
        Interaction.inputs("rm2 6 3 6 4\n"),
        Interaction.prints("Game over. Score: 169"));
  }

  //test that the game draws correctly when you remove cards properly
  @Test
  public void testDrawCorrectlyWhenRemove() throws IOException {
    testRun2(s,
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥, K♥, A♥, 2♣, 3♣\n"
            + "Score: 247"),
        Interaction.inputs("rm2 8 1 8 6\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "    5♥  6♥  7♥  8♥      10♥ J♥\n"
            + "Draw: Q♥, K♥, A♥, 2♣, 3♣\n"
            + "Score: 234"),
        Interaction.inputs("rm2 8 2 8 5\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "        6♥  7♥          10♥ J♥\n"
            + "Draw: Q♥, K♥, A♥, 2♣, 3♣\n"
            + "Score: 221"),
        Interaction.inputs("rm2 8 3 8 4\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw: Q♥, K♥, A♥, 2♣, 3♣\n"
            + "Score: 208"),
        Interaction.inputs("rm1 7 4\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠      A♠  2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw: Q♥, K♥, A♥, 2♣, 3♣\n"
            + "Score: 195"),
        Interaction.inputs("q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠      A♠  2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw: Q♥, K♥, A♥, 2♣, 3♣\n"
            + "Score: 195"));
  }

  //test various incorrect inputs for rm1
  @Test
  public void testRemove1BadInput() throws IOException {
    testRun(s,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("rm1 5 7\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm1 5 -11\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm1 -11 5\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm1 e t 3 3\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm1 3 1\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm1 q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"));
  }

  //test various incorrect inputs for rm2
  @Test
  public void testRemove2BadInput() throws IOException {
    testRun(s,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("rm2 5 7 1 9\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm2 5 -11 -1 -5\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm2 -9 0 2 4\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm2 e t 3 3 p 4 u 7\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm2 0 0 0 0\n"),
        Interaction.prints("Invalid move. Play again. Move is invalid"),
        Interaction.inputs("rm2 q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"));

  }

  //test various incorrect inputs for rmwd
  @Test
  public void testRemoveWithDrawBadInput() throws IOException {
    testRun(s,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("rmwd 9 5 7\n"),
        Interaction.prints("Invalid move. Play again. Inputted number "
            + "of rows or cards is incorrect"),
        Interaction.inputs("rmwd 2 -11 0\n"),
        Interaction.prints("Invalid move. Play again. Inputted number"
            + " of rows or cards is incorrect"),
        Interaction.inputs("rmwd 0 0 0\n"),
        Interaction.prints("Invalid move. Play again. Inputted number"
            + " of rows or cards is incorrect"),
        Interaction.inputs("rmwd e t 3 3 o 2\n"),
        Interaction.prints("Invalid move. Play again. Inputted number "
            + "of rows or cards is incorrect"),
        Interaction.inputs("rmwd 3 1 9\n"),
        Interaction.prints("Invalid move. Play again. Inputted number "
            + "of rows or cards is incorrect"),
        Interaction.inputs("rmwd q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"));
  }

  //test various incorrect inputs for dd
  @Test
  public void testDiscardDrawBadInput() throws IOException {
    testRun(s,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("dd 0\n"),
        Interaction.prints("Invalid move. Play again. Index is invalid"),
        Interaction.inputs("dd -11\n"),
        Interaction.prints("Invalid move. Play again. Index is invalid"),
        Interaction.inputs("dd 80\n"),
        Interaction.prints("Invalid move. Play again. Index is invalid"),
        Interaction.inputs("dd e p m 9\n"),
        Interaction.prints("Invalid move. Play again. Index is invalid"),
        Interaction.inputs("dd 50\n"),
        Interaction.prints("Invalid move. Play again. Index is invalid"),
        Interaction.inputs("dd q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"));
  }

  //test that the game draws correctly when game is quit in Relax mode
  @Test
  public void testDrawCorrectlyWhenQuitRelaxed() throws IOException {
    testRun(relax,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"));
  }


  @Test
  public void testDrawCorrectlyWhenLoseRelaxed() throws IOException {
    testRunLose(relax,
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♥\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: K♥\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: A♥\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 2♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 3♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 4♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 5♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 6♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 7♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 8♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 9♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: 10♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: J♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: Q♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: K♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw: A♣\n"
            + "Score: 247"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n"
            + "Draw:   \n"
            + "Score: 247"),
        Interaction.inputs("rm2 8 3 8 4\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥  5♥          8♥  9♥  10♥ J♥\n"
            + "Draw:   \n"
            + "Score: 234"),
        Interaction.inputs("rm2 8 2 8 5\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "4♥                  9♥  10♥ J♥\n"
            + "Draw:   \n"
            + "Score: 221"),
        Interaction.inputs("rm2 8 1 8 6\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠  K♠  A♠  2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw:   \n"
            + "Score: 208"),
        Interaction.inputs("rm1 7 4\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠  Q♠      A♠  2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw:   \n"
            + "Score: 195"),
        Interaction.inputs("rm2 7 3 7 5\n"),
        Interaction.prints("              2♦\n"
            + "            3♦  4♦\n"
            + "          5♦  6♦  7♦\n"
            + "        8♦  9♦  10♦ J♦\n"
            + "      Q♦  K♦  A♦  2♠  3♠\n"
            + "    4♠  5♠  6♠  7♠  8♠  9♠\n"
            + "  10♠ J♠              2♥  3♥\n"
            + "                        10♥ J♥\n"
            + "Draw:   \n"
            + "Score: 182"),
        Interaction.inputs("rm2 6 3 6 4\n"),
        Interaction.prints("Game over. Score: 169"));
  }


  //test that the game draws correctly when game is won in relax mode
  @Test
  public void testDrawCorrectlyWhenWinRelaxed() throws IOException {
    testRun(relax,
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: Q♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: A♦, K♦\n"
            + "Score: 65"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: 2♠, K♦\n"
            + "Score: 65"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦  10♦ J♦\n"
            + "Draw: 2♠, 3♠\n"
            + "Score: 65"),
        Interaction.inputs("rmwd 2 4 3\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦      J♦\n"
            + "Draw: 2♠, 4♠\n"
            + "Score: 55"),
        Interaction.inputs("rmwd 1 4 4\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦  9♦\n"
            + "Draw: 5♠, 4♠\n"
            + "Score: 44"),
        Interaction.inputs("rmwd 2 4 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦  6♦  7♦\n"
            + "8♦\n"
            + "Draw: 5♠, 6♠\n"
            + "Score: 35"),
        Interaction.inputs("rm2 3 2 3 3\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  5♦\n"
            + "8♦\n"
            + "Draw: 5♠, 6♠\n"
            + "Score: 22"),
        Interaction.inputs("rm2 4 1 3 1\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  \n"
            + "\n"
            + "Draw: 5♠, 6♠\n"
            + "Score: 9"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  \n"
            + "\n"
            + "Draw: 7♠, 6♠\n"
            + "Score: 9"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  \n"
            + "\n"
            + "Draw: 7♠, 8♠\n"
            + "Score: 9"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  \n"
            + "\n"
            + "Draw: 9♠, 8♠\n"
            + "Score: 9"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦  4♦\n"
            + "  \n"
            + "\n"
            + "Draw: 9♠, 10♠\n"
            + "Score: 9"),
        Interaction.inputs("rmwd 1 2 2\n"),
        Interaction.prints("      2♦\n"
            + "    3♦\n"
            + "  \n"
            + "\n"
            + "Draw: J♠, 10♠\n"
            + "Score: 5"),
        Interaction.inputs("rmwd 2 2 1\n"),
        Interaction.prints("      2♦\n"
            + "    \n"
            + "  \n"
            + "\n"
            + "Draw: J♠, Q♠\n"
            + "Score: 2"),
        Interaction.inputs("rmwd 1 1 1\n"),
        Interaction.prints("You win!"));
  }



  //test that the game draws correctly when game is quit in TriPeak mode
  @Test
  public void testDrawCorrectlyWhenQuitTriPeaks() throws IOException {
    testRun(triPeak,
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n"
            + "Draw: K♠, A♠\n"
            + "Score: 168"),
        Interaction.inputs("q\n"),
        Interaction.prints("Game quit!\n"
            + "State of game when quit:\n"
            + "      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n"
            + "Draw: K♠, A♠\n"
            + "Score: 168"));
  }


  //test that the game draws correctly when game is quit in TriPeak mode
  @Test
  public void testDrawCorrectlyWhenWinTriPeaks() throws IOException {
    testRun(triPeak,
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n"
            + "Draw: K♠, A♠, 2♥, 3♥, 4♥\n"
            + "Score: 168"),
        Interaction.inputs("rm2 4 2 4 3\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "5♠          8♠  9♠  10♠ J♠  Q♠\n"
            + "Draw: K♠, A♠, 2♥, 3♥, 4♥\n"
            + "Score: 155"),
        Interaction.inputs("rm2 4 1 4 4\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "                9♠  10♠ J♠  Q♠\n"
            + "Draw: K♠, A♠, 2♥, 3♥, 4♥\n"
            + "Score: 142"),
        Interaction.inputs("rmwd 5 4 5\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "                    10♠ J♠  Q♠\n"
            + "Draw: K♠, A♠, 2♥, 3♥, 5♥\n"
            + "Score: 133"),
        Interaction.inputs("rmwd 4 4 6\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "                        J♠  Q♠\n"
            + "Draw: K♠, A♠, 2♥, 6♥, 5♥\n"
            + "Score: 123"),
        Interaction.inputs("rmwd 3 4 7\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "                            Q♠\n"
            + "Draw: K♠, A♠, 7♥, 6♥, 5♥\n"
            + "Score: 112"),
        Interaction.inputs("rmwd 2 4 8\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦  K♦  A♦  2♠  3♠  4♠\n"
            + "\n"
            + "Draw: K♠, 8♥, 7♥, 6♥, 5♥\n"
            + "Score: 100"),
        Interaction.inputs("rm1 3 3\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦  Q♦      A♦  2♠  3♠  4♠\n"
            + "\n"
            + "Draw: K♠, 8♥, 7♥, 6♥, 5♥\n"
            + "Score: 87"),
        Interaction.inputs("rm2 3 2 3 4\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "  J♦              2♠  3♠  4♠\n"
            + "\n"
            + "Draw: K♠, 8♥, 7♥, 6♥, 5♥\n"
            + "Score: 74"),
        Interaction.inputs("rm2 3 1 3 5\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦  6♦  7♦  8♦  9♦  10♦\n"
            + "                      3♠  4♠\n"
            + "\n"
            + "Draw: K♠, 8♥, 7♥, 6♥, 5♥\n"
            + "Score: 61"),
        Interaction.inputs("rm2 2 2 2 3\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "    5♦          8♦  9♦  10♦\n"
            + "                      3♠  4♠\n"
            + "\n"
            + "Draw: K♠, 8♥, 7♥, 6♥, 5♥\n"
            + "Score: 48"),
        Interaction.inputs("rm2 2 1 2 4\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "                    9♦  10♦\n"
            + "                      3♠  4♠\n"
            + "\n"
            + "Draw: K♠, 8♥, 7♥, 6♥, 5♥\n"
            + "Score: 35"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "                    9♦  10♦\n"
            + "                      3♠  4♠\n"
            + "\n"
            + "Draw: 9♥, 8♥, 7♥, 6♥, 5♥\n"
            + "Score: 35"),
        Interaction.inputs("dd 5\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "                    9♦  10♦\n"
            + "                      3♠  4♠\n"
            + "\n"
            + "Draw: 9♥, 8♥, 7♥, 6♥, 10♥\n"
            + "Score: 35"),
        Interaction.inputs("rmwd 5 3 6\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "                    9♦  10♦\n"
            + "                          4♠\n"
            + "\n"
            + "Draw: 9♥, 8♥, 7♥, 6♥, J♥\n"
            + "Score: 32"),
        Interaction.inputs("rmwd 1 3 7\n"),
        Interaction.prints("      2♦      3♦      4♦\n"
            + "                    9♦  10♦\n"
            + "  \n"
            + "\n"
            + "Draw: Q♥, 8♥, 7♥, 6♥, J♥\n"
            + "Score: 28"),
        Interaction.inputs("rm2 1 3 2 6\n"),
        Interaction.prints("      2♦              4♦\n"
            + "                    9♦\n"
            + "  \n"
            + "\n"
            + "Draw: Q♥, 8♥, 7♥, 6♥, J♥\n"
            + "Score: 15"),
        Interaction.inputs("rmwd 5 1 1\n"),
        Interaction.prints("                      4♦\n"
            + "                    9♦\n"
            + "  \n"
            + "\n"
            + "Draw: Q♥, 8♥, 7♥, 6♥, K♥\n"
            + "Score: 13"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("                      4♦\n"
            + "                    9♦\n"
            + "  \n"
            + "\n"
            + "Draw: A♥, 8♥, 7♥, 6♥, K♥\n"
            + "Score: 13"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("                      4♦\n"
            + "                    9♦\n"
            + "  \n"
            + "\n"
            + "Draw: A♥, 2♣, 7♥, 6♥, K♥\n"
            + "Score: 13"),
        Interaction.inputs("dd 3\n"),
        Interaction.prints("                      4♦\n"
            + "                    9♦\n"
            + "  \n"
            + "\n"
            + "Draw: A♥, 2♣, 3♣, 6♥, K♥\n"
            + "Score: 13"),
        Interaction.inputs("dd 3\n"),
        Interaction.prints("                      4♦\n"
            + "                    9♦\n"
            + "  \n"
            + "\n"
            + "Draw: A♥, 2♣, 4♣, 6♥, K♥\n"
            + "Score: 13"),
        Interaction.inputs("rmwd 3 2 5\n"),
        Interaction.prints("                      4♦\n"
            + "    \n"
            + "  \n"
            + "\n"
            + "Draw: A♥, 2♣, 5♣, 6♥, K♥\n"
            + "Score: 4"),
        Interaction.inputs("dd 1\n"),
        Interaction.prints("                      4♦\n"
            + "    \n"
            + "  \n"
            + "\n"
            + "Draw: 6♣, 2♣, 5♣, 6♥, K♥\n"
            + "Score: 4"),
        Interaction.inputs("dd 2\n"),
        Interaction.prints("                      4♦\n"
            + "    \n"
            + "  \n"
            + "\n"
            + "Draw: 6♣, 7♣, 5♣, 6♥, K♥\n"
            + "Score: 4"),
        Interaction.inputs("dd 3\n"),
        Interaction.prints("                      4♦\n"
            + "    \n"
            + "  \n"
            + "\n"
            + "Draw: 6♣, 7♣, 8♣, 6♥, K♥\n"
            + "Score: 4"),
        Interaction.inputs("dd 3\n"),
        Interaction.prints("                      4♦\n"
            + "    \n"
            + "  \n"
            + "\n"
            + "Draw: 6♣, 7♣, 9♣, 6♥, K♥\n"
            + "Score: 4"),
        Interaction.inputs("rmwd 3 1 5\n"),
        Interaction.prints("You win!"));
  }


  void testRun(PyramidSolitaireModel model, Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();


    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    PyramidSolitaireTextualController controller =
        new PyramidSolitaireTextualController(input, actualOutput);
    controller.playGame(model, model.getDeck(), false, 4, 5);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  void testRunLose(PyramidSolitaireModel model, Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();


    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    PyramidSolitaireTextualController controller =
        new PyramidSolitaireTextualController(input, actualOutput);
    controller.playGame(model, model.getDeck(), false, 8, 1);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  void testRun2(PyramidSolitaireModel model, Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();


    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    PyramidSolitaireTextualController controller =
        new PyramidSolitaireTextualController(input, actualOutput);
    controller.playGame(model, model.getDeck(), false, 8, 5);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }



}
