package model;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.SwingWorker;
/**
 * This class is a singleton that translates Morse code to English and vice-versa.
 * 
 * @author Hasanain Jamal
 *
 */
public class MorseCodeTranslator extends SwingWorker<String, String>{
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
	private String token;
	/**
	 * variable to store the singleton instance of the translator
	 */
	private static MorseCodeTranslator instance;
	
	private MorseCodeTranslator() {
		dictionary = new HashMap<Character, String>();
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

	public static MorseCodeTranslator getInstance(){
		if(instance == null){
			instance = new MorseCodeTranslator();
		}
		return instance;
	}
	
	public String translateToMorse(String token) {
		StringBuilder translation = new StringBuilder();
		
		int limit = 0;
		String newToken = token.toLowerCase();
		for (String s : newToken.split(" ")){
			char lastChar = '\0';
			for(char a : s.toCharArray()){
				if(dictionary.get(a) != null){
					translation.append(dictionary.get(a) + " ");
				}
				if(a == '\n'){
					translation.append('\n');
					lastChar = a;
				}
			}
			if(lastChar != '\n'){
				translation.append(" | ");
			}
		}
		limit = translation.length();
		if(limit > 4){
			limit = limit -4;
		}
//		System.out.println(translation);
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

	public boolean isLetter(char c){
		return c >= 97 && c <= 122;
	}
	public boolean isDigit(char c){
		return c >= 48 && c <= 57;
	}
	public boolean isMorse(String s){
		
		return s.matches("[\\.\\-\\s|/]*");
	}
	public boolean isValidChar(char c){
		String s = String.valueOf(c);
		return s.matches("[\\(\\)\\.,=\"'@/\\-:]");
	}
	public String translate(String token){
		this.token = token;
		try {
			return doInBackground();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String doInBackground() throws Exception {
		
		if(isMorse(token)){
			return this.translateToEnglish(token);
		}else{
			return this.translateToMorse(token);
		}
	}
}
