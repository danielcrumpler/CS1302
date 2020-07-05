package edu.westga.cs1302.texttool.viewmodel;

import edu.westga.cs1302.texttool.model.PaperSubmission;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class TextToolViewModel.
 * 
 * @author CS1302
 */
public class TextToolViewModel {

	private final StringProperty titleProperty;
	private final StringProperty abstractProperty;
	private final StringProperty wordCountProperty;

	private PaperSubmission paperSubmission;

	/**
	 * Instantiates a new password manager gui view model.
	 */
	public TextToolViewModel() {
		this.titleProperty = new SimpleStringProperty("");
		this.abstractProperty = new SimpleStringProperty("");
		this.wordCountProperty = new SimpleStringProperty();
		this.paperSubmission = new PaperSubmission();
	}

	/**
	 * Gets the title property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the title property
	 */
	public StringProperty titleProperty() {
		return this.titleProperty;
	}

	/**
	 * Gets the abstract property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the abstract property
	 */
	public StringProperty abstractProperty() {
		return this.abstractProperty;
	}

	/**
	 * Gets the word count property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the word count property
	 */
	public StringProperty wordCountProperty() {
		return this.wordCountProperty;
	}

	/**
	 * Sets the title of this paper submission.
	 *
	 * @precondition none
	 * @postcondition the title of this paper submission has been set to the title
	 *                entered by the user
	 */
	public void setTitle() {
		this.paperSubmission.setTitle(this.titleProperty.get());
	}

	/**
	 * Sets the abstract of this paper submission.
	 *
	 * @precondition none
	 * @postcondition the abstract of this paper submission has been set to the
	 *                abstract entered by the user
	 */
	public void setAbstract() {
		this.paperSubmission.setAbstract(this.abstractProperty.get());
	}

	/**
	 * Sets the abstract of this paper submission to uppercase.
	 *
	 * @precondition none
	 * @postcondition the abstract of this paper submission has been set to the
	 *                abstract to uppercase
	 */
	public void uppercaseAbstract() {
		this.abstractProperty.set(this.paperSubmission.getAbstract().toUpperCase());
	}

	/**
	 * Sets the abstract of this paper submission to lowercase.
	 *
	 * @precondition none
	 * @postcondition the abstract of this paper submission has been set to the
	 *                abstract to lowercase
	 */
	public void lowercaseAbstract() {
		this.abstractProperty.set(this.paperSubmission.getAbstract().toLowerCase());
	}

	/**
	 * Sets the word count property to the word count
	 * 
	 * @precondition none
	 * @postcondition the word count property has been set to the word count
	 */
	public void checkWordCount() {
		this.wordCountProperty.set(this.paperSubmission.calculateWordCount());
	}

	/**
	 * Sets the abstract of this paper submission to remove duplicate whitespace.
	 *
	 * @precondition none
	 * @postcondition the abstract of this paper submission has been set to the
	 *                abstract to remove duplicate whitespace
	 */
	public void removeSpaces() {
		this.abstractProperty.set(this.paperSubmission.getAbstract().replaceAll("\\s+", " "));
	}

	/**
	 * Sets the abstract of this paper submission to join the words.
	 *
	 * @precondition none
	 * @postcondition the abstract of this paper submission has been set to the
	 *                abstract to join the words
	 */
	public void joinText() {
		this.abstractProperty.set(this.paperSubmission.getAbstract().replaceAll("\\s+", ""));
	}

	/**
	 * Sets the abstract of this paper to capitalize the first word in the sentence.
	 * 
	 * @precondition none
	 * @postcondition the abstract of this paper submission has been set to
	 *               capitalize the first word in each sentence
	 */
	public void capitalizeSentences() {
		this.paperSubmission.capitalizeSentences();
		this.paperSubmission.capitalizeTitle();
		this.abstractProperty.set(this.paperSubmission.getAbstract());
		this.titleProperty.set(this.paperSubmission.getTitle());
	}

	/**
	 * Sets the abstract of this paper submission to remove html.
	 *
	 * @precondition none
	 * @postcondition the abstract of this paper submission has been set to the
	 *                abstract to remove html
	 */
	public void extractText() {
		this.abstractProperty.set(this.paperSubmission.getAbstract().replaceAll("\\<|\\>", ""));
	}
}
