package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.westga.cs1302.project3.model.StoryMakerPage;
import edu.westga.cs1302.project3.model.StoryMakerStory;
import edu.westga.cs1302.project3.resources.ExceptionMessages;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import storyreader.StoryReader;
import storyreader.util.ColorUtil;
import storyreader.util.InvalidStoryFileException;
import storyreader.util.Page;
import storyreader.util.Story;
import storyreader.util.StoryLoader;

/**
 * The Class StoryMakerViewModel.
 * 
 * @author Daniel Crumpler
 */
public class StoryMakerViewModel {

	private static final String COMMA = ",";
	private int pageNumber;
	private final StringProperty authorProperty;
	private final StringProperty titleProperty;
	private final StringProperty textProperty;
	private final StringProperty synonymProperty;
	private final ListProperty<Page> pagesProperty;
	private final ObjectProperty<Image> imageProperty;
	private final StringProperty fontSizeProperty;
	private Story story;
	private Map<String, String[]> synonyms;
	private final ObjectProperty<String> comboProperty;
	private final ObjectProperty<Color> backgroundColorProperty;
	private final ObjectProperty<Color> textColorProperty;

	/**
	 * Instantiates a new StoryMakerViewModel.
	 */
	public StoryMakerViewModel() {
		this.synonyms = new HashMap<String, String[]>();
		this.authorProperty = new SimpleStringProperty();
		this.titleProperty = new SimpleStringProperty();
		this.textProperty = new SimpleStringProperty();
		this.synonymProperty = new SimpleStringProperty();
		this.imageProperty = new SimpleObjectProperty<Image>();
		this.story = new StoryMakerStory(this.titleProperty.toString(), this.authorProperty.toString());
		this.pagesProperty = new SimpleListProperty<Page>(FXCollections.observableArrayList(this.story));
		this.comboProperty = new SimpleObjectProperty<String>();
		this.fontSizeProperty = new SimpleStringProperty();
		this.backgroundColorProperty = new SimpleObjectProperty<Color>();
		this.textColorProperty = new SimpleObjectProperty<Color>();
	}

	/**
	 * Returns the story;
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the story
	 */
	public Story getStory() {
		return this.story;
	}

	/**
	 * Gets the font size property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the font size property
	 */
	public StringProperty getFontSizeProperty() {
		return this.fontSizeProperty;
	}

	/**
	 * Gets the background color property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the background color property
	 */
	public ObjectProperty<Color> getBackgroundColorProperty() {
		return this.backgroundColorProperty;
	}

	/**
	 * Gets the text color property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the text color property
	 */
	public ObjectProperty<Color> getTextColorProperty() {
		return this.textColorProperty;
	}

	/**
	 * Gets the author property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the author property
	 */
	public StringProperty getAuthorProperty() {
		return this.authorProperty;
	}

	/**
	 * Gets the synonym property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the author property
	 */
	public StringProperty getSynonymProperty() {
		return this.synonymProperty;
	}

	/**
	 * Gets the title property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the title property
	 */
	public StringProperty getTitleProperty() {
		return this.titleProperty;
	}

	/**
	 * Gets the text property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the text property
	 */
	public StringProperty getTextProperty() {
		return this.textProperty;
	}

	/**
	 * Gets the pages property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the pages property
	 */
	public ListProperty<Page> pagesProperty() {
		return this.pagesProperty;
	}

	/**
	 * Gets the image property
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the image property
	 */
	public ObjectProperty<Image> imageProperty() {
		return this.imageProperty;
	}

	/**
	 * Adds the default page
	 * 
	 * @return true, if successful
	 */
	public boolean addDefaultPage() {
		String text = "New Page";
		String imagePath = "AppData/upload.jpg";
		StoryMakerPage page = new StoryMakerPage(imagePath, text);

		if (this.story.add(page)) {
			this.pagesProperty.add(page);
			return true;
		}
		return false;
	}

	/**
	 * Removes the page
	 * 
	 * @return true, if successful
	 * 
	 */
	public boolean deletePage() {
		if (this.story.contains(this.getPage(this.pageNumber))) {
			Page page = this.getPage(this.pageNumber);
			this.story.remove(page);
			this.pagesProperty.remove(page);
			return true;
		}
		return false;
	}

	/**
	 * Gets the page
	 * 
	 * @param pageNumber the number of the page
	 * 
	 * @return page if successful; null, otherwise
	 */
	public Page getPage(int pageNumber) {
		return this.story.getPage(pageNumber);
	}

	/**
	 * Gets the Image of the page
	 * 
	 */
	public void getImage() {
		String path2img = this.story.getPage(this.pageNumber).getImagePath();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(path2img);
			this.imageProperty.set(new Image(inputStream));
		} catch (FileNotFoundException error) {
			error.printStackTrace();
		}
	}

	/**
	 * Gets the combo property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the combo property
	 */
	public ObjectProperty<String> getComboProperty() {
		return this.comboProperty;
	}

	/**
	 * Sets the Image in the ImageView
	 * 
	 * @param selectedFile the file of the Image
	 */
	public void setImage(File selectedFile) {
		String path2img = selectedFile.getPath();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(path2img);
			this.imageProperty.set(new Image(inputStream));
			this.story.getPage(this.pageNumber).setImagePath(path2img);
		} catch (FileNotFoundException error) {
			error.printStackTrace();
		}
	}

	/**
	 * Saves the story into a specified File object
	 * 
	 * @precondition none
	 * @postcondition A specified File is created
	 * 
	 * @param selectedFile the file to be saved to
	 */
	public void saveStory(File selectedFile) {
		try (PrintWriter writer = new PrintWriter(selectedFile)) {
			String output = this.story.getDisplaySetting().getFont() + COMMA
					+ this.story.getDisplaySetting().getFontSize() + COMMA
					+ ColorUtil.toHexWebString(this.story.getDisplaySetting().getTextColor()) + COMMA
					+ ColorUtil.toHexWebString(this.story.getDisplaySetting().getBackgroundColor())
					+ System.lineSeparator() + this.story.getTitle() + System.lineSeparator() + this.story.getAuthor()
					+ System.lineSeparator();

			for (Page page : this.story) {
				output = output + "PAGE" + System.lineSeparator() + page.getImagePath() + System.lineSeparator()
						+ page.getText() + System.lineSeparator();

			}
			writer.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loads the story file using the specified File object.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param selectedFile The file to load the story from
	 */
	public void loadStory(File selectedFile) {
		try {
			this.story = StoryLoader.loadFromFile(selectedFile);
		} catch (InvalidStoryFileException e) {
			e.printStackTrace();
		}
		this.setupStoryFileOnLoad();
	}

	/**
	 * Displays the story
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void displayStoryReader() {
		Story myStory = this.story;
		StoryReader reader = new StoryReader(myStory);
		reader.run();
	}

	private void setupStoryFileOnLoad() {
		this.titleProperty.set(this.story.getTitle());
		this.setDisplaySettings();
		if (this.story.getAuthor().isEmpty()) {
			this.authorProperty.set("");
		} else {
			this.authorProperty.set(this.story.getAuthor());
		}
		if (this.story.isEmpty()) {
			this.textProperty.set("");
			this.imageProperty.set(null);
		} else {
			this.pagesProperty.clear();
			Story story = new StoryMakerStory(this.titleProperty.get(), this.authorProperty.get());
			story.getDisplaySetting().setBackgroundColor(this.story.getDisplaySetting().getBackgroundColor());
			story.getDisplaySetting().setFont(this.story.getDisplaySetting().getFont());
			story.getDisplaySetting().setFontSize(this.story.getDisplaySetting().getFontSize());
			story.getDisplaySetting().setTextColor(this.story.getDisplaySetting().getTextColor());
			for (Page page : this.story) {
				StoryMakerPage makerPage = new StoryMakerPage(page.getImagePath(), page.getText());
				story.add(makerPage);
			}
			this.story = story;
			for (Page page : this.story) {
				this.pagesProperty.add(page);
			}
			this.pageNumber = 1;
		}
	}

	private void setDisplaySettings() {
		this.backgroundColorProperty.set(this.story.getDisplaySetting().getBackgroundColor());
		this.textColorProperty.set(this.story.getDisplaySetting().getTextColor());
		if (this.story.getDisplaySetting().getFontSize() < 1) {
			this.fontSizeProperty.setValue("1");
		} else {
			String string = "" + this.story.getDisplaySetting().getFontSize();
			this.fontSizeProperty.set(string);
		}
	}

	/**
	 * Gets the page number
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the page number
	 */
	public int getPageNumber() {
		return this.pageNumber;
	}

	/**
	 * Sets the page number
	 * 
	 * @precondition pageNumber > 0
	 * @postcondition getPageNumber() == pageNumber
	 * 
	 * @param pageNumber the page number
	 */
	public void setPageNumber(int pageNumber) {
		if (pageNumber <= 0) {
			throw new IllegalArgumentException(ExceptionMessages.PAGE_NUMBER_CANNOT_BE_LESS_THAN_ONE);
		}
		this.pageNumber = pageNumber;
	}

	/**
	 * Gets the word count for the story
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the word count as a string for the menu
	 */
	public String wordCount() {
		int wordCount = 0;
		for (Page page : this.story) {
			String[] array = page.getText().split(" ");
			wordCount += array.length;
		}
		return "Total number of words (all pages): " + wordCount;
	}

	/**
	 * Loads the synonyms from the file at the start of the program
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void loadSynonyms() {
		int lineNumber = 0;
		String line = "";
		try (Scanner input = new Scanner(new File("AppData/synonyms.csv"))) {
			while (input.hasNextLine()) {
				lineNumber += 1;
				line = input.nextLine();
				String[] fields = this.splitLine(line, ",");
				this.synonyms.put(fields[0].toLowerCase(), fields);
			}
		} catch (Exception e) {
			System.err.println("Error reading file, line " + lineNumber + ": " + e.getMessage() + ": " + line);
		}
	}

	private String[] splitLine(String line, String fieldSeparator) {
		return line.split(fieldSeparator);
	}

	/**
	 * Returns the list of synonyms for a word
	 * 
	 * @precondition none
	 * @postcondition a formatted string of synonyms for the word
	 * 
	 * @param word the word to find synonyms
	 */
	public void getASynonym(String word) {
		if (this.synonyms.containsKey(word.toLowerCase())) {
			String[] array = this.synonyms.get(word.toLowerCase());
			String result = "Synonyms of '" + word + "':" + System.lineSeparator();
			for (int i = 1; i < array.length; i++) {
				result += array[i].toLowerCase() + System.lineSeparator();
			}
			this.synonymProperty.set(result);
		}
	}

	/**
	 * Moves the Page to where the user selects the page to be moved to
	 * 
	 * @param toMove where the page needs to be moved to
	 */
	public void movePage(int toMove) {
		if (this.story.size() != 0) {
			ArrayList<Page> list = new ArrayList<Page>();
			Page pageToMove = this.story.getPage(this.pageNumber);
			this.story.remove(pageToMove);
			for (Page page : this.story) {
				list.add(page);
			}
			this.story.clear();
			for (int i = 0; i < list.size(); i++) {
				if (i == toMove - 1) {
					this.story.add(pageToMove);
					this.story.add(list.get(i));
				} else {
					this.story.add(list.get(i));
				}
			}
			this.setupStoryFileOnLoad();
		}
	}
}
