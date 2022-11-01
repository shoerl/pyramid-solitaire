import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw02.Card.Val;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * class representing and containing my tripeak tests.
 **/
public class TriPeaksPyramidSolitiareTests {

  TriPeaksPyramidSolitaire game = new TriPeaksPyramidSolitaire();
  List<Card> deck = game.getDeck();

  @Test
  public void forMe() {
    game.startGame(deck, false, 9, 0);
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(game);
    System.out.print(view.toString());
  }

  @Test
  public void testGetDeck() {
    assertEquals(104, deck.size());
  }

  @Test
  public void testGoodStartGame() {
    game.startGame(deck, true, 7, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooManyRowsStartGame() {
    game.startGame(deck, true, 9, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooFewRowsStartGame() {
    game.startGame(deck, true, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooFewRowsStartGame2() {
    game.startGame(deck, true, -30, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooFewDraw() {
    game.startGame(deck, true, 5, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooManyDraw() {
    game.startGame(deck, true, 5, 79);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooManyDraw2() {
    game.startGame(deck, true, 1, 104);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooSmallDeck() {
    deck.remove(0);
    game.startGame(deck, true, 7, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooBigDeck() {
    Card newC = new Card(Val.Two, Suit.Hearts);
    deck.add(newC);
    game.startGame(deck, true, 7, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateCards() {
    Card newC = new Card(Val.Two, Suit.Hearts);
    deck.remove(1);
    deck.add(newC);
    game.startGame(deck, true, 7, 4);
  }

  @Test
  public void testNumRows() {
    game.startGame(deck, true, 7, 4);
    assertEquals(game.getNumRows(), 7);
  }

  @Test
  public void testNumDraw() {
    game.startGame(deck, true, 7, 4);
    assertEquals(game.getNumDraw(), 4);
  }

  @Test
  public void testShuffle() {
    game.startGame(deck, true, 7, 4);
    assertEquals(game.getNumDraw(), 4);
  }

  @Test
  public void testShuffle2() {
    game.startGame(deck, false, 7, 4);
    Card c1 = game.getCardAt(0, 0);
    game.startGame(deck, true, 7, 4);
    Card c2 = game.getCardAt(0, 0);
    assertNotSame(c1, c2);
  }


}
