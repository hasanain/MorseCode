package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MorseCodeRunner {
	public static void main(String [] args) throws FileNotFoundException{
		Translator mtr = MorseToEnglishTranslator.getInstance();
		File file = new File("./gettysburg");
		Scanner scanner = new Scanner(file);
		StringBuffer translation = new StringBuffer();
		while(scanner.hasNextLine()){
			translation.append(mtr.translate(scanner.nextLine()) + "\n");
		}
		scanner.close();
		System.out.println(translation.toString());
	}
}
