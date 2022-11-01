package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class which handles methods for TriPeaksPyramidSolitaire gamemode.
 */
public class TriPeaksPyramidSolitaire extends AbstractPyramidSolitaire {

  /**
   * Basic Constructor.
   */
  public TriPeaksPyramidSolitaire() {
    /**
     * Basic constructor which allows the game to be tested.
     * Can be empty because we don't need to set any variables.
     * We can use the public methods we have made to do that.
     */
  }

  /**
   * Return a valid and complete deck of cards for a game of Pyramid Solitaire. There is no
   * restriction imposed on the ordering of these cards in the deck. The validity of the deck is
   * determined by the rules of the game.
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<Card> getDeck() {
    List<Card> l = new ArrayList<Card>();
    for (int i = 0; i < 2; i++) {
      for (Card.Suit s : Card.Suit.values()) {
        for (Card.Val v : Card.Val.values()) {
          Card c = new Card(v, s);
          l.add(c);
        }
      }
    }
    return l;
  }

  /**
   * <p>Deal a new game of Pyramid Solitaire.  The cards to be used and their order
   * are specified by the the given deck, unless the {@code shuffle} parameter indicates the order
   * should be ignored.</p>
   *
   * <p>This method first verifies that the deck is valid. It deals cards into the characteristic
   * pyramid shape having the specified number of complete rows, followed by the specified number of
   * draw cards. The 0th card in {@code deck} is the top of the pyramid.</p>
   *
   * <p>This method should have no other side effects, and should work for any valid arguments.</p>
   *
   * @param deck          the deck to be dealt
   * @param shouldShuffle if {@code false}, use the order as given by {@code deck}, otherwise
   *                      shuffle the cards
   * @param numRows       number of rows in the pyramid
   * @param numDraw       number of open piles
   * @throws IllegalArgumentException if the deck is null or invalid, or another input is invalid
   */
  @Override
  public void startGame(List deck, boolean shouldShuffle, int numRows, int numDraw) {
    List<Card> cardDeck = new ArrayList<Card>(deck);
    if (cardDeck == null || cardDeck.isEmpty()) {
      throw new IllegalArgumentException("Deck is either null or invalid");
    }
    if (numRows < 1 || numRows > 8 || numDraw < 0) {
      throw new IllegalArgumentException("Check inputted rows and draw");
    }

    if (cardDeck.size() != 104) {
      throw new IllegalArgumentException("Deck is either null or invalid");
    }

    for (int i = 0; i < cardDeck.size(); i++) {
      for (int j = 0; j < cardDeck.size(); j++) {
        for (int k = 0; k < cardDeck.size(); k++) {
          if (i != j) {
            if (j != k) {
              if (i != k) {
                if (cardDeck.get(i).equals(cardDeck.get(j))) {
                  if (cardDeck.get(j).equals(cardDeck.get(k))) {
                    throw new IllegalArgumentException("Cannot have three of the same card");
                  }
                }
              }
            }
          }
        }
      }
      if (cardDeck.get(i) == null) {
        throw new IllegalArgumentException("Deck is either null or invalid");
      }
      if (!(cardDeck.get(i) instanceof Card)) {
        throw new IllegalArgumentException("Deck is either null or invalid");
      }

    }

    int nullCount = 0;
    this.masterList.clear();
    this.drawPile.clear();
    this.stock.clear();
    int goneThrough = 0;

    if (shouldShuffle) {
      Collections.shuffle(cardDeck);
    }

    int overlap;
    if (numRows % 2 != 0) {
      overlap = (numRows / 2) + 1;
    } else {
      overlap = (numRows / 2);
    }
    int restOverlap = numRows - overlap;
    int size = 0;
    int r = restOverlap - 1;
    for (int i = 1; i < restOverlap + 1; i++) {
      ArrayList<Card> microList = new ArrayList<Card>();
      for (int j = 0; j < (i * 3); j++) {
        microList.add(cardDeck.get(goneThrough));
        goneThrough += 1;
        if ((i < restOverlap) && (j < ((i * 3) - 1)) && (((j + 1) % i) == 0)) {
          for (int l = 0; l < r; l++) {
            microList.add(null);
            nullCount += 1;
          }
        }
      }
      r -= 1;
      masterList.add(microList);
      size = microList.size();
    }
    for (int i = restOverlap; i < numRows; i++) {
      ArrayList<Card> microList = new ArrayList<Card>();
      for (int j = 0; j < size + 1; j++) {
        microList.add(cardDeck.get(goneThrough));
        goneThrough += 1;
      }
      size += 1;
      masterList.add(microList);
    }
    int numDrawPerfect = 104 - goneThrough;
    if (numDraw > numDrawPerfect) {
      throw new IllegalArgumentException("Deck is either null or invalid");
    }

    for (int i = 0; i < numDraw; i++) {
      drawPile.add(cardDeck.get(goneThrough));
      goneThrough += 1;

    }
    origSize = numRows;
    drawSize = numDraw;
    stock = cardDeck.subList(goneThrough, deck.size());
    gameStarted = true;
  }


}
