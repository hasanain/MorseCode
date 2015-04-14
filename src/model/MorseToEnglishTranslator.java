package model;

import java.util.HashMap;
import java.util.Map;

public final class MorseToEnglishTranslator extends Translator {
	private static Map<String, Character> dictionary;
	private static MorseToEnglishTranslator instance;
	private MorseToEnglishTranslator(){
		makeDictionary();
	}
	private void makeDictionary() {
		dictionary = new HashMap<String, Character>();
		char a = 'a';
		for(int i = 0; i < 26; i++){
			dictionary.put(morseLetters[i], a++);
		}
		a ='0';
		for(int i = 0; i < 10 ; i++){
			dictionary.put(morseNumbers[i], a++);
		}
		dictionary.put(morseChars[0], '.');
		dictionary.put(morseChars[1], ',');
		dictionary.put(morseChars[2], ':');
		dictionary.put(morseChars[3], '?');
		dictionary.put(morseChars[4], '\'');
		dictionary.put(morseChars[5], '-');
		dictionary.put(morseChars[6], '/');
		dictionary.put(morseChars[7], '(');
		dictionary.put(morseChars[7], ')');
		dictionary.put(morseChars[8], '"');
		dictionary.put(morseChars[9], '@');
		dictionary.put(morseChars[10], '=');
		
	}
	public synchronized String translate(String token){
		StringBuilder translation = new StringBuilder();
		for(String s : token.split(" ")){
			if(dictionary.get(s) !=null){
				translation.append(dictionary.get(s));
			}else if(s.equals("|") || s.equals("/")){
				translation.append(" ");
			}
		}
		return translation.toString().trim();
	}
	
	
	public static MorseToEnglishTranslator getInstance() {
		if(instance == null){
			instance = new MorseToEnglishTranslator();
		}
		return instance;
	}
	public boolean isMorse(String s){
		
		return s.matches("[\\.\\-\\s|/]+");
	}
}
