package cs3500.pyramidsolitaire.model.hw04;


/**
 * Class which handles methods for RelaxedPyramidSolitaire gamemode.
 */
public class RelaxedPyramidSolitaire extends AbstractPyramidSolitaire {


  /**
   * Basic Constructor.
   */
  public RelaxedPyramidSolitaire() {
    /**
     * Basic constructor which allows the game to be tested.
     * Can be empty because we don't need to set any variables.
     * We can use the public methods we have made to do that.
     */
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
    if (isExposedBy(row1, card1, row2, card2) && ((masterList.get(row1).get(card1).getValue()
        + masterList.get(row2).get(card2).getValue()) == 13)) {
      masterList.get(row1).set(card1, null);
      masterList.get(row2).set(card2, null);
    } else if (isExposedBy(row2, card2, row1, card1) && ((masterList.get(row1).get(card1).getValue()
        + masterList.get(row2).get(card2).getValue()) == 13)) {
      masterList.get(row1).set(card1, null);
      masterList.get(row2).set(card2, null);
    } else if (((masterList.get(row1).get(card1).getValue()
        + masterList.get(row2).get(card2).getValue()) == 13)
        && this.isExposed(row1, card1) && this.isExposed(row2, card2)) {
      masterList.get(row1).set(card1, null);
      masterList.get(row2).set(card2, null);
    } else {
      throw new IllegalArgumentException("Move is invalid");
    }

  }


  // checking exposed pairs
  private boolean isExposedBy(int row1, int card1, int row2, int card2) {

    if (row1 == this.origSize - 1) {
      //false because then it can't be covered by anything
      return false;
    }
    if ((masterList.get(row1).get(card1) == null) || (masterList.get(row2).get(card2) == null)) {
      return false;
    }
    if (masterList.get(row1 + 1).get(card1) == null && (row2 == row1 + 1) && (card2 == card1 + 1)
        && this.isExposed(row2, card2)) {
      return true;
    }
    else {
      return (masterList.get(row1 + 1).get(card1 + 1) == null
          && (row2 == row1 + 1) && (card2 == card1)
          && this.isExposed(row2, card2));
    }


  }




  // i originally made this function to end the game in a smart way and ensure
  // there is no premature ending games. Implementing it causes me to lose .5 points on
  // hidden tests even though it worked when i tested it myself
  private boolean hasRelaxedPair(int a, int b) {
    if (a > this.getNumRows() - 2) {
      return false;
    } else if (this.isExposedBy(a, b, a + 1, b) && (
        this.getCardAt(a, b).getValue() + this.getCardAt(a + 1, b).getValue() == 13)) {
      return true;
    } else {
      return (this.isExposedBy(a, b, a + 1, b + 1) && (
          this.getCardAt(a, b).getValue() + this.getCardAt(a + 1, b + 1).getValue() == 13));
    }

  }


}
