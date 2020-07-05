package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.westga.cs1302.project3.resources.ExceptionMessages;
import storyreader.util.DisplaySetting;
import storyreader.util.Page;
import storyreader.util.Story;

/**
 * The Class StoryMakerStory that stores the imagePath and text of a Page.
 * 
 * @author Daniel Crumpler
 */
public class StoryMakerStory implements Story {

	private ArrayList<Page> pages;
	private DisplaySetting setting;
	private String title;
	private String author;

	/**
	 * Instantiates a new StoryMakerStory
	 * 
	 * @precondition title != null and title is not empty AND author != null and
	 *               author is not empty
	 * @postcondition getTitle() == title && getAuthor == author
	 * 
	 * @param title  the title
	 * @param author the author of the story
	 */
	public StoryMakerStory(String title, String author) {
		if (title == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_TITLE);
		}
		if (title.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_TITLE);
		}
		if (author == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_AUTHOR);
		}
		if (author.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_AUTHOR);
		}

		this.title = title;
		this.author = author;
		this.pages = new ArrayList<Page>();
		this.setting = new DisplaySetting();
	}

	@Override
	public int size() {
		return this.pages.size();
	}

	@Override
	public boolean isEmpty() {
		return this.pages.isEmpty();
	}

	@Override
	public boolean contains(Object page) {
		if (page == null) {
			throw new NullPointerException(ExceptionMessages.NULL_PAGE);
		}
		return this.pages.contains(page);
	}

	@Override
	public Iterator<Page> iterator() {
		return this.pages.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.pages.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		return this.pages.toArray(array);
	}

	@Override
	public boolean add(Page page) {
		if (page == null) {
			throw new NullPointerException(ExceptionMessages.NULL_PAGE);
		}
		return this.pages.add(page);
	}

	@Override
	public boolean remove(Object page) {
		if (page == null) {
			throw new NullPointerException(ExceptionMessages.NULL_PAGE);
		}
		return this.pages.remove(page);
	}

	@Override
	public boolean containsAll(Collection<?> pages) {
		if (pages == null) {
			throw new NullPointerException(ExceptionMessages.NULL_PAGE);
		}
		if (pages.contains(null)) {
			throw new NullPointerException(ExceptionMessages.PAGES_CANNOT_CONTAIN_NULL);
		}
		return this.pages.containsAll(pages);
	}

	@Override
	public boolean addAll(Collection<? extends Page> pages) {
		if (pages == null) {
			throw new NullPointerException(ExceptionMessages.NULL_PAGE);
		}
		if (pages.contains(null)) {
			throw new NullPointerException(ExceptionMessages.PAGES_CANNOT_CONTAIN_NULL);
		}
		return this.pages.addAll(pages);
	}

	@Override
	public boolean removeAll(Collection<?> pages) {
		if (pages == null) {
			throw new NullPointerException(ExceptionMessages.NULL_PAGE);
		}
		if (pages.contains(null)) {
			throw new NullPointerException(ExceptionMessages.PAGES_CANNOT_CONTAIN_NULL);
		}
		return this.pages.removeAll(pages);
	}

	@Override
	public boolean retainAll(Collection<?> pages) {
		if (pages == null) {
			throw new NullPointerException(ExceptionMessages.NULL_PAGE);
		}
		if (pages.contains(null)) {
			throw new NullPointerException(ExceptionMessages.PAGES_CANNOT_CONTAIN_NULL);
		}
		return this.pages.retainAll(pages);
	}

	@Override
	public void clear() {
		this.pages.clear();
	}

	@Override
	public String getAuthor() {
		return this.author;
	}

	@Override
	public DisplaySetting getDisplaySetting() {
		return this.setting;
	}

	@Override
	public Page getPage(int pageNumber) {
		return this.pages.get(pageNumber - 1);
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public void setAuthor(String author) {
		if (author == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_AUTHOR);
		}
		if (author.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_AUTHOR);
		}

		this.author = author;
	}

	@Override
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_TITLE);
		}
		if (title.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_TITLE);
		}

		this.title = title;
	}
}
