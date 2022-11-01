package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * class representing the textual controller. Contains the methods that allowed the game to be
 * controlled with a appendable and a readable.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  private final Readable rd;
  private final Appendable ap;
  private boolean uQuit = false;
  private final Scanner scan;
  private PyramidSolitaireTextualView view;

  /**
   * basic constructor that also instiaties the scanner.
   *
   * @param rd the Readable
   * @param ap the Appendable
   * @throws IllegalArgumentException if the readable or appendable is null
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("One of the arguments is null");
    } else {
      this.rd = rd;
      this.ap = ap;
      this.scan = new Scanner(this.rd);
    }
  }


  /**
   * should start a new game of Pyramid Solitaire using the provided model.
   *
   * @param model   PyramidSolitaireModel
   * @param deck    the deck to be used in the game
   * @param shuffle Do you want to shuffle the cards?
   * @param numRows How many rows do you want?
   * @param numDraw How many draw cards do you want?
   */
  @Override
  public <Card> void playGame(PyramidSolitaireModel<Card> model, List<Card> deck, boolean shuffle,
      int numRows, int numDraw) {

    if (model == null) {
      throw new IllegalArgumentException("model is null!");
    }

    try {
      view = new PyramidSolitaireTextualView(model, this.ap);
      model.startGame(deck, shuffle, numRows, numDraw);
      this.renderH(model);

    } catch (Exception e) {
      throw new IllegalStateException("rip");
    }

    if (this.scan.hasNext()) {
      while (!(model.isGameOver() || uQuit)) {
        if (this.scan.hasNext()) {
          try {
            String input = scan.next();
            if (input.equals("q") || input.equals("Q")) {
              uQuit = true;
            } else if (input.equals("rm1")) {

              int num1 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              int num2 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              model.remove(num1, num2);
              this.renderH(model);
            } else if (input.equals("rm2")) {
              int num1 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              int num2 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              int num3 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              int num4 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              model.remove(num1, num2, num3, num4);
              this.renderH(model);
            } else if (input.equals("rmwd")) {
              int num1 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              int num2 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              int num3 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              model.removeUsingDraw(num1, num2, num3);
              this.renderH(model);
            } else if (input.equals("dd")) {
              int num1 = this.numValHelp(validator());
              if (uQuit) {
                break;
              }
              model.discardDraw(num1);
              this.renderH(model);
            } else {
              ap.append("Please re-enter" + "\n");
            }


          } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
            try {
              ap.append("Invalid move. Play again. " + ex.getMessage() + "\n");
            } catch (IOException e) {
              throw new IllegalStateException("rip");
            }
          } catch (IOException e) {
            throw new IllegalStateException("rip");
          }
        }

      }
    }

    if (uQuit) {
      try {
        ap.append("Game quit!" + "\n");
        ap.append("State of game when quit:" + "\n");
        this.renderH(model);
      } catch (IOException e) {
        throw new IllegalStateException("rip");
      }
    }
  }

  private <Card> void renderH(PyramidSolitaireModel<Card> model) {
    try {
      view.render();
      if (!(model.isGameOver() || this.isEmpty(model))) {
        ap.append("\n" + "Score: " + model.getScore());
      }
      ap.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("rip");
    }
  }

  private <Card> String validator() {
    if (scan.hasNextInt()) {
      return scan.next();
    } else if (scan.hasNext("Q") || scan.hasNext("q")) {
      uQuit = true;
      scan.next();
      return "1";
    }

    if (scan.hasNext()) {
      scan.next();
    }
    return this.validator();
  }


  private <Card> boolean isEmpty(PyramidSolitaireModel<Card> model) {
    int rows = model.getNumRows();
    for (int a = 0; a < rows; a++) {
      int rowWidth = model.getRowWidth(a);
      for (int b = 0; b < rowWidth; b++) {
        if (model.getCardAt(a, b) != null) {
          return false;
        }
      }
    }
    return true;
  }


  private int numValHelp(String s) {
    return Integer.valueOf(s) - 1;
  }
}
