package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.Card;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Class represnting the PyramidSolitaireTextualView. The methods within the class allow for the
 * Solitaire game to be viewed on a screen as a string.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {

  private final PyramidSolitaireModel<?> model;
  private final Appendable ap;
  // ... any other fields you need

  /**
   * basic constructor allowing for textual view to be tested.
   *
   * @param model a PyramidSolitaireModel
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
    this.ap = new PrintStream(System.out);
  }

  /**
   * more advanced constructor allowing for controller to be tested.
   *
   * @param model a PyramidSolitaireModel
   * @param ap    a Appendable
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void render() throws IOException {
    if (ap == null) {
      throw new IOException("Appendable is null");
    }
    ap.append(this.toString());
  }

  @Override
  public String toString() {
    if (this.model.getNumRows() == -1) {
      return "";
    }
    if (this.isEmpty()) {
      return "You win!";
    }
    if (this.model.isGameOver()) {
      return "Game over. Score: " + this.model.getScore();
    } else {
      return this.generatePyramid();
    }

  }

  private String generatePyramid() {
    String output = "";
    int rows = this.model.getNumRows();
    int maxWidth = this.model.getRowWidth(rows - 1);
    int length = (maxWidth * 4) - 2;
    for (int a = 0; a < rows; a++) {
      int rowWidth = this.model.getRowWidth(a);
      int stringSpace = (rowWidth * 4) - 2;
      int whiteSpace = (length - stringSpace) / 2;
      for (int k = 0; k < whiteSpace; k++) {
        output += " ";
      }
      for (int b = 0; b < rowWidth; b++) {
        if (b == 0) {
          if (this.model.getCardAt(a, b) == null) {
            if (this.restEmpty(a, b + 1, rowWidth)) {
              break;
            }
            output += "  ";
            continue;
          } else {
            output += this.model.getCardAt(a, b).toString();
            if (this.restEmpty(a, b + 1, rowWidth)) {
              break;
            }
            continue;
          }
        }

        Card c1 = (Card) this.model.getCardAt(a, b);
        if (b != 0) {
          if (this.model.getCardAt(a, b - 1) != null) {
            Card c2 = (Card) this.model.getCardAt(a, b - 1);
            if (c2.getValue() == 10) {
              if (this.model.getCardAt(a, b) == null) {
                if (this.restEmpty(a, b + 1, rowWidth)) {
                  break;
                }
                output += "   ";
                continue;
              } else {
                output += " " + this.model.getCardAt(a, b).toString();
                if (this.restEmpty(a, b + 1, rowWidth)) {
                  break;
                }
                continue;
              }
            }
          }
        }
        if (this.model.getCardAt(a, b) == null) {
          output += "    ";
          if (this.restEmpty(a, b + 1, rowWidth)) {
            break;
          }
          continue;
        }
        output += "  " + this.model.getCardAt(a, b).toString();

        if (this.restEmpty(a, b + 1, rowWidth)) {
          break;
        }

      }
      output += "\n";
    }
    output += "Draw: ";
    //List<Card> draw = (List<Card>) this.model.getDrawCards().get(i)
    int drawSize = this.model.getNumDraw();
    for (int i = 0; i < drawSize; i++) {

      if (this.model.getDrawCards().get(i) == null) {
        output += "  ";
      } else {
        output += this.model.getDrawCards().get(i).toString();
      }
      if (i != drawSize - 1) {
        output += ", ";
      }
    }

    return output;
  }


  private boolean restEmpty(int currentA, int currentB, int finalB) {
    for (int p = currentB; p < finalB; p++) {
      if (this.model.getCardAt(currentA, p) != null) {
        return false;
      }
    }
    return true;
  }

  private boolean isEmpty() {
    int rows = this.model.getNumRows();
    for (int a = 0; a < rows; a++) {
      int rowWidth = this.model.getRowWidth(a);
      for (int b = 0; b < rowWidth; b++) {
        if (this.model.getCardAt(a, b) != null) {
          return false;
        }
      }
    }
    return true;
  }
}
