package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * The PyramidSolitaireCreator class creates various instances of PyramidSolitaireModel.
 */
public class PyramidSolitaireCreator {

  /**
   * enum representing the three various gametypes/modes.
   */
  public enum GameType { BASIC, RELAXED, TRIPEAKS }

  /**
   * Creates an instance of a PyramidSolitaire game
   * in the mode which is specified in the argument.
   *
   * @param type the Enum which represents one of the three gametypes.
   * @return
   */
  public static PyramidSolitaireModel create(GameType type) {
    if (type.equals(GameType.TRIPEAKS)) {
      return new TriPeaksPyramidSolitaire();
    } else if (type.equals(GameType.RELAXED)) {
      return new RelaxedPyramidSolitaire();
    } else if (type.equals(GameType.BASIC)) {
      return new BasicPyramidSolitaire();
    } else {
      return null;
    }
  }


}
