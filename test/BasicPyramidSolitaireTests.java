import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw02.Card.Val;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

/**
 * Class respresenting the tests for my BasicPyramidSolitaire class. This allows
 * the game to be tested.
 */
public class BasicPyramidSolitaireTests {

  BasicPyramidSolitaire game = new BasicPyramidSolitaire();
  List<Card> seanlist = game.getDeck();

  /**
   * For myself.
  public void initGame() {
    System.out.println(seanlist);
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 0, 6, 6);
    game.remove(6, 3);
    game.remove(6, 2, 6, 4);
    game.remove(6, 1, 6, 5);
    game.remove(5, 0, 5, 5);
    game.remove(5,1, 5, 4);
    game.remove(5, 2, 5, 3);
    game.remove(4, 0, 4, 2);
    game.remove(4, 1);
    game.discardDraw(2);
    game.discardDraw(2);
    game.discardDraw(2);
    game.discardDraw(2);
   // game.removeUsingDraw(2, 4, 4);
    //game.removeUsingDraw(2, 4, 3);
    PyramidSolitaireTextualView s = new PyramidSolitaireTextualView(game);
    System.out.println(s.toString());

  }
   **/

  @Test
  public void testGetDeck() {
    Card c1 = new Card(Card.Val.Two, Card.Suit.Diamonds);
    assertEquals(c1, game.getDeck().get(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame() {
    game.startGame(seanlist, false, 10, 3);
  }

  @Test
  public void testStartGame1() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, false, 9, 3);
    assertEquals(c1, seanlist);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame2() {
    game.startGame(null, false, 10, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame3() {
    List<Card> loc = new ArrayList<Card>();
    game.startGame(loc, false, 10, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame4() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, false, 9, 3);
    c1.add(game.getDeck().get(2));
    game.startGame(c1, false, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame5() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, false, 9, 3);
    c1.remove(1);
    game.startGame(c1, false, 7, 3);
  }

  @Test
  public void testStartGame6() {
    List<Card> c1 = seanlist;
    game.startGame(seanlist, true, 9, 3);
    assertNotSame(seanlist, game.getDeck());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame7() {
    game.startGame(seanlist, false, 9, 50);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame8() {
    seanlist.add(null);
    seanlist.remove(0);
    game.startGame(seanlist, false, 9, 50);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGame9() {
    List<Card> cl = null;
    game.startGame(cl, false, 7, 3);

  }

  @Test
  public void testStartGame10() {
    game.startGame(seanlist, false, 7, 0);
    assertNotNull(game);
  }

  @Test
  public void testRemove() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 0, 6, 6);
    assertEquals(null, game.getCardAt(6, 0));
    assertEquals(null, game.getCardAt(6, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove2() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRemove3() {
    game.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove4() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove5() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(7, 0, 6, 7);
  }

  @Test
  public void testRemove6() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 3);
    assertEquals(null, game.getCardAt(6, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove7() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove8() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove9() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(4, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove10() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(9, 3);
  }

  @Test
  public void testRemoveDraw() {
    game.startGame(seanlist, false, 7, 3);
    Card p = game.getDrawCards().get(2);
    game.remove(6, 3);
    game.remove(6, 2, 6, 4);
    game.removeUsingDraw(2, 5, 3);
    assertNotSame(p, game.getDrawCards().get(2));
    assertNotNull(game.getDrawCards().get(2));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDraw1() {
    game.startGame(seanlist, false, 7, 3);
    game.removeUsingDraw(4, 5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDraw2() {
    game.startGame(seanlist, false, 7, 3);
    game.removeUsingDraw(3, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDraw3() {
    game.startGame(seanlist, false, 7, 3);
    game.removeUsingDraw(3, 4, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDraw4() {
    game.startGame(seanlist, false, 7, 3);
    game.removeUsingDraw(3, 4, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDraw5() {
    game.startGame(seanlist, false, 7, 3);
    game.removeUsingDraw(3, 6, 5);
  }

  @Test(expected = IllegalStateException.class)
  public void testRemoveDraw6() {
    game.removeUsingDraw(3, 6, 5);
  }

  @Test
  public void testDiscardDraw() {
    game.startGame(seanlist, false, 7, 3);
    Card p = game.getDrawCards().get(1);
    game.discardDraw(1);
    assertNotSame(p, game.getDrawCards().get(1));
    assertNotNull(game.getDrawCards().get(1));
  }

  @Test(expected = IllegalStateException.class)
  public void testDiscardDraw1() {
    game.discardDraw(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDraw2() {
    game.startGame(seanlist, false, 7, 3);
    game.discardDraw(3);
  }

  @Test
  public void testDiscardDraw3() {
    game.startGame(seanlist, false, 9, 7);
    assertEquals(7, game.getNumDraw());
  }

  @Test
  public void testGetNumRows() {
    game.startGame(seanlist, false, 7, 3);
    assertEquals(7, game.getNumRows());
  }

  @Test
  public void testGetNumRows1() {
    assertEquals(-1, game.getNumRows());
  }

  @Test
  public void testGetNumDraw() {
    game.startGame(seanlist, false, 7, 3);
    assertEquals(3, game.getNumDraw());
  }

  @Test
  public void testGetNumDraw1() {
    assertEquals(-1, game.getNumDraw());
  }

  @Test
  public void testGetRowWidth() {
    game.startGame(seanlist, false, 7, 3);
    assertEquals(1, game.getRowWidth(0));
    assertEquals(3, game.getRowWidth(2));
    assertEquals(7, game.getRowWidth(6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRowWidth1() {
    game.startGame(seanlist, false, 7, 3);
    game.getRowWidth(7);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetRowWidth2() {
    game.getRowWidth(2);
  }

  @Test(expected = IllegalStateException.class)
  public void testIsGameOver() {
    game.isGameOver();
  }

  @Test
  public void testIsGameOver1() {
    game.startGame(seanlist, false, 3, 15);
    assertEquals(false, game.isGameOver());
  }

  @Test
  public void testIsGameOver2() {
    game.startGame(seanlist, false, 2, 3);
    assertEquals(false, game.isGameOver());
    for (int i = 0; i < 47; i++) {
      game.discardDraw(0);
    }
    game.discardDraw(1);
    game.discardDraw(2);
    assertEquals(true, game.isGameOver());
  }

  @Test
  public void testGetScore() {
    game.startGame(seanlist, false, 4, 3);
    assertEquals(65, game.getScore());
  }

  @Test
  public void testGetScore1() {
    game.startGame(seanlist, false, 7, 3);
    assertEquals(187, game.getScore());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetScore2() {
    game.getScore();
  }

  @Test
  public void testGetScore3() {
    game.startGame(seanlist, false, 1, 3);
    assertEquals(2, game.getScore());
  }

  @Test
  public void testGetCard() {
    Card p = seanlist.get(0);
    game.startGame(seanlist, false, 7, 3);
    assertEquals(p, game.getCardAt(0, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCard1() {
    game.getCardAt(1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCard2() {
    game.startGame(seanlist, false, 7, 3);
    game.getCardAt(8, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCard3() {
    game.startGame(seanlist, false, 7, 3);
    game.getCardAt(2, 3);
  }

  @Test
  public void testGetCard4() {
    game.startGame(seanlist, false, 7, 3);
    game.remove(6, 3);
    game.remove(6, 2, 6, 4);
    assertNull(game.getCardAt(6, 2));
  }

  @Test
  public void testGetCard5() {
    Card c = new Card(Val.Three, Suit.Hearts);
    game.startGame(seanlist, false, 7, 3);
    assertTrue(game.getCardAt(6, 6).equals(c));
  }

  @Test
  public void testGetDrawCards() {
    Card c1 = seanlist.get(28);
    Card c2 = seanlist.get(29);
    Card c3 = seanlist.get(30);
    List<Card> stocklist = new ArrayList<Card>();
    stocklist.add(c1);
    stocklist.add(c2);
    stocklist.add(c3);
    game.startGame(seanlist, false, 7, 3);
    assertEquals(stocklist, game.getDrawCards());

  }

  @Test(expected = IllegalStateException.class)
  public void testGetDrawCards1() {
    game.getDrawCards();
  }


}
