package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class containing all my methods for various games of PyramidSolitaire.
 */
public abstract class AbstractPyramidSolitaire implements PyramidSolitaireModel {

  protected List<ArrayList<Card>> masterList = new ArrayList<ArrayList<Card>>();
  protected List<Card> drawPile = new ArrayList<Card>();
  protected List<Card> stock = new ArrayList<Card>();
  protected boolean gameStarted = false;
  protected int origSize = 0;
  protected int drawSize = 0;


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
    for (Card.Suit s : Card.Suit.values()) {
      for (Card.Val v : Card.Val.values()) {
        Card c = new Card(v, s);
        l.add(c);
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
    if (deck == null) {
      throw new IllegalArgumentException("Deck is either null or invalid");
    }
    List<Card> cardDeck = new ArrayList<Card>(deck);
    if (cardDeck.isEmpty()) {
      throw new IllegalArgumentException("Deck is either null or invalid");
    }
    if (cardDeck.size() != 52 || numRows < 1 || numRows > 9 || numDraw < 0 || numDraw >= 52) {
      throw new IllegalArgumentException("Check inputted rows and draw");
    }

    for (int i = 0; i < cardDeck.size(); i++) {
      if (cardDeck.get(i) == null) {
        throw new IllegalArgumentException("Deck is either null or invalid");
      }
      if (!(cardDeck.get(i) instanceof Card)) {
        throw new IllegalArgumentException("Deck is either null or invalid");
      }
      for (int l = 0; l < cardDeck.size(); l++) {
        if (l != i) {
          if (cardDeck.get(i) == cardDeck.get(l)) {
            throw new IllegalArgumentException("Deck is either null or invalid");
          }
        }
      }
    }
    int cardsNeeded = 0;
    for (int i = 0; i < numRows; i++) {
      cardsNeeded += (i + 1);
    }
    int numDrawPerfect = cardDeck.size() - cardsNeeded;
    if (numDraw > numDrawPerfect || cardsNeeded > cardDeck.size()) {
      throw new IllegalArgumentException("Deck is either null or invalid");
    }
    this.masterList.clear();
    this.drawPile.clear();
    this.stock.clear();
    int goneThrough = 0;

    if (shouldShuffle) {
      Collections.shuffle(cardDeck);
    }

    for (int i = 0; i < numRows; i++) {
      ArrayList<Card> microList = new ArrayList<Card>();
      for (int j = 0; j < i + 1; j++) {
        microList.add(cardDeck.get(goneThrough));
        goneThrough += 1;
      }
      masterList.add(microList);
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


  protected boolean isExposed(int row, int card) {
    if (row == this.origSize - 1) {
      return true;
    }
    if (this.getCardAt(row, card) == null) {
      throw new IllegalArgumentException("Card not there");
    }
    return (masterList.get(row + 1).get(card) == null
        && masterList.get(row + 1).get(card + 1) == null);

  }

  /**
   * Execute a pairwise move on the pyramid, using the two specified card positions.
   *
   * @param row1  row of first card position, numbered from 0 from the top of the pyramid
   * @param card1 card of first card position, numbered from 0 from left
   * @param row2  row of second card position
   * @param card2 card of second card position
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started yet!");
    }
    if (row1 > this.getNumRows() - 1 || row2 > this.getNumRows() - 1 || row1 < 0 || row2 < 0) {
      throw new IllegalArgumentException("Move is invalid");
    }
    if (card1 > this.getRowWidth(row1) - 1 || card2 > this.getRowWidth(row2) - 1
        || card1 < 0 || card2 < 0) {
      throw new IllegalArgumentException("Move is invalid");
    }
    if (this.getCardAt(row1, card1) == null || this.getCardAt(row2, card2) == null) {
      throw new IllegalArgumentException("Move is invalid");
    }
    if (((masterList.get(row1).get(card1).getValue()
        + masterList.get(row2).get(card2).getValue()) == 13)
        && this.isExposed(row1, card1) && this.isExposed(row2, card2)) {
      masterList.get(row1).set(card1, null);
      masterList.get(row2).set(card2, null);
    } else {
      throw new IllegalArgumentException("Move is invalid");
    }

  }

  /**
   * Execute a single-card move on the pyramid, using the specified card position.
   *
   * @param row  row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void remove(int row, int card) throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started yet!");
    }
    if (row < 0 || card < 0) {
      throw new IllegalArgumentException("Move is invalid");
    }
    if (row > this.getNumRows() - 1 || card > this.getRowWidth(row) - 1) {
      throw new IllegalArgumentException("Move is invalid");
    }
    if (this.getCardAt(row, card) == null) {
      throw new IllegalArgumentException("No card exists there!");
    }
    if ((masterList.get(row).get(card).getValue() == 13) && this.isExposed(row, card)) {
      masterList.get(row).set(card, null);
    } else {
      throw new IllegalArgumentException("Move is invalid");
    }
  }

  /**
   * Execute a pairwise move, using the specified card from the draw pile and the specified card
   * from the pyramid.
   *
   * @param row  row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started yet!");
    }
    if (drawIndex < 0 || row < 0 || card < 0) {
      throw new IllegalArgumentException("Inputted number of rows or cards is incorrect");
    }
    if (row > this.getNumRows() - 1 || card > this.getRowWidth(row) - 1
        || drawIndex > this.drawSize - 1 || drawIndex < 0 || row < 0 || card < 0) {
      throw new IllegalArgumentException("Inputted number of rows or cards is incorrect");
    }
    if (this.getCardAt(row, card) == null) {
      throw new IllegalArgumentException("Specified card is invalid");
    }

    if ((masterList.get(row).get(card).getValue() + drawPile.get(drawIndex).getValue() == 13)
        && this.isExposed(row, card)) {
      masterList.get(row).set(card, null);
      if (stock.size() != 0) {
        drawPile.set(drawIndex, stock.get(0));
        stock.remove(0);
      } else {
        drawPile.set(drawIndex, null);
      }
    } else {
      throw new IllegalArgumentException("Move is invalid");
    }
  }

  /**
   * Discards an individual card from the draw pile.
   *
   * @param drawIndex the card to be discarded
   * @throws IllegalArgumentException if the index is invalid or no card is present there.
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started yet!");
    }
    if (drawIndex > drawSize - 1 || drawIndex < 0) {
      throw new IllegalArgumentException("Index is invalid");
    }
    if (drawPile.get(drawIndex) == null) {
      throw new IllegalArgumentException("No card is present there");
    }
    if (!stock.isEmpty()) {
      drawPile.set(drawIndex, stock.get(0));
      stock.remove(0);
    } else {
      drawPile.set(drawIndex, null);
    }

  }

  /**
   * Returns the number of rows originally in the pyramid, or -1 if the game hasn't been started.
   *
   * @return the height of the pyramid, or -1
   */
  @Override
  public int getNumRows() {
    if (!gameStarted) {
      return -1;
    }
    return origSize;
  }

  /**
   * Returns the maximum number of visible cards in the draw pile, or -1 if the game hasn't been
   * started.
   *
   * @return the number of visible cards in the draw pile, or -1
   */
  @Override
  public int getNumDraw() {
    if (!gameStarted) {
      return -1;
    }
    return drawSize;
  }

  /**
   * Returns the number of cards dealt into the given row.
   *
   * @param row the desired row (zero-indexed)
   * @return the width of that row
   * @throws IllegalArgumentException if the row is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public int getRowWidth(int row) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started!");
    }
    if (row > origSize - 1) {
      throw new IllegalArgumentException("Row is invalid!");
    }
    return masterList.get(row).size();
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public boolean isGameOver() throws IllegalStateException {

    if (!gameStarted) {
      throw new IllegalStateException("Game hasn't started yet!");
    }
    if (this.restNull()) {
      return true;
    }
    List<Card> exposedList = new ArrayList<Card>();
    for (int a = 0; a < masterList.size(); a++) {
      for (int b = 0; b < masterList.get(a).size(); b++) {
        if (masterList.get(a).get(b) == null) {
          continue;
        }
        if (this.isExposed(a, b)) {
          exposedList.add(masterList.get(a).get(b));
        }
      }
    }
    for (int i = 0; i < exposedList.size(); i++) {
      if (exposedList.get(i) == null) {
        continue;
      }
      if (13 == exposedList.get(i).getValue()) {
        return false;
      }
      for (int j = 0; j < exposedList.size(); j++) {
        if (exposedList.get(j) == null) {
          continue;
        }
        if (i != j) {
          if (13 == exposedList.get(i).getValue() + exposedList.get(j).getValue()) {
            return false;
          }
        }
      }
    }
    int k = 0;
    for (int i = 0; i < this.drawSize; i++) {
      if (drawPile.get(i) == null) {
        k += 1;
      }
      if (k == drawPile.size()) {
        return true;
      }

    }
    return false;

  }

  boolean restNull() {
    for (int a = 0; a < masterList.size(); a++) {
      for (int b = 0; b < masterList.get(a).size(); b++) {
        if (masterList.get(a).get(b) != null) {
          return false;
        }
      }
    }
    return true;

  }

  /**
   * Return the current score, which is the sum of the values of the cards remaining in the
   * pyramid.
   *
   * @return the score
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public int getScore() throws IllegalStateException {
    int score = 0;
    if (!gameStarted) {
      throw new IllegalStateException("Game hasn't started yet!");
    }
    for (int i = 0; i < masterList.size(); i++) {
      for (int j = 0; j < masterList.get(i).size(); j++) {
        if (masterList.get(i).get(j) == null) {
          score += 0;
        } else {
          score += masterList.get(i).get(j).getValue();
        }
      }
    }
    return score;
  }

  /**
   * Returns the card at the specified coordinates.
   *
   * @param row  row of the desired card
   * @param card column of the desired card
   * @return the card at the given position, or <code>null</code> if no card is there
   * @throws IllegalArgumentException if the coordinates are invalid
   * @throws IllegalStateException    if the game hasn't been started yet
   */
  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game hasn't started yet!");
    }
    if (row >= origSize || row < 0) {
      throw new IllegalArgumentException("Index is invalid");
    }
    if (card >= masterList.get(row).size() || card < 0) {
      throw new IllegalArgumentException("Index is invalid");
    }
    if (masterList.get(row).get(card) == null) {
      return null;
    }
    Card c = new Card(masterList.get(row).get(card));
    return c;
  }

  /**
   * Returns the currently available draw cards. There should be at most {@link
   * PyramidSolitaireModel#getNumDraw} cards (the number specified when the game started) -- there
   * may be fewer, if cards have been removed.
   *
   * @return the ordered list of available draw cards
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game hasn't started yet!");
    }
    List<Card> loc = new ArrayList<Card>(drawPile);
    return loc;
  }

}
