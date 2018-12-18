package decoder;

/**
 * This class provides methods and functions to implement a coding tree.
 */
public abstract class TreeNode implements Tree {

  protected String data;

  /**
   * This constructor instantiates a new TreeNode.
   *
   * @param data the data
   */
  public TreeNode(String data) {
    this.data = data;
  }


}
