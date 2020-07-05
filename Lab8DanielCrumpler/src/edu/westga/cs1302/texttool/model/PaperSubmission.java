package edu.westga.cs1302.texttool.model;

/**
 * The Class PaperSubmission
 * 
 * @author CS1302
 */
public class PaperSubmission {

	private String title;
	private String shortAbstract;

	/**
	 * Instantiates a new paper submission
	 * 
	 * @precondition none
	 * @postcondition getTitle().isEmpty() and getAbstract.isEmpty()
	 */
	public PaperSubmission() {
		this.title = "";
		this.shortAbstract = "";
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @precondition title != null
	 * @postcondition getTitle().equals(researchTitle)
	 * @param title the new title
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("title cannot be null");
		}
		this.title = title;
	}

	/**
	 * Gets the short abstract.
	 *
	 * @return the short abstract
	 */
	public String getAbstract() {
		return this.shortAbstract;
	}

	/**
	 * Sets the short abstract.
	 * 
	 * @precondition shortAbstract != null
	 * @postcondition getAbstract.equals(shortAbstract)
	 * 
	 * @param shortAbstract the new short abstract
	 */
	public void setAbstract(String shortAbstract) {
		if (shortAbstract == null) {
			throw new IllegalArgumentException("abstract cannot be null");
		}
		this.shortAbstract = shortAbstract;
	}

	/**
	 * Applies the specified filter to the title and abstract.
	 * 
	 * @precondition textFilter != null
	 * @postcondition getTitle().equals(textFilter.filter(getTitle()@pre)) and
	 *                getAbstract().equals(textFilter.filter(getAbstract()@pre))
	 * 
	 * @param textFilter the filter
	 */
	public void filter(TextFilter textFilter) {
		if (textFilter == null) {
			throw new IllegalArgumentException("text filter cannot be null");
		}
		this.title = textFilter.filter(this.title);
		this.shortAbstract = textFilter.filter(this.shortAbstract);
	}

	/**
	 * Calculates the word count
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the string with the word count
	 */
	public String calculateWordCount() {
		String[] words = this.shortAbstract.split("\\s+");
		int count = words.length;
		return "Word Count: " + count;
	}

	/**
	 * Capitalize the first word in a sentence
	 * 
	 * @precondition none
	 * @postcondition abstract is set to the new string
	 */
	public void capitalizeSentences() {
		char[] arr = this.getAbstract().toCharArray();
		boolean cap = true;
		for (int i = 0; i < arr.length; i++) {
			if (cap && !Character.isWhitespace(arr[i])) {
				arr[i] = Character.toUpperCase(arr[i]);
				cap = false;
			} else {
				if (arr[i] == '.' || arr[i] == '?') {
					cap = true;
				}
			}
		}
		String finalString = new String(arr);
		this.setAbstract(finalString);

	}
	
	/**
	 * Capitalize the first word in the title
	 * 
	 * @precondition none
	 * @postcondition title is set to the new string
	 */
	public void capitalizeTitle() {
		char[] arr = this.getTitle().toCharArray();
		boolean cap = true;
		for (int i = 0; i < arr.length; i++) {
			if (cap && !Character.isWhitespace(arr[i])) {
				arr[i] = Character.toUpperCase(arr[i]);
				cap = false;
			} else {
				if (arr[i] == '.' || arr[i] == '?') {
					cap = true;
				}
			}
		}
		String finalString = new String(arr);
		this.setTitle(finalString);

	}
}
