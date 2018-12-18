import org.junit.Before;
import org.junit.Test;

import decoder.Decoder;
import decoder.DecoderImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * This class is used to test the implementation of Decoder interface.
 */
public class DecoderTest {

  Decoder d = new DecoderImpl("01");
  Decoder d1 = new DecoderImpl("0123456789ABCDEF");


  /**
   * This method is to build a binary code tree and hex code tree.
   */
  @Before
  public void setUp() {
    d.addCode('A', "0100");
    d.addCode('B', "111101");
    d.addCode('C', "11000");
    d.addCode('D', "10110");
    d.addCode('E', "000");
    d.addCode('F', "11100");
    d.addCode('G', "111010");
    d.addCode('H', "1001");
    d.addCode('I', "0110");
    d.addCode('J', "11111110");
    d.addCode('K', "1111110");
    d.addCode('L', "10111");
    d.addCode('M', "11010");
    d.addCode('N', "0111");
    d.addCode('O', "0101");
    d.addCode('P', "111100");
    d.addCode('Q', "1111111110");
    d.addCode('R', "1010");
    d.addCode('S', "1000");
    d.addCode('T', "001");
    d.addCode('U', "11001");
    d.addCode('V', "111110");
    d.addCode('W', "11011");
    d.addCode('X', "111111110");
    d.addCode('Y', "111011");
    d.addCode('Z', "1111111111");

    d1.addCode('[', "5B");
    d1.addCode('\\', "5C");
    d1.addCode(']', "5D");
    d1.addCode('^', "5E");
    d1.addCode('_', "5F");
    d1.addCode('`', "60");
    d1.addCode('{', "7B");
    d1.addCode('|', "7C");
    d1.addCode('}', "7D");
    d1.addCode('~', "7E");
    d1.addCode(' ', "20");
    d1.addCode('!', "21");
    d1.addCode('"', "22");
    d1.addCode('#', "23");
    d1.addCode('$', "24");
    d1.addCode('%', "25");
    d1.addCode('&', "26");
    d1.addCode('\'', "27");
    d1.addCode('(', "28");
    d1.addCode(')', "29");
    d1.addCode('*', "2A");
    d1.addCode('+', "2B");
    d1.addCode(',', "2C");
    d1.addCode('-', "2D");
    d1.addCode('.', "2E");
    d1.addCode('/', "2F");
    d1.addCode('a', "61");
    d1.addCode('b', "62");
    d1.addCode('c', "63");
    d1.addCode('d', "64");
    d1.addCode('e', "65");
    d1.addCode('f', "66");
    d1.addCode('g', "67");
    d1.addCode('h', "68");
    d1.addCode('i', "69");
    d1.addCode('j', "6A");
    d1.addCode('k', "6B");
    d1.addCode('l', "6C");
    d1.addCode('m', "6D");
    d1.addCode('n', "6E");
    d1.addCode('o', "6F");
    d1.addCode('p', "70");
    d1.addCode('q', "71");
    d1.addCode('r', "72");
    d1.addCode('s', "73");
    d1.addCode('t', "74");
    d1.addCode('u', "75");
    d1.addCode('v', "76");
    d1.addCode('w', "77");
    d1.addCode('x', "78");
    d1.addCode('y', "79");
    d1.addCode('Z', "7A");
    d1.addCode('0', "30");
    d1.addCode('1', "31");
    d1.addCode('2', "32");
    d1.addCode('3', "33");
    d1.addCode('4', "34");
    d1.addCode('5', "35");
    d1.addCode('6', "36");
    d1.addCode('7', "37");
    d1.addCode('8', "38");
    d1.addCode('9', "39");


  }


  /**
   * This method is to check if the code tree is instantiated properly.
   */
  @Test
  public void testCodeTreeInstantiation() {
    try {
      Decoder dempty = new DecoderImpl("");
    } catch (Exception e) {
      assertEquals("The argument cannot be empty or null.", e.getMessage());
    }

    try {
      Decoder dnull = new DecoderImpl(null);
    } catch (Exception e) {
      assertEquals("The argument cannot be empty or null.", e.getMessage());
    }

    Decoder dq = new DecoderImpl("10");
    dq.addCode('A', "10");
    assertEquals("A", dq.decode("10"));
    dq.addCode('B', "00");
    assertEquals("B", dq.decode("00"));
    dq.addCode('C', "11");
    assertEquals("C", dq.decode("11"));

    try {
      dq.addCode('D', "");
    } catch (Exception e) {
      assertEquals("the code or symbol cannot be null or empty.", e.getMessage());
    }

  }

  /**
   * This method is used to check if the code tree is complete or not.
   */
  @Test
  public void testIsCodeComplete() {

    Decoder dempty = new DecoderImpl("01");
    assertEquals(false, dempty.isCodeComplete());

    Decoder dincomplete = new DecoderImpl("01");
    dincomplete.addCode('A', "11");
    dincomplete.addCode('B', "0");

    assertEquals(false, dincomplete.isCodeComplete());

    Decoder dcomplete = new DecoderImpl("01");
    dcomplete.addCode('A', "1");
    dcomplete.addCode('B', "0");
    assertEquals(true, dcomplete.isCodeComplete());

    Decoder dcomplete1 = new DecoderImpl("01");
    dcomplete1.addCode('A', "10");
    dcomplete1.addCode('B', "11");
    dcomplete1.addCode('C', "0");
    assertEquals(true, dcomplete1.isCodeComplete());


  }

  /**
   * This method is to test encoding with binary tree.
   */
  @Test
  public void testDecodingWithBinaryEncoding() {

    assertEquals("SOUMYADEEP", d.decode("100001011100111010111011010010110000000111100"));
    assertEquals("SINHA", d.decode("10000110011110010100"));
    assertEquals("IS", d.decode("01101000"));
    assertEquals("GREAT", d.decode("11101010100000100001"));

    try {
      d.decode("1000010111001110101110110100101100000001111001");
      fail();
    } catch (Exception e) {
      assertEquals("there is some problem with decoding.", e.getMessage());
    }

    try {
      d.decode("1011012");
      fail();
    } catch (Exception e) {
      assertEquals("The encoded string contains illegal symbol", e.getMessage());
    }

    try {
      d.decode("");
      fail();
    } catch (Exception e) {
      assertEquals("the code cannot be null or empty.", e.getMessage());
    }

    try {
      d.decode(null);
      fail();
    } catch (Exception e) {
      assertEquals("the code cannot be null or empty.", e.getMessage());
    }


    Decoder dch = new DecoderImpl("10");
    dch.addCode('a', "10");
    dch.addCode('b', "11");

    try {
      dch.decode("101100");
    } catch (Exception e) {
      assertEquals("there is some problem with decoding.", e.getMessage());
    }


  }

  /**
   * This method is used to check the add code method.
   */
  @Test
  public void testAddCode() {

    Decoder normaldecode = new DecoderImpl("01");
    normaldecode.addCode('A', "10");
    assertEquals("A", normaldecode.decode("10"));
    normaldecode.addCode('B', "00");
    assertEquals("B", normaldecode.decode("00"));
    normaldecode.addCode('C', "11");
    assertEquals("C", normaldecode.decode("11"));
    assertEquals("AB", normaldecode.decode("1000"));
    assertEquals("ABC", normaldecode.decode("100011"));

    Decoder illegalAddCode = new DecoderImpl("01");
    try {
      illegalAddCode.addCode('\0', "0");
    } catch (Exception e) {
      assertEquals("the code or symbol cannot be null or empty.", e.getMessage());
    }

    try {
      illegalAddCode.addCode('A', "2");
    } catch (Exception e) {
      assertEquals("the code contains illegal entry.", e.getMessage());
    }

    try {
      illegalAddCode.addCode('A', "0");
      illegalAddCode.addCode('A', "1");
    } catch (Exception e) {
      assertEquals("trying to insert duplicate symbol.", e.getMessage());
    }

    try {
      illegalAddCode.addCode('A', "0");
      illegalAddCode.addCode('B', "01");
    } catch (Exception e) {
      assertEquals("prefix code detected!", e.getMessage());
    }

    try {
      illegalAddCode.addCode('A', "");
    } catch (Exception e) {
      assertEquals("the code or symbol cannot be null or empty.", e.getMessage());
    }

    try {
      illegalAddCode.addCode('\0', "");
    } catch (Exception e) {
      assertEquals("the code or symbol cannot be null or empty.", e.getMessage());
    }

  }

  /**
   * This method is used to check the add code method.
   */
  @Test
  public void testAllCodes() {
    Decoder emptydecode = new DecoderImpl("01");
    assertEquals("", emptydecode.allCodes());
    emptydecode.addCode('A', "1010");
    assertEquals("A:1010\n", emptydecode.allCodes());
    emptydecode.addCode('B', "010");
    assertEquals("A:1010\nB:010\n", emptydecode.allCodes());
    emptydecode.addCode('C', "000");
    assertEquals("A:1010\nB:010\nC:000\n", emptydecode.allCodes());
  }

  /**
   * This method is to test encoding with hexadecimal tree.
   */
  @Test
  public void testDecodingWithHexadecimalEncoding() {

    assertEquals("soumyadeep2011#$%^+.", d1.decode("736F756D796164656570323031312324255E2B2E"));


    try {
      d1.decode("736F756D796164656570323031312324255E2B2E2");
      fail();
    } catch (Exception e) {
      assertEquals("there is some problem with decoding.", e.getMessage());
    }

    try {
      d1.decode("73T");
      fail();
    } catch (Exception e) {
      assertEquals("The encoded string contains illegal symbol", e.getMessage());
    }

    try {
      d1.decode("");
      fail();
    } catch (Exception e) {
      assertEquals("the code cannot be null or empty.", e.getMessage());
    }

    try {
      d1.decode(null);
      fail();
    } catch (Exception e) {
      assertEquals("the code cannot be null or empty.", e.getMessage());
    }


  }


}