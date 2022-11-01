
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw02.Card.Val;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Class representing tests for card class.
 */
public class CardTests {
  Card d5 = new Card(Card.Val.Five, Card.Suit.Diamonds);
  Card c7 = new Card(Val.Seven, Suit.Clubs);
  Card s7 = new Card(Val.Seven, Suit.Spades);
  Card h7 = new Card(Val.Seven, Suit.Hearts);
  Card nullcard = new Card(null, null);

  @Test
  public void testGetValue() {
    assertEquals(5, d5.getValue());
  }

  @Test
  public void testGetValue1() {
    assertEquals(7, c7.getValue());
    assertEquals(0, nullcard.getValue());
  }


  @Test
  public void testGetValueString() {
    assertEquals("7", c7.getValueString());
    assertEquals("5", d5.getValueString());
    assertEquals("10", new Card(Val.Ten, Suit.Clubs).getValueString());
    assertEquals("", nullcard.getValueString());
  }

  @Test
  public void testGetSuitString() {
    assertEquals("♦", d5.getSuitString());
    assertEquals("♠", s7.getSuitString());
    assertEquals("♣", c7.getSuitString());
    assertEquals("♥", h7.getSuitString());
    assertEquals("", nullcard.getSuitString());
  }

  @Test
  public void testHash() {
    Card ca = new Card(Val.Ten, Suit.Clubs);
    Card cb = new Card(Val.Ten, Suit.Clubs);
    Card n = new Card(null, null);
    assertEquals(ca.hashCode(), cb.hashCode());
    assertEquals(nullcard.hashCode(), n.hashCode());
  }

  @Test
  public void testEquals() {
    Card ca = new Card(Val.Ten, Suit.Clubs);
    Card cb = new Card(Val.Ten, Suit.Clubs);
    Card cc = new Card(Val.Ten, Suit.Hearts);
    assertTrue(ca.equals(cb));
    assertFalse(cc.equals(cb));
    assertTrue(nullcard.equals(nullcard));
  }

  @Test
  public void testString() {
    Card cb = new Card(Val.Ten, Suit.Clubs);
    Card cc = new Card(Val.Ten, Suit.Hearts);
    assertEquals("10♣", cb.toString());
    assertEquals("10♥", cc.toString());
    assertEquals("", nullcard.toString());
  }
}
