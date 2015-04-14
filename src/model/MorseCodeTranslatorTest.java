package model;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MorseCodeTranslatorTest {
	Translator etm = EnglishToMorseTranslator.getInstance();
	Translator mte = MorseToEnglishTranslator.getInstance();
	String[] letters = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
			"....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
			"--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--.." };
	String[] numbers = { "-----", ".----", "..---", "...--", "....-", ".....",
			"-....", "--...", "---..", "----." };
	//						   .         ,         :         ?         '
	String[] morseChars = {".-.-.-", "--..--", "---...", "..--..", ".----.",
	//     -         /        ()         "        @         =
		"-....-", "-..-.", "-.--.-", ".-..-.", ".--.-.", "-...-"};
	char [] chars = {'.',',',':','?','\'','-','/', '(','"','@', '='};
	
	@Test
	public void testSingleChar() {
		char a = 'a';
		System.out.println(letters.length);
		for (int i = 0; i < 26; i++) {
			assertEquals(letters[i], etm.translate(String.valueOf(a++)));
		}
	}
	
	@Test
	public void testSingleDigit() {
		char a = '0';
		for (int i = 0; i < 10; i++) {
			assertEquals(numbers[i], etm.translate(String.valueOf(a++)));
		}
	}
	
	@Test
	public void testOtherChars(){
		for(int i = 0; i < morseChars.length; i++){
			assertEquals(morseChars[i], etm.translate(String.valueOf(chars[i])));
		}
		assertEquals(morseChars[7], etm.translate(String.valueOf(')')));
	}
	@Test
	public void testNumber() {
		char a = '0';
		for (int i = 0; i < 10; i++) {
			assertEquals(numbers[i], etm.translate(String.valueOf(a++)));
		}
	}
	@Test
	public void testSingleWord() {

		assertEquals(".... .- ... .- -. .- .. -.",
				etm.translate("hasanain"));
		assertEquals("-.-. --- -- -- .- -. -..", etm.translate("command"));
		assertEquals("--.. --- --- --", etm.translate("zoom"));
		assertEquals(".-- .- -..-", etm.translate("wax"));
		assertEquals("-... .- ... ....", etm.translate("bash"));
		assertEquals(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-..",
				etm.translate("abcdefghijkl"));
		assertEquals(
				"-- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..",
				etm.translate("mnopqrstuvwxyz"));

	}
	@Test
	public void Test2() {
		assertEquals("zoom", mte.translate("--.. --- --- --"));
	}
	@Test
	public void Test3() {
		assertEquals("hasanain",
				mte.translate(".... .- ... .- -. .- .. -."));
	}
	@Test
	public void Test4() {
		assertEquals(".... .- ... .- -. .- .. -.",
				etm.translate("Hasanain"));
	}
//	@Test
//	public void testValidChars(){
//		for(int i = 0; i < 26; i++){
//			assertTrue(mtr.isLetter((char)('a'+i)));
//		}
//		for(int i = 0; i < 10; i++){
//			assertTrue(mtr.isDigit((char)('0'+i)));
//		}
//	}
//	@Test
//	public void testInvalidChars(){
//		assertFalse(mtr.isLetter('{'));
//
//		assertFalse(mtr.isDigit('h'));
//		assertFalse(mtr.isDigit('^'));
//		assertFalse(mtr.isDigit(':'));
//		assertFalse(mtr.isDigit('c'));
//		assertFalse(mtr.isDigit('n'));
//		assertFalse(mtr.isDigit('v'));
//		assertFalse(mtr.isDigit('/'));
//		
//	}

}
