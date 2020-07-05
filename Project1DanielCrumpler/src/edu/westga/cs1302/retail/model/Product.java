package edu.westga.cs1302.retail.model;

import java.io.File;
import java.util.Scanner;

import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class Product.
 * 
 * @author CS1302
 */
public class Product {

	private String upc;
	private String description;
	private double revenue;
	private int quantitySold;
	private File acronymFile = new File("acronyms.txt");

	/**
	 * Instantiates a new product.
	 * 
	 * @precondition upc != null && !upc.isEmpty() && upc contains digits only &&
	 *               description != null && !description.isEmpty() && revenue >= 0 &&
	 *               quantitySold > 0
	 * @postcondition getUpc().equals(upc) && getDescription().equals(description) &&
	 *                getRevenue() == revenue && getQuantitySold() == quantitySold
	 * 
	 * @param upc          the UPC code
	 * @param description  the description
	 * @param revenue      the revenue
	 * @param quantitySold the quantity sold of this product
	 */
	public Product(String upc, String description, double revenue, int quantitySold) {
		if (upc == null) {
			throw new IllegalArgumentException(ExceptionMessages.UPC_CANNOT_BE_NULL);
		}
		if (upc.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.UPC_CANNOT_BE_EMPTY);
		}
		if (!upc.matches("\\d+")) {
			throw new IllegalArgumentException(ExceptionMessages.UPC_CANNOT_HAVE_NONDIGITS);
		}
		if (description == null) {
			throw new IllegalArgumentException(ExceptionMessages.DESCRIPTION_CANNOT_BE_NULL);
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.DESCRIPTION_CANNOT_BE_EMPTY);
		}
		if (revenue < 0) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_REVENUE);
		}
		if (quantitySold <= 0) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_QUANTITY);
		}

		this.upc = upc;
		String descriptionFinal = this.descriptionFix(description.strip().toUpperCase());
		this.description = descriptionFinal;
		this.revenue = revenue;
		this.quantitySold = quantitySold;
	}

	/**
	 * Gets the upc.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the upc
	 */
	public String getUpc() {
		return this.upc;
	}

	/**
	 * Gets the description.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the revenue.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the revenue
	 */
	public double getRevenue() {
		return this.revenue;
	}

	/**
	 * Gets the quantity sold.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the quantity sold
	 */
	public int getQuantitySold() {
		return this.quantitySold;
	}

	/**
	 * Sets the description.
	 *
	 * @precondition description != null && !description.isEmpty()
	 * @postcondition getDescription().equals(description)
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException(ExceptionMessages.DESCRIPTION_CANNOT_BE_NULL);
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.DESCRIPTION_CANNOT_BE_EMPTY);
		}
		description.strip();
		description.toUpperCase();
		String descriptionFinal = this.descriptionFix(description);
		this.description = descriptionFinal;
	}
	
	private String descriptionFix(String description) {
		String[] descriptions = description.split(" ");
		try (Scanner input = new Scanner(this.acronymFile)) {
			String line = "";
			String[] file;
			while (input.hasNextLine()) {
				line = input.nextLine();
				file = line.split(",");
				for (int i = 0; i < descriptions.length; i++) {
					if (descriptions[i].matches(file[0])) {
						descriptions[i] = file[1];
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error reading file");
		}
		String descriptionFinal = "";
		for (int i = 0; i < descriptions.length; i++) {
			descriptionFinal += descriptions[i] + " ";
		}
		return descriptionFinal.toUpperCase();
	}

	/**
	 * Sets the revenue.
	 *
	 * @precondition revenue >= 0
	 * @postcondition getRevenue() == revenue
	 * 
	 * @param revenue the revenue to set
	 */
	public void setRevenue(double revenue) {
		if (revenue < 0) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_REVENUE);
		}
		this.revenue = revenue;
	}

	/**
	 * Sets the quantity sold.
	 *
	 * @precondition quantitySold > 0
	 * @postcondition getQuantitySold() == quantitySold
	 * 
	 * @param quantitySold the quantity sold to set
	 */
	public void setQuantitySold(int quantitySold) {
		if (quantitySold <= 0) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_QUANTITY);
		}
		this.quantitySold = quantitySold;
	}

	@Override
	public String toString() {
		return this.upc + ": " + this.description;
	}
}
