package model;
import java.util.HashMap;
import java.util.Map;

public class MorseCodeTranslatorNew {
	private static Map<Character, String> dictionary;
	private static Map<String, Character> dict2;
	//										 a       b      c       d     e     f       g
	public static String[] morseLetters = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
		//    h       i      j      k      l       m    n      o       p
			"....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
		//    q       r      s     t     u      v        w      x       y
			"--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
		//     z
			"--.." };
		//                                     0        1        2        3        4        5
	public static String[] morseNumbers = { "-----", ".----", "..---", "...--", "....-", ".....",
		//     6        7        8        9
			"-....", "--...", "---..", "----." };
		//								    .         ,         :         ?         '
	public static String[] morseChars = {".-.-.-", "--..--", "---...", "..--..", ".----.",
		//     -         /        ()         "        @         =
			"-....-", "-..-.", "-.--.-", ".-..-.", ".--.-.", "-...-"};
	
	public MorseCodeTranslator() {
		dictionary = new HashMap<Character, String>(47);
		makeDictionary();
		dict2 = new HashMap<String, Character>();
		makeDict2();
	}

	private void makeDict2() {
		char a = 'a';
		for(int i = 0; i < 26; i++){
			dict2.put(morseLetters[i], a++);
		}
		a ='0';
		for(int i = 0; i < 10 ; i++){
			dict2.put(morseNumbers[i], a++);
		}
		dict2.put(morseChars[0], '.');
		dict2.put(morseChars[1], ',');
		dict2.put(morseChars[2], ':');
		dict2.put(morseChars[3], '?');
		dict2.put(morseChars[4], '\'');
		dict2.put(morseChars[5], '-');
		dict2.put(morseChars[6], '/');
		dict2.put(morseChars[7], '(');
		dict2.put(morseChars[7], ')');
		dict2.put(morseChars[8], '"');
		dict2.put(morseChars[9], '@');
		dict2.put(morseChars[10], '=');
		
	}

	private void makeDictionary() {
		char a = 'a';
		for(int i = 0; i < 26; i++){
			dictionary.put(a++, morseLetters[i]);
		}
		a ='0';
		for(int i = 0; i < 10 ; i++){
			dictionary.put(a++, morseNumbers[i]);
		}
		dictionary.put('.', morseChars[0]);
		dictionary.put(',', morseChars[1]);
		dictionary.put(':', morseChars[2]);
		dictionary.put('?', morseChars[3]);
		dictionary.put('\'', morseChars[4]);
		dictionary.put('-', morseChars[5]);
		dictionary.put('/', morseChars[6]);
		dictionary.put('(', morseChars[7]);
		dictionary.put(')', morseChars[7]);
		dictionary.put('"', morseChars[8]);
		dictionary.put('@', morseChars[9]);
		dictionary.put('=', morseChars[10]);
	}

	public String translateToMorse(String token) {
		token = token.toLowerCase();
		StringBuilder translation = new StringBuilder();
		for (String s : token.split(" ")){
			for(char a : s.toCharArray()){
				if(dictionary.get(a) != null){
					translation.append(dictionary.get(a) + " ");
				}
			}
			translation.append(" | ");
		}
		int limit = translation.length();
		if(limit > 4){
			limit = limit -4;
		}
		return translation.substring(0, limit);
	}
	public String translateToEnglish(String token){
		StringBuilder translation = new StringBuilder();
		for(String s : token.split(" ")){
			if(dict2.get(s) !=null){
				translation.append(dict2.get(s));
			}else if(s.equals("|") || s.equalsIgnoreCase("/")){
				translation.append(" ");
			}
		}
		return translation.toString().trim();
	}
	private String findMorse(String token){
		for(int i = 0; i < morseLetters.length; i++){
			if(token.equals(morseLetters[i])){
				return morseLetters[i];
			}
		}
		for(int i = 0; i < morseNumbers.length; i++){
			if(token.equals(morseLetters[i])){
				return morseLetters[i];
			}
		}
		for(int i= 0; i < morseChars.length; i++){
			if(token.equals(morseChars[i])){
				return morseChars[i];
			}
		}
		return null;
	}
	
	public boolean isLetter(String c){
		return c.matches("[A-Za-z]");
	}
	public boolean isDigit(String c){
		return c.matches("[0-9]");
	}
	public boolean isValidChar(String c){
		return c.matches("[\\(\\)\\.,=\"'@/\\-:]");
	}