package decoder;

/**
 * Encoding and decoding are common operations on data. Given data in the form of symbols it can be
 * encoded by translating each symbol into a unique code, possibly consisting of many symbols.
 * Decoding applies this process in reverse. This interface provides various methods and functions
 * for decoding operations.The interface provides method to addCode, decode the symbols, all the
 * symbols that are added to the Code tree and check if the code tree is complete.
 */
public interface Decoder {

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
  public void addCode(char symbol, String code) throws IllegalStateException;


  /**
   * This method takes an encoded message as a string, and generate the decoded message as a string
   * using the coding tree created thus far.
   *
   * @param code the code which is to be decoded.
   * @return the string of symbols that is decoded.
   * @throws IllegalStateException if the decoding fails.
   * @throws IllegalStateException if the code is null or empty.
   * @throws IllegalStateException if the code is illegal symbol.
   */
  public String decode(String code) throws IllegalStateException;

  /**
   * This method will return the codes entered thus far as a string. This string contains each
   * symbol x and its code yyy on a separate line, in the form x:yyy.
   *
   * @return the codes entered thus far as a string.
   */
  public String allCodes();

  /**
   * A isCodeComplete method that yields true if the code entered so far is complete,  false
   * otherwise. A code is said to be complete if every valid encoded message can be successfully
   * decoded. If the decoding is done by using a coding tree, then this condition is fulfilled if
   * the coding tree is full.
   *
   * @return if the code entered so far is complete, false otherwise.
   */
  public boolean isCodeComplete();


}