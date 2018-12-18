package decoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the concrete implementation of the Decoder Interface which provides various methods
 * and functions for  decoding operations.
 */
public class DecoderImpl implements Decoder {

  private Tree root;
  private String allCode = "";
  private List<Character> encodingArray = new ArrayList<Character>();
  private String decode;
  private HashMap<String, String> symbolDictonary = new HashMap<String, String>();


  /**
   * This is a public constructor that Instantiates a new Decoder.
   *
   * @param codingSymbols the code symbol.
   * @throws IllegalArgumentException the argument is empty or null.
   */
  public DecoderImpl(String codingSymbols) throws IllegalArgumentException {
    if (codingSymbols == null || codingSymbols.isEmpty()) {
      throw new IllegalArgumentException("The argument cannot be empty or null.");
    }

    root = new GroupTreeNode("root");

    for (int i = 0; i < codingSymbols.length(); i++) {
      encodingArray.add(codingSymbols.charAt(i));
    }

  }

  /**
   * This method that takes a symbol and its code as a character and string respectively. This
   * method should add this code to the coding tree.
   *
   * @param symbol the symbol which is to be encoded.
   * @param code   the code by which the symbol is encoded with.
   * @throws IllegalStateException if the code contains symbols other than the coding symbols.
   * @throws IllegalStateException prefix is detected or duplicate.
   * @throws IllegalStateException if duplicate symbol is present.
   * @throws IllegalStateException if symbol or code is null or empty.
   */
  @Override
  public void addCode(char symbol, String code) throws IllegalStateException {

    if (symbol == '\0' || code == null || code.equals("")) {
      throw new IllegalStateException("the code or symbol cannot be null or empty.");
    }

    for (int i = 0; i < code.length(); i++) {
      if (!encodingArray.contains(code.charAt(i))) {
        throw new IllegalStateException("the code contains illegal entry.");
      }
    }
    allCode = allCode + symbol + ":" + code + "\n";


    for (String sym : symbolDictonary.keySet()) {

      String indir = symbolDictonary.get(sym);

      String a = indir.length() > code.length() ? indir : code;
      String b = indir.length() <= code.length() ? indir : code;

      if (b.equals(a.substring(0, b.length()))) {
        throw new IllegalStateException("prefix code detected!");
      }
    }

    if (!symbolDictonary.keySet().contains(symbol + "")) {
      root = root.addChild(symbol + "", code);
    } else {
      throw new IllegalStateException("trying to insert duplicate symbol.");
    }

    symbolDictonary.put(symbol + "", code);

  }

  /**
   * This method takes an encoded message as a string, and generates the decoded message as a string
   * using the coding tree created thus far.
   *
   * @param code the code which is to be decoded.
   * @return the string of symbols that is decoded.
   * @throws IllegalStateException if the decoding fails.
   * @throws IllegalStateException if the code is null or empty.
   * @throws IllegalStateException if the code is illegal symbol.
   */
  @Override
  public String decode(String code) throws IllegalStateException {
    if (code == null || code.equals("")) {
      throw new IllegalStateException("the code cannot be null or empty.");
    }
    Tree iterator = root;
    decode = "";

    for (int i = 0; i < code.length(); i++) {
      if (!encodingArray.contains(code.charAt(i))) {
        throw new IllegalStateException("The encoded string contains illegal symbol");
      }
    }

    code = decoder(code, iterator);

    if (code.length() != 0) {
      throw new IllegalStateException("there is some problem with decoding.");
    }

    return decode;
  }

  /**
   * This method will return the codes entered thus far as a string. This string contains each
   * symbol x and its code yyy on a separate line, in the form x:yyy.
   *
   * @return the codes entered thus far as a string.
   */
  @Override
  public String allCodes() {
    return allCode;
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
  public boolean isCodeComplete() {

    Tree iterator = root;

    return iterator.isCodeComplete(encodingArray.size());
  }

  /**
   * This method does operations on the coded message that was passed.
   *
   * @param code     the encoded string.
   * @param iterator the pointer.
   * @return the code as a string.
   */
  private String decoder(String code, Tree iterator) {
    for (int i = 0; i < code.length(); i++) {
      String decodedChar = iterator.decode(code.substring(0, i + 1));

      if (decodedChar != null) {
        decode = decode + decodedChar;
        code = code.substring(i + 1);
        i = -1;
      }
    }

    return code;
  }


}

