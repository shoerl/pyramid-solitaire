import static junit.framework.TestCase.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

/**
 * Class respresenting PyramidSolitaireTextualView tests.
 */
public class PyramidSolitaireTextualViewTests {
  PyramidSolitaireModel<Card> game = new BasicPyramidSolitaire();
  PyramidSolitaireTextualView textualView = new PyramidSolitaireTextualView(game);
  List<Card> seanlist = game.getDeck();


  @Test(expected = NullPointerException.class)
  public void testConstructor() {
    assertEquals(new PyramidSolitaireTextualView(null), "");
  }

  @Test
  public void testPyramid() {
    assertEquals(textualView.toString(), "");
  }

  @Test
  public void testPyramid2() {
    game.startGame(seanlist, false, 3, 2);
    assertEquals(textualView.toString(), "    2♦\n"
        + "  3♦  4♦\n"
        + "5♦  6♦  7♦\n"
        + "Draw: 8♦, 9♦");
  }

  @Test
  public void testPyramid3() {
    game.startGame(seanlist, false, 3, 2);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(0);
    game.discardDraw(1);
    game.removeUsingDraw(1, 2, 2);
    assertEquals(textualView.toString(), "    2♦\n"
        + "  3♦  4♦\n"
        + "5♦  6♦    \n"
        + "Draw: 5♠, 7♠");
  }

  @Test
  public void testPyramid4() {
    game.startGame(seanlist, false, 1, 9);
    game.removeUsingDraw(8, 0, 0);
    assertEquals(textualView.toString(), "You win!");

  }

  @Test
  public void testPyramid5() {
    game.startGame(seanlist, false, 7, 4);
    for (int i = 0; i < 20; i++) {
      game.discardDraw(0);
    }
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    assertEquals(textualView.toString(), "Game Over. Score: 187");

  }

  @Test
  public void testPyramid6() throws IOException {
    StringBuilder a = new StringBuilder();
    game.startGame(seanlist, false, 3, 2);
    PyramidSolitaireTextualView textualViewNew = new PyramidSolitaireTextualView(game, a);
    textualViewNew.render();
  }

  @Test(expected = java.io.IOException.class)
  public void testRender1() throws IOException {
    new PyramidSolitaireTextualView(game, null).render();
  }




}
