package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

/**
 * Class representing a card.
 * contains a val and suit.
 * Offers few private methods and public
 * method overrides for handling.
 */
public class Card {
  /**
   * enum representing Value.
   */
  public enum Val {
    Two, Three, Four, Five, Six, Seven,
    Eight, Nine, Ten, Jack, Queen, King, Ace }

  /**
   * enum representing Suit.
   */
  public enum Suit { Diamonds, Spades, Hearts, Clubs }

  private final Val val;
  private final Suit suit;

  /**
   * Constructor allowing cards to be made.
   * @param v representing the value
   * @param s representing the suit
   */
  public Card(Val v, Suit s) {
    this.val = v;
    this.suit = s;
  }

  public Card(Card c) {
    this.val = c.val;
    this.suit = c.suit;
  }

  /**
   * Gets the integer value for card.
   * @return the integer value for card
   */
  public int getValue() {
    if (this.val == null) {
      return 0;
    }
    int v;
    switch (this.val) {
      case Ace:
        v = 1;
        break;
      case Two:
        v = 2;
        break;
      case Three:
        v = 3;
        break;
      case Four:
        v = 4;
        break;
      case Five:
        v = 5;
        break;
      case Six:
        v = 6;
        break;
      case Seven:
        v = 7;
        break;
      case Eight:
        v = 8;
        break;
      case Nine:
        v = 9;
        break;
      case Ten:
        v = 10;
        break;
      case Jack:
        v = 11;
        break;
      case Queen:
        v = 12;
        break;
      case King:
        v = 13;
        break;
      default:
        v = 0;
        break;
    }
    return v;
  }

  /**
   * gets the string value for card.
   * @return the string value for card
   */
  public String getValueString() {
    if (this.val == null) {
      return "";
    }
    String v = "";
    switch (this.val) {
      case Two:
        v = "2";
        break;
      case Three:
        v = "3";
        break;
      case Four:
        v = "4";
        break;
      case Five:
        v = "5";
        break;
      case Six:
        v = "6";
        break;
      case Seven:
        v = "7";
        break;
      case Eight:
        v = "8";
        break;
      case Nine:
        v = "9";
        break;
      case Ten:
        v = "10";
        break;
      case Jack:
        v = "J";
        break;
      case Queen:
        v = "Q";
        break;
      case King:
        v = "K";
        break;
      case Ace:
        v = "A";
        break;
      default:
        v = "";
        break;
    }
    return v;
  }

  /**
   * gets the suit string for card.
   * @return the suit string for card
   */
  public String getSuitString() {
    if (this.suit == null) {
      return "";
    }
    String s = "";
    switch (this.suit) {
      case Diamonds:
        s = "♦";
        break;
      case Spades:
        s = "♠";
        break;
      case Clubs:
        s = "♣";
        break;
      case Hearts:
        s = "♥";
        break;
      default:
        s = "";
        break;
    }
    return s;
  }

  @Override
  public String toString() {
    return this.getValueString() + this.getSuitString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(val, suit);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Card) {
      if (((Card) o).suit == this.suit && ((Card) o).val == this.val) {
        return true;
      }
    }
    return false;
  }


}
