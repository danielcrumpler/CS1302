package edu.westga.cs1302.retail.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.westga.cs1302.retail.model.Department;
import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Store;
import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class SalesDataFileWriter.
 * 
 * @author CS1302
 */
public class SalesDataFileWriter {

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
	 * @precondition salesData != null
	 * @postcondition none
	 * 
	 * @param salesData the sales data to write to file.
	 * @throws FileNotFoundException exception to be thrown if file not found
	 */
	public void write(SalesData salesData) throws FileNotFoundException {
		if (salesData == null) {
			throw new IllegalArgumentException(ExceptionMessages.SALESDATA_CANNOT_BE_NULL);
		}
		ArrayList<Product> products = salesData.getProducts();

		try (PrintWriter writer = new PrintWriter(this.salesDataFile)) {
			for (Product currProduct : products) {
				String output = currProduct.getUpc() + SalesDataFileReader.FIELD_SEPARATOR;
				output += currProduct.getDescription() + SalesDataFileReader.FIELD_SEPARATOR;
				output += currProduct.getRevenue() + SalesDataFileReader.FIELD_SEPARATOR;
				output += currProduct.getQuantitySold();

				writer.println(output);
			}
		}

	}
	
	/**
	 * Writes all the products of the store to the specified sales data file.
	 * Each product will be on a separate line and of the following format:
	 * department,UPC,description,revenue,sold_quantity
	 * 
	 * @precondition salesData != null
	 * @postcondition none
	 * 
	 * @param store the store to write to file.
	 * @throws FileNotFoundException exception to be thrown if file not found
	 */
	public void writeStore(Store store) throws FileNotFoundException {
		if (store == null) {
			throw new IllegalArgumentException("store cannot be null");
		}
		
		try (PrintWriter writer = new PrintWriter(this.salesDataFile)) {
			for (Department currDepartment : store.getDepartments()) {
				String output = currDepartment.getName() + SalesDataFileReader.FIELD_SEPARATOR;
				for (Product currProduct : currDepartment.getSalesData().getProducts()) {
					output += currProduct.getUpc() + SalesDataFileReader.FIELD_SEPARATOR;
					output += currProduct.getDescription() + SalesDataFileReader.FIELD_SEPARATOR;
					output += currProduct.getRevenue() + SalesDataFileReader.FIELD_SEPARATOR;
					output += currProduct.getQuantitySold();
					writer.println(output);
				}	
			}
		}

	}
}
