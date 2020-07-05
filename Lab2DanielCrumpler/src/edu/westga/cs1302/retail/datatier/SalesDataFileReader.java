package edu.westga.cs1302.retail.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.westga.cs1302.retail.model.Product;
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
	 */
	public ArrayList<Product> loadAllProducts() throws FileNotFoundException {
		ArrayList<Product> store = new ArrayList<Product>();
		try (Scanner input = new Scanner(this.salesDataFile)) {
			while (input.hasNext()) {
				input.useDelimiter(",|\\r\\n");
				String upc = input.next();
				String description = input.next();
				double revenue = input.nextDouble();
				int quantitySold = input.nextInt();			
				Product product = new Product(upc, description, revenue, quantitySold);
				store.add(product);
			}

			return store;
		}
	}
}
