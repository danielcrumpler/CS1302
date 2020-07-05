package edu.westga.cs1302.retail.model;


/**
 * Represents a product
 * 
 * @author csuser
 *
 */
public class Product {
	public static final int MIN_UNITS_SOLD = 0;
	public static final double MIN_REVENUE = 0.0;
	public static final int MIN_UPC = 0;
	private int upc;
	private String description;
	private double revenue;
	private int quantitySold;
	
	/**
	 * Default Constructor for Product
	 * 
	 * @precondition upc > MIN_UPC && description != null && !description.isEmpty() && 
	 * 				 revenue >= 0 && quantitySold >= 0
	 * @postconditon getUpc() == upc && getDescription().equals(description)
	 * 				 getRevenue() == revenue && getQuantitySold() == quantitySold
	 * @param upc upc
	 * @param description description
	 * @param revenue revenue
	 * @param quantitySold quantitySold
	 */
	public Product(int upc, String description, double revenue, int quantitySold) {
		if (description == null) {
			throw new IllegalArgumentException("Description can not be null.");
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException("Description can not be empty.");
		}
		if (upc <= 0) {
			throw new IllegalArgumentException("UPC can not be less then zero.");
		}
		if (revenue < 0) {
			throw new IllegalArgumentException("Revenue cannot be less then zero.");
		}
		if (quantitySold < 0) {
			throw new IllegalArgumentException("Quantity Sold cannot be less then zero.");
		}
		this.upc = upc;
		this.description = description;
		this.revenue = revenue;
		this.quantitySold = quantitySold;
	}

	/**
	 * Gets upc of product
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the upc
	 */
	public int getUpc() {
		return this.upc;
	}

	/**
	 * Sets the upc of a product
	 * 
	 * @precondition int > 0
	 * @postcondition getUpc() == upc
	 * @param upc the upc to set
	 */
	public void setUpc(int upc) {
		if (upc <= 0) {
			throw new IllegalArgumentException("UPC cannot be less then zero.");
		}
		this.upc = upc;
	}

	/**
	 * Gets the description of a product
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of a product
	 * @precondition String != null && !description.isEmpty()
	 * @postcondition getDescription().equals(description)
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("Description cannot be null.");
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException("Description cannot be empty.");
		}
		this.description = description;
	}

	/**
	 * Gets the revenue of a product
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the revenue
	 */
	public double getRevenue() {
		return this.revenue;
	}

	/**
	 * Sets the revenue of a product
	 * @precondition double >= 0
	 * @postcondition getRevenue() == revenue
	 * @param revenue the revenue to set
	 */
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	/**
	 * Gets the quantity sold of a product
	 * @precondition none
	 * @postcondition none
	 * @return the quantitySold
	 */
	public int getQuantitySold() {
		return this.quantitySold;
	}

	/**
	 * Sets the quantity sold of a product
	 * 
	 * @precondition int >= 0
	 * @postcondition getQuantitySold() == quantitySold
	 * @param quantitySold the quantitySold to set
	 */
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	
	/**
	 * Returns the Product formatted
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return upc && description
	 */
	public String toString() {
		return this.upc + this.description;	
	}
}
