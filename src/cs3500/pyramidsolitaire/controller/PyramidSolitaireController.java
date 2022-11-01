package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.util.List;

/**
 * Public interface representing the controller
 * for the Pyramid Solitaire Game.
 */
public interface PyramidSolitaireController {

  /**
   * should start a new game of Pyramid Solitaire using the provided model
   * @param model PyramidSolitaireModel
   * @param deck the deck to be used in the game
   * @param shuffle Do you want to shuffle the cards?
   * @param numRows How many rows do you want?
   * @param numDraw How many draw cards do you want?
   */
  <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck,
      boolean shuffle, int numRows, int numDraw);
}
