package decoder;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class is a concrete implementation of a non leaf node of a coding tree.
 */
public class GroupTreeNode extends TreeNode {


  private List<TreeNode> children;


  /**
   * this constructor instantiates a new Group node.
   *
   * @param data the data
   */
  public GroupTreeNode(String data) {
    super(data);
    children = new LinkedList<TreeNode>();
  }

  /**
   * This method that takes a symbol and its code as a character and string respectively. This
   * method should add this code to the coding tree.
   *
   * @param symbol the symbol which is to be encoded.
   * @param code   the code by which the symbol is encoded with.
   * @return the node
   */
  @Override
  public TreeNode addChild(String symbol, String code) {

    String encode = code.charAt(0) + "";

    List<String> childrenList = this.children.stream()
            .map(e -> e.data).collect(Collectors.toList());

    if (childrenList.contains(encode)) {

      int index = 0;

      for (String child : childrenList) {
        if (child.equals(encode)) {
          break;
        }
        index++;
      }

      TreeNode extractedNode = this.children.get(index);

      String restString = code.substring(1);

      extractedNode.addChild(symbol, restString);

      return this;

    } else {

      if (code.length() == 1) {

        this.children.add(new LeafTreeNode(symbol, encode));

      } else {

        this.children.add(new LeafTreeNode(symbol, encode).addChild(symbol, code.substring(1)));

      }

      return this;
    }
  }


  /**
   * This method takes an encoded message as a string, and generates the decoded message as a string
   * using the coding tree created thus far.
   *
   * @param code the code which is to be decoded.
   * @return the string of symbols that is decoded.
   */
  @Override
  public String decode(String code) {
    if (code.equals("")) {
      return null;
    }
    String dataVal = code.charAt(0) + "";
    List<TreeNode> child = this.children;
    Integer index = child.stream().map(e -> e.data).collect(Collectors.toList()).indexOf(dataVal);
    if (index < 0) {
      return null;
    }
    String temp = "";
    temp = child.get(index).decode(code.substring(1));
    return temp;

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
    boolean check = false;

    if (this.children.size() == sizeOfCode) {
      check = true;

      for (TreeNode child : this.children) {

        check = child.isCodeComplete(sizeOfCode);

        if (!check) {
          break;
        }

      }
    }

    return check;

  }


}
