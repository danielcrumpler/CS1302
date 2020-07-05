package edu.westga.cs1302.casino.view.converter;

import javafx.util.converter.NumberStringConverter;

/**
 * The Class ValidWholeNumberStringConverter.
 * 
 * @author CS1302
 * @version Spring 2018
 */
public class ValidPositiveWholeNumberStringConverter extends NumberStringConverter {

	/**
	 * Removes all non-digits from the string and then converts it to a number.
	 * 
	 * @param input
	 *            The string to convert
	 */
	@Override
	public Number fromString(String input) {
		input = input.replaceAll("[^\\d]", "");

		return super.fromString(input);
	}

	/**
	 * Converts number to string with no formatting at all.
	 * 
	 * @param value
	 *            The number to convert
	 */
	@Override
	public String toString(Number value) {
		String output = "";
		output += value;
		return output;
	}

}
