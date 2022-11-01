/**
 * Interaction interface to mock interactions for testing.
 */
interface Interaction {

  /**
   * apply function which allows input and output to be applied to.
   * @param in a StringBuilder
   * @param out a StringBuilder
   */
  void apply(StringBuilder in, StringBuilder out);

  //appends string to input appendable
  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }

  //appends string to output appendable
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

}
