package edu.westga.cs1302.si.model;

/**
 * The Class Book.
 * 
 * @author CS1302
 */
public class Book {

	private String title;
	private String author;
	private int pages;
	private double price;

	/**
	 * Instantiates a new book with given data.
	 * 
	 * @precondition title != null && title not empty; author != null and author not
	 *               empty; pages > 0; price >= 0.0
	 * @postcondition getTitle() == title; getAuthor() == author; getPages() ==
	 *                pages; getPrice() == price
	 *
	 * @param title
	 *            the title
	 * @param author
	 *            the author
	 * @param pages
	 *            the pages
	 * @param price
	 *            the price
	 */
	public Book(String title, String author, int pages, double price) {
		
		if (title == null) {
			throw new IllegalArgumentException("title cannot be null.");
		}
		
		if (title.isEmpty()) {
			throw new IllegalArgumentException("title cannot be empty.");
		}
		
		if (author == null) {
			throw new IllegalArgumentException("author cannot be null.");
		}
		
		if (author.isEmpty()) {
			throw new IllegalArgumentException("author cannot be empty.");
		}
		
		if (pages <= 0) {
			throw new IllegalArgumentException("pages must be >= 0.");
		}

		if (price < 0) {
			throw new IllegalArgumentException("price must be > 0.");
		}
		
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.price = price;
	}

	/**
	 * Gets the pages.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the pages
	 */
	public int getPages() {
		return this.pages;
	}

	/**
	 * Sets the pages.
	 * 
	 * @precondition pages > 0
	 * @postcondition getPages() == pages
	 *
	 * @param pages
	 *            the new pages
	 */
	public void setPages(int pages) {
		if (pages <= 0) {
			throw new IllegalArgumentException("pages must be >= 0.");
		}
		
		this.pages = pages;
	}

	/**
	 * Gets the price.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Sets the price.
	 *
	 * @precondition price >= 0
	 * @postcondition getPrice() == price
	 * 
	 * @param price
	 *            the new price
	 */
	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("price must be > 0.");
		}
		
		this.price = price;
	}

	/**
	 * Gets the title.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the author.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}
	
	@Override
	public String toString() {
		return this.title + " by " + this.author;
	}

}
