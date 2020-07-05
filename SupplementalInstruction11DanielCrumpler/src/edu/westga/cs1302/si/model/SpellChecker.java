package edu.westga.cs1302.si.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * The Class SpellChecker.
 * 
 * @author CS1302
 */
public class SpellChecker {

	private Dictionary dictionary;

	/**
	 * Instantiates a new SpellChecker.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public SpellChecker() {
		this.dictionary = new Dictionary();
	}

	/**
	 * Instantiates a new SpellChecker.
	 * 
	 * @precondition file != null
	 * @postcondition none
	 * 
	 * @param filename name of file with valid words
	 */
	public SpellChecker(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException("filename cannot be null");
		}
		this.dictionary = new Dictionary();
		this.loadWordsFromFile(filename);
	}

	/**
	 * Checks if the specified word is spelled correctly.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @param word the word to be checked for
	 * @return true if the word is contained in the dictionary.
	 */
	public boolean isValid(String word) {
		return this.dictionary.contains(word);
	}

	/**
	 * Determines possible correct spellings of the specified word
	 * 
	 * @precondition word != null
	 * @postcondition none
	 *
	 * @param word the word to be checked for
	 * @return true if the word is contained in the dictionary.
	 */
	public Collection<String> getSpellingSuggestions(String word) {
		if (word == null) {
			throw new IllegalArgumentException("word cannot be null");
		}
		ArrayList<String> suggestedWords = new ArrayList<String>();

		this.findWordsByDroppingLetter(word, suggestedWords);
		this.findWordsByAddingLetter(word, suggestedWords);
		this.findWordsByReplacingLetter(word, suggestedWords);
		this.findWordsBySwappingLetters(word, suggestedWords);

		return suggestedWords;
	}

	/**
	 * Loads the valid words from file
	 * 
	 * @param filename the name of the file to load the words from
	 */
	private void loadWordsFromFile(String wordfile) {
		File file = new File(wordfile);
		try (Scanner wordScanner = new Scanner(file)) {
			while (wordScanner.hasNextLine()) {
				String word = wordScanner.nextLine().trim();
				this.dictionary.add(word);
			}
		} catch (IOException e) {
			System.err.println("Failed to read word file " + e.getStackTrace());
		}
	}

	/**
	 * Add all valid words to the specified collection that result from removing
	 * one-by-one a letter from the given word
	 * 
	 * @param word
	 * @param suggestedWords
	 */
	private void findWordsByDroppingLetter(String word, Collection<String> suggestedWords) {
		for (int i = 0; i < word.length(); i++) {
			String newWord = word.substring(0, i) + word.substring(i + 1, word.length());
			if (this.isValid(newWord) && !suggestedWords.contains(newWord)) {
				suggestedWords.add(newWord);
			}
		}
	}

	/**
	 * Add all valid words to the specified collection that result from adding a
	 * single letter to the given word
	 * 
	 * @param word
	 * @param suggestedWords
	 */
	private void findWordsByAddingLetter(String word, Collection<String> suggestedWords) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (char character : alphabet) {
			String atFront = character + word;
			String atBack = word + character;
			if (this.isValid(atFront) && !suggestedWords.contains(atFront)) {
				suggestedWords.add(atFront);
			}
			if (this.isValid(atBack) && !suggestedWords.contains(atBack)) {
				suggestedWords.add(atBack);
			}
		}
	}

	/**
	 * Add all valid words to the specified collection that result from replacing a
	 * single letter by another letter in the given word
	 * 
	 * @param word
	 * @param suggestedWords
	 */
	private void findWordsByReplacingLetter(String word, Collection<String> suggestedWords) {
		String[] results = new String[26 * word.length()];
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < 26; j++) {
				results[26 * i + j] = word.substring(0, i) + (char) ('a' + j) + word.substring(i + 1);
			}
		}
		for (String result : results) {
			if (this.isValid(result) && !suggestedWords.contains(result)) {
				suggestedWords.add(result);
			}
		}
	}

	/**
	 * Add all valid words to the specified collection that result from swapping two
	 * neighboring letters in the given word.
	 * 
	 * @param word
	 * @param suggestedWords
	 */
	private void findWordsBySwappingLetters(String word, Collection<String> suggestedWords) {
		for (int i = 0; i < word.length() - 1; i++) {
			String working = word.substring(0, i);
			working = working + word.charAt(i + 1);
			working = working + word.charAt(i);
			working = working.concat(word.substring((i + 2)));
			if (this.isValid(working) && !suggestedWords.contains(working)) {
				suggestedWords.add(working);
			}
		}
	}
}
