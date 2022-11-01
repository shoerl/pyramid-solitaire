package cs3500.pyramidsolitaire.view;

import java.io.IOException;

/**
 * Interface representing the view of PyramidSolitaire.
 * It handles the methods that relate to viewing the game.
 */
interface PyramidSolitaireView {
  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;
}
