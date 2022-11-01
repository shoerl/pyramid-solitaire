import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.util.ArrayList;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * class representing and containing my relaxed pyramid solitaire tests.
 */
public class RelaxedPyramidSolitaireTests {

  RelaxedPyramidSolitaire game = new RelaxedPyramidSolitaire();
  //PyramidSolitaireModel<Card> model = new TriPeaksPyramidSolitaire();
  List<Card> seanlist = game.getDeck();
  PyramidSolitaireTextualView d = new PyramidSolitaireTextualView(game);


  @Test
  public void forMe() {
    game.startGame(seanlist, false, 4, 5);
    System.out.println(d.toString());
  }

  @Test
  public void testGetDeck() {
    Card c1 = new Card(Card.Val.Two, Card.Suit.Diamonds);
    TestCase.assertEquals(c1, seanlist.get(0));
  }

  @Test
  public void testRelaxedRules() {
    game.startGame(seanlist, false, 4, 5);
    game.removeUsingDraw(4, 3, 2);
    game.removeUsingDraw(4, 3, 1);
    game.removeUsingDraw(3, 3, 3);
    game.remove(2, 1, 2, 2);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.removeUsingDraw(2, 1, 1);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    game.discardDraw(4);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    game.discardDraw(4);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    game.discardDraw(4);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    game.discardDraw(4);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    game.discardDraw(4);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    game.discardDraw(4);
    game.discardDraw(0);
    game.discardDraw(1);
    game.discardDraw(2);
    game.discardDraw(3);
    // i am also testing that remove removes a relaxed pair properly
    game.remove(2, 0, 3, 0);
    assertNull(game.getCardAt(2, 0));
    game.discardDraw(4);
    // i am now testing that the game ends properly
    assertTrue(game.isGameOver());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testStartGame() {
    game.startGame(seanlist, false, 10, 3);
  }

  @Test
  public void testStartGameNoMutation() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, false, 9, 3);
    TestCase.assertEquals(c1, seanlist);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullDeck() {
    game.startGame(null, false, 10, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameEmptyList() {
    List<Card> loc = new ArrayList<Card>();
    game.startGame(loc, false, 10, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameBigDeck() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, false, 9, 3);
    c1.add(game.getDeck().get(2));
    game.startGame(c1, false, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameSmallDeck() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, false, 9, 3);
    c1.remove(1);
    game.startGame(c1, false, 7, 3);
  }

  @Test
  public void testStartGameShuffle() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, true, 9, 3);
    Assert.assertNotSame(seanlist, game.getDeck());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameTooManyDraw() {
    game.startGame(seanlist, false, 9, 50);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullItemInList() {
    seanlist.add(null);
    seanlist.remove(0);
    game.startGame(seanlist, false, 9, 50);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullList() {
    List<Card> cl = null;
    game.startGame(cl, false, 7, 3);

  }

  @Test
  public void testStartGameRuns() {

    game.startGame(seanlist, false, 7, 0);

    TestCase.assertNotNull(game);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove2NotValidMove() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRemoveGameNotStarted() {
    game.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNotExposed() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInvalidIndex() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(7, 0, 6, 7);
  }

  @Test
  public void testRemoveWorks() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 3);
    TestCase.assertEquals(null, game.getCardAt(6, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove1NotValid() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove1NotValid2() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove1InvalidIndex() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(4, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove1InvalidIndex2() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(9, 3);
  }

  @Test(expected = IllegalStateException.class)
  public void testIsGameOverNotStarted() {
    game.isGameOver();
  }

  @Test
  public void testIsGameOverWorks() {
    game.startGame(seanlist, false, 3, 15);
    TestCase.assertEquals(false, game.isGameOver());
  }

}
