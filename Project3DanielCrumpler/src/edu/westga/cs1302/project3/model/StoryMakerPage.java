package edu.westga.cs1302.project3.model;

import edu.westga.cs1302.project3.resources.ExceptionMessages;
import storyreader.util.Page;

/**
 * The Class StoryMakerPage that stores the imagePath and text of a Page.
 * 
 * @author Daniel Crumpler
 */
public class StoryMakerPage implements Page {

	private String imagePath;
	private String text;

	/**
	 * Instantiates a new StoryMakerPage
	 * 
	 * @precondition text != null and text is not empty AND imagePath != null and
	 *               imagePath is not empty
	 * @postcondition getText() == text && getImagePath == imagePath
	 * 
	 * @param imagePath the image path
	 * @param text      the text of the page
	 */
	public StoryMakerPage(String imagePath, String text) {
		if (imagePath == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_IMAGE_PATH);
		}
		if (imagePath.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_IMAGE_PATH);
		}
		if (text == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_TEXT);
		}
		if (text.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_TEXT);
		}

		this.imagePath = imagePath;
		this.text = text;
	}

	@Override
	public String getImagePath() {
		return this.imagePath;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void setImagePath(String imagePath) {
		if (imagePath == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_IMAGE_PATH);
		}
		if (imagePath.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_IMAGE_PATH);
		}
		this.imagePath = imagePath;
	}

	@Override
	public void setText(String text) {
		if (text == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_TEXT);
		}
		if (text.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_TEXT);
		}
		this.text = text;
	}

	@Override
	public String toString() {
		String str = this.text;
		String page = "Page: ";
		if (this.text.length() > 9) {
			String str2 = str.substring(0, 9);
			return page + str2 + "...";
		} else {
			str = this.text;
			return page + str;
		}
	}
}
