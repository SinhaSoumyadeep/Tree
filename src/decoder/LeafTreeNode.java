package decoder;

/**
 * The class is a concrete implementation of a leaf node of a coding tree.
 */
public class LeafTreeNode extends TreeNode {


  private String symbol;

  /**
   * this constructor instantiates a new Leaf node.
   *
   * @param symbol the symbol
   * @param data   the data
   */
  public LeafTreeNode(String symbol, String data) {
    super(data);
    this.symbol = symbol;
  }

  /**
   * This method that takes a symbol and its code as a character and string respectively. This
   * method should add this code to the coding tree. If a new node is added then the leaf node is
   * promoted to the Group node.
   *
   * @param symbol the symbol which is to be encoded.
   * @param code   the code by which the symbol is encoded with.
   * @return the node
   */
  @Override
  public TreeNode addChild(String symbol, String code) {


    GroupTreeNode newSelf = new GroupTreeNode(this.data);
    newSelf.addChild(symbol, code);
    return newSelf;

  }

  /**
   * This method takes an encoded message as a string, and generates the decoded message as a string
   * using the coding tree created thus far.
   *
   * @param code the code which is to be decoded.
   * @return the string of symbols that is decoded.
   * @throws IllegalStateException if the decoding fails.
   */
  @Override
  public String decode(String code) {
    return this.symbol;
  }

  /**
   * A isCodeComplete method that yields true if the code entered so far is complete,  false
   * otherwise. A code is said to be complete if every valid encoded message can be successfully
   * decoded. If the decoding is done by using a coding tree, then this condition is fulfilled if
   * the coding tree is full.
   *
   * @return if the code entered so far is complete, false otherwise.
   */
  @Override
  public boolean isCodeComplete(Integer sizeOfCode) {
    return true;
  }
}
