package edu.westga.cs1302.si.controller;

import java.util.Collection;
import java.util.Scanner;
import edu.westga.cs1302.si.model.SpellChecker;


/**
 * The Class DemoController.
 * 
 * @author CS1302
 */
public class DemoController {
	
	/**
	 * Demos functionality
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void run() {
		SpellChecker checker = new SpellChecker("words.txt");

		Scanner input = new Scanner(System.in);
		
		String word = this.getWordFromUser(input);
		while (word != null) {
			if (checker.isValid(word)) {
				System.out.println(word + " is spelled correctly");
			} else {
				System.out.println(word + " is misspelled");
				Collection<String> validWords = checker.getSpellingSuggestions(word);
				this.printSpellingSuggestions(validWords);
			}
			word = this.getWordFromUser(input);
		}
		input.close();
		System.out.println("Goodbye!");
	}

	/**
	 * Prompts the user for a word and reads the word from the specified scanner
	 * 
	 * @param input the Scanner 
	 * @return the word entered by the user, null if the user did not enter a word
	 */
	public String getWordFromUser(Scanner input) {
		System.out.println();
		System.out.print("Enter a word (or press enter to quit): ");
		
		String line = input.nextLine();
		line = line.trim();
	
		if (line.isEmpty()) {
			return null;
		}
		
		return line;
	}

	/**
	 * Prints collection of suggested spelling corrections
	 * 
	 * @param validWords collection of suggested words 
	 */
	private void printSpellingSuggestions(Collection<String> validWords) {
		if (!validWords.isEmpty()) {
			System.out.println("Suggested spellings:");
			for (String word : validWords) {
				System.out.println("\t" + word);
			}
		}
	}
}
