package model;
import java.util.HashMap;
import java.util.Map;

public class MorseCodeTranslator_127A {
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
	

	public String translateToMorse(String token) {
		token = token.toLowerCase();
		StringBuilder translation = new StringBuilder();
		for (String s : token.split(" ")){
			for(char a : s.toCharArray()){
				if(findMorse(a) != null){
					translation.append(findMorse(a) + " ");
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
			if(findEnglish(s) !='\0'){
				translation.append(findEnglish(s));
			}else if(s.equals("|") || s.equals("/")){
				translation.append(" ");
			}
		}
		return translation.toString().trim();
	}
	private char findEnglish(String token){
		for(int i = 0; i < morseLetters.length; i++){
			if(morseLetters[i].equals(token)){
				return (char) (i+97);
			}
		}
		for(int i = 0; i < morseNumbers.length; i++){
			if(morseNumbers[i].equals(token)){
				return (char) (i+48);
			}
		}
		return '\0';
	}

	private String findMorse(char token){
		if(isLetter(token)){
			return morseLetters[token - 97];
			
		}else if(isDigit(token)){
			
			return morseNumbers[token - 48];
		}
		return null;
	}

	public boolean isLetter(char c){
		return c >= 97 && c <= 122;
	}
	public boolean isDigit(char c){
		return c >=48 && c <=57;
	}
}
