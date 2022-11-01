import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Mock controller class which allows us to make mock controller
 * and verify that the controller is receiving input properly.
 */
class MockController implements PyramidSolitaireController {
  Readable in;
  Appendable out;

  /**
   * Constructor which allows us to make a MockController.
   * @param rd the readable
   * @param ap the appendable
   */
  MockController(Readable rd, Appendable ap) {
    this.in = rd;
    this.out = ap;
  }

  /**
   * Takes input from scanner and runs getCardAt method.
   * @param model the PyramidSolitaireModel
   * @throws IOException if errors happen while appending
   */
  public void goGetCard(PyramidSolitaireModel model) throws IOException {
    Objects.requireNonNull(model);
    int num1;
    int num2;
    Scanner scan = new Scanner(this.in);
    num1 = scan.nextInt();
    num2 = scan.nextInt();
    this.out.append(String.format("%d\n", model.getCardAt(num1, num2)));
  }

  /**
   * Takes input from scanner and runs remove method.
   * @param model the PyramidSolitaireModel
   * @throws IOException if errors happen while appending
   */
  public void goRemove(PyramidSolitaireModel model) throws IOException {
    Objects.requireNonNull(model);
    int num1;
    int num2;
    int num3;
    int num4;
    Scanner scan = new Scanner(this.in);
    num1 = scan.nextInt();
    num2 = scan.nextInt();
    num3 = scan.nextInt();
    num4 = scan.nextInt();
    model.remove(num1, num2, num3, num4);
    //this.out.append(String.format("%d\n", model.remove(num1, num2, num3, num4)));
  }

  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
      int numRows, int numDraw) {

    // needed so this compiles correctly
  }
}
