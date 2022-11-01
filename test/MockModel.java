import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.util.List;
import java.util.Objects;

/**
 * Calculator which verifies the inputs.
 */
class MockModel implements PyramidSolitaireModel {

  final StringBuilder log;

  /**
   * Basic constructor which allows us to make a MockModel.
   * @param log the StringBuilder
   */
  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public Object getCardAt(int row, int card) throws IllegalStateException {
    log.append(String.format("row = %d, card = %d\n", row - 1, card - 1));
    return null;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    log.append(String.format("row1 = %d, card1 = %d, row2 = %d, card2 = %d\n",
        row1 - 1, card1 - 1, row2 - 1, card2 - 1));
    return;
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    //needed to compile
  }

  @Override
  public List getDeck() {
    return null;
  }

  @Override
  public void startGame(List deck, boolean shouldShuffle, int numRows, int numDraw) {
    //needed to compile
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    //needed to compile
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    //needed to compile
  }

  @Override
  public int getNumRows() {
    return 0;
  }

  @Override
  public int getNumDraw() {
    return 0;
  }

  @Override
  public int getRowWidth(int row) {
    return 0;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    return 0;
  }


  @Override
  public List getDrawCards() throws IllegalStateException {
    return null;
  }
}
