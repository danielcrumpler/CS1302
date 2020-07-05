package edu.westga.cs1302.si.controller;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import edu.westga.cs1302.gradeconverter.model.Student;
import edu.westga.cs1302.si.model.Book;

/**
 * The Class DemoController.
 * 
 * @author CS1302
 */
public class DemoController {

	/**
	 * Demos functionality
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void run() {
		// Used by Task 1 of the SI exercise
		 int sum = this.promptAndSumNumbersEnteredByUser();
		 System.out.println("Sum of whole numbers entered by user is: " + sum);

		// Will be used as a part of Task 2 of the SI exercise
		 ArrayList<Book> readingList = this.loadBooksFromFile("CSReadingList.dat");
		 this.printBooks(readingList);
	}

	/**
	 * Prompts the user for integer numbers one by one and sums them until the user
	 * enters the sentinel value (-999). If the user enters an value that cannot be
	 * converted to an integer, e.g., "a". The program should inform the user, but
	 * continue to prompt the user for numbers to enter until -999 is entered. -999
	 * should not be part of the sum.
	 * 
	 * Example prompt: "Enter whole number: "
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return The sum of the integers entered by the user.
	 */
	public int promptAndSumNumbersEnteredByUser() {
		int value = -999;
		int sum = 0;
		
		try (Scanner input = new Scanner(System.in)) {
			do {
				try {
					System.out.print("Enter whole number: ");
					String line = input.nextLine();
					value = Integer.parseInt(line);
					if(value != -999) {
					sum += value;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Try again.");
				}
			} while (value != -999);
		}
		return sum;
	}

	/**
	 * Loads the file specified by filename into a list of books and returns the
	 * list of books. The format of the file is a CSV file of the following format:
	 * <title>,<author>,<pages>,<price>
	 * 
	 * @precondition filename != null
	 * @postcondition none
	 * 
	 * @param filename The file to load the books from.
	 * @return The list of books loaded from the file.
	 */
	public ArrayList<Book> loadBooksFromFile(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException("filename cannot be null");
		}
		ArrayList<Book> books = new ArrayList<Book>();
		File inFile = new File(filename);
		try (Scanner input = new Scanner(inFile)) {
			while (input.hasNext()) {
				input.useDelimiter(",|\\r\\n");
				String title = input.next();
				String author = input.next();
				int pages = input.nextInt();
				double price = input.nextDouble();			
				Book book = new Book(title, author, pages, price);
				books.add(book);
			}
		} catch (IOException e) {
			System.err.println("File does not exist.");
		}
		return books;
	}

	private void printBooks(ArrayList<Book> books) {
		if (books != null) {
			for (Book currBook : books) {
				System.out.println(currBook);
			}
		}
	}

}
