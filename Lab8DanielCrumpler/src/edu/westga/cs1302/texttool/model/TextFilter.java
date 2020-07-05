package edu.westga.cs1302.texttool.model;

/**
 * The interface TextFilter
 * 
 * @author CS1302
 *
 */
public interface TextFilter {
	
	/**
	 * return a filtered version of the specified string
	 * 
	 * @precondition text != null
	 * @postcondition none
	 * 
	 * @param text to be filtered
	 * @return filtered string
	 */
	String filter(String text);
}
