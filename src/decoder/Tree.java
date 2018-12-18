package decoder;

/**
 * This interface provides operations and methods to implement a coding tree.
 */
public interface Tree {
  /**
   * This method that takes a symbol and its code as a character and string respectively. This
   * method should add this code to the coding tree.
   *
   * @param symbol the symbol which is to be encoded.
   * @param code   the code by which the symbol is encoded with.
   * @return the node
   */
  public TreeNode addChild(String symbol, String code);

  /**
   * This method takes an encoded message as a string, and generates the decoded message as a string
   * using the coding tree created thus far.
   *
   * @param code the code which is to be decoded.
   * @return the string of symbols that is decoded.
   */
  public String decode(String code);


  /**
   * A isCodeComplete method that yields true if the code entered so far is complete,  false
   * otherwise. A code is said to be complete if every valid encoded message can be successfully
   * decoded. If the decoding is done by using a coding tree, then this condition is fulfilled if
   * the coding tree is full.
   *
   * @return if the code entered so far is complete, false otherwise.
   */
  public boolean isCodeComplete(Integer sizeOfCode);
}
