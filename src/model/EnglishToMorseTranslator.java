package model;

import java.util.HashMap;
import java.util.Map;

public final class EnglishToMorseTranslator extends Translator {
	private static Map<Character, String> dictionary;
	private static EnglishToMorseTranslator instance;
	
	
	// TODO 5: look at this private constructor an important part of singleton
	private EnglishToMorseTranslator(){
		makeDictionary();
	}
	private void makeDictionary() {
		dictionary = new HashMap<Character, String>();
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
	
	public synchronized String translate(String token) {
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
		return translation.substring(0, limit);

	}
	public boolean isValidChar(char c){
		String s = String.valueOf(c);
		return s.matches("[\\(\\)\\.,=\"'@/\\-:]");
	}
	// TODO 4: This factory is also a singleton!
	// You can see that this is a getInstance class
	public static EnglishToMorseTranslator getInstance() {
		if(instance == null){
			instance = new EnglishToMorseTranslator();
		}
		return instance;
	}
}
