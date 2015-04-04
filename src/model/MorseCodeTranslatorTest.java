package model;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MorseCodeTranslatorTest {
	MorseCodeTranslator mtr = MorseCodeTranslator.getInstance();
	
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
			assertEquals(letters[i], mtr.translateToMorse(String.valueOf(a++)));
		}
	}
	
	@Test
	public void testSingleDigit() {
		char a = '0';
		for (int i = 0; i < 10; i++) {
			assertEquals(numbers[i], mtr.translateToMorse(String.valueOf(a++)));
		}
	}
	
	@Test
	public void testOtherChars(){
		for(int i = 0; i < morseChars.length; i++){
			assertEquals(morseChars[i], mtr.translateToMorse(String.valueOf(chars[i])));
		}
		assertEquals(morseChars[7], mtr.translateToMorse(String.valueOf(')')));
	}
	@Test
	public void testNumber() {
		char a = '0';
		for (int i = 0; i < 10; i++) {
			assertEquals(numbers[i], mtr.translateToMorse(String.valueOf(a++)));
		}
	}
	@Test
	public void testSingleWord() {

		assertEquals(".... .- ... .- -. .- .. -.",
				mtr.translateToMorse("hasanain"));
		assertEquals("-.-. --- -- -- .- -. -..", mtr.translateToMorse("command"));
		assertEquals("--.. --- --- --", mtr.translateToMorse("zoom"));
		assertEquals(".-- .- -..-", mtr.translateToMorse("wax"));
		assertEquals("-... .- ... ....", mtr.translateToMorse("bash"));
		assertEquals(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-..",
				mtr.translateToMorse("abcdefghijkl"));
		assertEquals(
				"-- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..",
				mtr.translateToMorse("mnopqrstuvwxyz"));

	}
	@Test
	public void Test2() {
		assertEquals("zoom", mtr.translateToEnglish("--.. --- --- --"));
	}

	public void Test3() {
		assertEquals("hasanain",
				mtr.translateToMorse(".... .- ... .- -. .- .. -."));
	}

	public void Test4() {
		assertEquals(".... .- ... .- -. .- .. -.",
				mtr.translateToMorse("Hasanain"));
	}
	@Test
	public void testValidChars(){
		for(int i = 0; i < 26; i++){
			assertTrue(mtr.isLetter((char)('a'+i)));
		}
		for(int i = 0; i < 10; i++){
			assertTrue(mtr.isDigit((char)('0'+i)));
		}
	}
	@Test
	public void testInvalidChars(){
		assertFalse(mtr.isLetter('{'));

		assertFalse(mtr.isDigit('h'));
		assertFalse(mtr.isDigit('^'));
		assertFalse(mtr.isDigit(':'));
		assertFalse(mtr.isDigit('c'));
		assertFalse(mtr.isDigit('n'));
		assertFalse(mtr.isDigit('v'));
		assertFalse(mtr.isDigit('/'));
		
	}

}
