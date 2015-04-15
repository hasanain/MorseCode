package model;

public abstract class Translator {
	//										 a       b      c       d     e     f       g
	protected static String[] morseLetters = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
		//    h       i      j      k      l       m    n      o       p
			"....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
		//    q       r      s     t     u      v        w      x       y
			"--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
		//     z
			"--.." };
		//                                     0        1        2        3        4        5
	protected static String[] morseNumbers = { "-----", ".----", "..---", "...--", "....-", ".....",
		//     6        7        8        9
			"-....", "--...", "---..", "----." };
		//								    .         ,         :         ?         '
	protected static String[] morseChars = {".-.-.-", "--..--", "---...", "..--..", ".----.",
		//     -         /        ()         "        @         =
			"-....-", "-..-.", "-.--.-", ".-..-.", ".--.-.", "-...-"};
	

	//Types of Translators this factory can make
	public static int ENGLISH_TO_MORSE = 0x0;
	public static int MORSE_TO_ENGLISH = 0x1;
	
	public abstract String translate(String token);
	
	public static Translator makeTranslator(int type){
		if(type == ENGLISH_TO_MORSE){
			return EnglishToMorseTranslator.getInstance();
		}
		if(type == MORSE_TO_ENGLISH){
			return MorseToEnglishTranslator.getInstance();
		}else{
			return null;
		}
	}
	
}
