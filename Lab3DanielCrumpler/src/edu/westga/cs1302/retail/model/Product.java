package edu.westga.cs1302.retail.model;

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

	/**
	 * Instantiates a new product.
	 * 
	 * @precondition upc != null && !upc.isEmpty(); description != null &&
	 *               !description.isEmpty() && revenue >= 0 && quantitySold > 0
	 * @postcondition getUpc() == upc && getDescription().equals(description) &&
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
		if (!upc.matches("[0-9]+")) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_UPC);
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
		this.description = description;
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
		this.description = description;
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
	 * @precondition quantitySold >= 0
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
