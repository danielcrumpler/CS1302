package edu.westga.cs1302.retail.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.Store;
import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class SalesDataFileReader.
 * 
 * Reads an .rsd (Retail Sales Data) File which is a CSV file with the following
 * format: UPC,description,revenue,sold_quantity
 * 
 * @author CS1302
 */
public class SalesDataFileReader {
	public static final String FIELD_SEPARATOR = ",";

	private File salesDataFile;

	/**
	 * Instantiates a new sales data file reader.
	 *
	 * @precondition salesDataFile != null
	 * @postcondition none
	 * 
	 * @param salesDataFile the sales data file with the store's retail sales data
	 */
	public SalesDataFileReader(File salesDataFile) {
		if (salesDataFile == null) {
			throw new IllegalArgumentException(ExceptionMessages.SALES_FILE_CANNOT_BE_NULL);
		}

		this.salesDataFile = salesDataFile;
	}

	/**
	 * Opens the associated rsd file and reads all the sales data in the file one
	 * line at a time where each line contains the sales data of one product. Parses
	 * each line and creates a product object and stores it in an ArrayList of
	 * product objects. Once the file has been completely read, the ArrayList of
	 * products is returned from the method.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return Collection of products read from the file.
	 * @throws FileNotFoundException exception to be thrown if file is not found
	 * @throws NumberFormatException exception to be thrown if parse fails.
	 */
	public ArrayList<Product> loadAllProducts() throws FileNotFoundException {
		ArrayList<Product> products = new ArrayList<Product>();
		int lineNumber = 0;
		String line = "";
		try (Scanner input = new Scanner(this.salesDataFile)) {
			while (input.hasNextLine()) {
				lineNumber += 1;
				line = input.nextLine();
				String[] fields = this.splitLine(line, SalesDataFileReader.FIELD_SEPARATOR);
				Product product = this.makeProduct(fields);
				products.add(product);
			}
		} catch (Exception e) {
			System.err.println("Error reading file, line " + lineNumber + ": " + e.getMessage() + ": " + line);
		}

		return products;
	}
	
	private Product makeProduct(String[] fields) {
		String upc = fields[1];
		String description = fields[2];
		double revenue = Double.parseDouble(fields[3]);
		int quantity = Integer.parseInt(fields[4]);
		return new Product(upc, description, revenue, quantity);
	}

	private String[] splitLine(String line, String fieldSeparator) {
		return line.split(fieldSeparator);
	}
	
	/**
	 * Opens the associated rsd file and reads all the sales data in the file one
	 * line at a time where each line contains the sales data and department of 
	 * one product. Parses each line and creates a product object and stores it 
	 * in a the Store class.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param store the store
	 * 
	 * @throws FileNotFoundException exception to be thrown if file is not found
	 * @throws NumberFormatException exception to be thrown if parse fails.
	 */
	public void loadAllProductsIntoStore(Store store) throws FileNotFoundException {
		if (store == null) {
			throw new IllegalArgumentException("store cannot be null");
		}
		int lineNumber = 0;
		String line = "";
		try (Scanner input = new Scanner(this.salesDataFile)) {
			while (input.hasNextLine()) {
				lineNumber += 1;
				line = input.nextLine();
				String[] fields = this.splitLine(line, SalesDataFileReader.FIELD_SEPARATOR);
				Product product = this.makeProduct(fields);
				if (store.findDepartment(fields[0]) == null) {
					store.addDepartment(fields[0]);
				}	
				store.addProduct(fields[0], product);
			}
		} catch (Exception e) {
			System.err.println("Error reading file, line " + lineNumber + ": " + e.getMessage() + ": " + line);
		}
	}
}
