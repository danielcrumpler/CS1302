package edu.westga.cs1302.retail.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class SalesDataFileWriter.
 * 
 * @author CS1302
 */
public class SalesDataFileWriter {

	public static final String FIELD_SEPARATOR = ",";
	
	private File salesDataFile;

	/**
	 * Instantiates a new sales data file writer.
	 *
	 * @precondition salesDataFile != null
	 * @postcondition none
	 * 
	 * @param salesDataFile the sales data file with the retail sales data
	 */
	public SalesDataFileWriter(File salesDataFile) {
		if (salesDataFile == null) {
			throw new IllegalArgumentException(ExceptionMessages.SALES_FILE_CANNOT_BE_NULL);
		}

		this.salesDataFile = salesDataFile;
	}

	/**
	 * Writes all the products of the sales data to the specified sales data file.
	 * Each product will be on a separate line and of the following format:
	 * UPC,description,revenue,sold_quantity
	 * 
	 * @precondition products != null
	 * @postcondition none
	 * 
	 * @param products the collection of products to write to file.
	 * @throws FileNotFoundException exception to be thrown if file not found
	 */
	public void write(ArrayList<Product> products) throws FileNotFoundException {
		if (products == null) {
			throw new IllegalArgumentException(ExceptionMessages.PRODUCTS_CANNOT_BE_NULL);
		}
		PrintWriter writer = new PrintWriter(this.salesDataFile);
		for (Product currProduct : products) {
			String upc = currProduct.getUpc();
			String description = currProduct.getDescription();
			double revenue = currProduct.getRevenue();
			int quantitySold = currProduct.getQuantitySold();
		    writer.println(upc + FIELD_SEPARATOR + description + FIELD_SEPARATOR + revenue + FIELD_SEPARATOR + quantitySold);
		}
		writer.close();
	}
}
