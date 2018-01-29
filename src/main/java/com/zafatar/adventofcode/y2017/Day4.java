package com.zafatar.adventofcode.y2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.zafatar.adventofcode.utils.FileReader;

public class Day4 implements Day {
	private String inputFile = "/2017/Day4/input";
	public ArrayList<ArrayList<String>> input;
	public int totalPhrases;
	public int notValid;
	public int valid;

	public Day4() {
		super();
		this.init();
	}

	@Override
	public void init() {
		this.input = new ArrayList<ArrayList<String>>();
		this.totalPhrases = 0;
		this.notValid = 0;
		this.valid = 0;
		
		this.prepareInput(this.inputFile);
	}
	
	@Override
	public void solvePart1() {
		this.init();
		
		for (ArrayList<String> phrase : this.input) {
			HashMap<String, Integer> seen = new HashMap<String, Integer>();
			for (String word : phrase) {
				if (seen.get(word) != null) {
					this.notValid++;
					break;
				} else
					seen.put(word, 1);
			}

			this.totalPhrases++;
		}

		int valid = this.totalPhrases - this.notValid;
		System.out.println("Day #4 - Part #1: " + valid + " / " + this.totalPhrases);
	}

	@Override
	public void solvePart2() {
		this.init();

		for (ArrayList<String> phrase : this.input) {
			HashMap<String, Integer> seen = new HashMap<String, Integer>();
			for (String word : phrase) {
				// This time, use canonical form of the string to detect
				// uniqueness in the array.
				String canonical = this.generateCanonical(word);
				if (seen.get(canonical) != null) {
					this.notValid++;
					break;
				} else
					seen.put(canonical, 1);
			}

			this.totalPhrases++;
		}

		int valid = this.totalPhrases - this.notValid;
		System.out.println("Day #4 - Part #2: " + valid + " / " + this.totalPhrases);
	}

	/**
	 * This method creates a comparable unique signature for each string.
	 * Basically, it orders the chars in the string alphabetically.
	 * 
	 * @param word the input string
	 * @return the ordered string of the input.
	 */
	private String generateCanonical(String word) {
		// Convert the string into char array.
		char[] charArray = word.toCharArray();

		// Sort the char array to create same unique signature
		// with the other possible words.
		Arrays.sort(charArray);
		String sortedString = new String(charArray);

		return sortedString;
	}

	@Override
	public void prepareInput(String filepath) {
		FileReader fr = new FileReader(filepath);

		String[] lines = fr.getContent().split("\n");
		for (int i = 0; i < lines.length; i++) {
			ArrayList<String> row = new ArrayList<String>();

			// TODO: better splitting.
			String[] phrases = lines[i].split(" ");
			for (String phrase : phrases) {
				row.add(phrase);
				// System.out.println(">" + phrase);
			}

			// System.out.println("\n");
			this.input.add(row);
		}
	}
}

/**
 * --- Day 4: High-Entropy Passphrases ---
 * 
 * A new system policy has been put in place that requires all accounts to use a
 * passphrase instead of simply a password. A passphrase consists of a series of
 * words (lowercase letters) separated by spaces.
 * 
 * To ensure security, a valid passphrase must contain no duplicate words.
 * 
 * For example:
 * 
 * - aa bb cc dd ee is valid. 
 * - aa bb cc dd aa is not valid - the word aa appears more than once. 
 * - aa bb cc dd aaa is valid - aa and aaa count as different words.
 * 
 * The system's full passphrase list is available as your puzzle input. How many
 * passphrases are valid?
 * 
 * Your puzzle answer was ***. --- Part Two ---
 * 
 * For added security, yet another system policy has been put in place. Now, a
 * valid passphrase must contain no two words that are anagrams of each other -
 * that is, a passphrase is invalid if any word's letters can be rearranged to
 * form any other word in the passphrase.
 * 
 * For example:
 * 
 * - abcde fghij is a valid passphrase. 
 * - abcde xyz ecdab is not valid - the letters from the third word can be 
 *   rearranged to form the first word. 
 * - a ab abc abd abf abj is a valid passphrase, because all letters need 
 *   to be used when forming another word.
 * - iiii oiii ooii oooi oooo is valid. 
 * - oiii ioii iioi iiio is not valid - any of these words can be 
 *   rearranged to form any other word.
 * 
 * Under this new system policy, how many passphrases are valid?
 * 
 * Your puzzle answer was ***.
 * 
 */