package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

/**
 * Class that allows you to play various modes of PyramidSolitaire.
 */
public final class PyramidSolitaire {

  /**
   * Main method which handles inputs to start instances of
   * various modes of PyramidSolitaire.
   *
   * @param args the argument which specifies which game to play
   *            and how many rows and draw cards.
   */
  public static void main(String[] args) {
    Appendable out = new PrintStream(System.out);
    Readable in = new InputStreamReader(System.in);
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    int numRow = 7;
    int numDraw = 3;
    if (args.length > 0) {
      if (args[0].equals("basic")) {
        if (args.length > 1) {
          numRow = Integer.valueOf(args[1]);
        }
        if (args.length > 2) {
          numDraw = Integer.valueOf(args[2]);
        }
        PyramidSolitaireModel game = new PyramidSolitaireCreator()
            .create(PyramidSolitaireCreator.GameType.BASIC);
        List<Card> deck = game.getDeck();
        controller.playGame(game, deck, true, numRow, numDraw);
      } else if (args[0].equals("relaxed")) {
        if (args.length > 1) {
          numRow = Integer.valueOf(args[1]);
        }
        if (args.length > 2) {
          numDraw = Integer.valueOf(args[2]);
        }
        PyramidSolitaireModel game = new PyramidSolitaireCreator()
            .create(PyramidSolitaireCreator.GameType.RELAXED);
        List<Card> deck = game.getDeck();
        controller.playGame(game, deck, true, numRow, numDraw);
      } else if (args[0].equals("tripeaks")) {
        if (args.length > 1) {
          numRow = Integer.valueOf(args[1]);
        }
        if (args.length > 2) {
          numDraw = Integer.valueOf(args[2]);
        }
        PyramidSolitaireModel game = new PyramidSolitaireCreator()
            .create(PyramidSolitaireCreator.GameType.TRIPEAKS);
        List<Card> deck = game.getDeck();
        controller.playGame(game, deck, true, numRow, numDraw);
      } else {
        throw new IllegalArgumentException("Invalid game");
      }
    } else {
      throw new IllegalArgumentException("No arguments given");
    }
  }
}
