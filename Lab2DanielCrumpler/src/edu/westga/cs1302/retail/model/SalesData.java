package edu.westga.cs1302.retail.model;

import java.util.ArrayList;
import java.util.Iterator;

import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class SalesData.
 * 
 * @author CS1302
 */
public class SalesData {

	private String storeName;
	private ArrayList<Product> products;
	public static final String FIELD_SEPARATOR = ",";

	/**
	 * Instantiates a new sales data object.
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public SalesData() {
		this.storeName = null;
		this.products = new ArrayList<Product>();
	}

	/**
	 * Instantiates a new sales data object.
	 * 
	 * @precondition storeName != null && !storeName.isEmpty()
	 * @postcondition getStoreName().equals(storeName) && size() == 0
	 *
	 * @param storeName the store name
	 */
	public SalesData(String storeName) {
		if (storeName == null) {
			throw new IllegalArgumentException(ExceptionMessages.STORE_NAME_CANNOT_BE_NULL);
		}

		if (storeName.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.STORE_NAME_CANNOT_BE_EMPTY);
		}

		this.storeName = storeName;
		this.products = new ArrayList<Product>();
	}

	/**
	 * Gets the store name.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the storeName
	 */
	public String getStoreName() {
		return this.storeName;
	}
	
	/**
	 * Sets the store name.
	 * 
	 * @precondition storeName != null && !storeName.isEmpty()
	 * @postcondition getStoreName().equals(storeName)
	 *
	 * @param storeName the store name to set
	 */
	public void setStoreName(String storeName) {
		if (storeName == null) {
			throw new IllegalArgumentException(ExceptionMessages.STORE_NAME_CANNOT_BE_NULL);
		}

		if (storeName.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.STORE_NAME_CANNOT_BE_EMPTY);
		}

		this.storeName = storeName;
	}
	
	/**
	 * Number products included in this sales data.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of products included in this sales data.
	 */
	public int size() {
		return this.products.size();
	}

	/**
	 * Adds the product to this sales data.
	 * 
	 * @precondition product != null
	 * @postcondition size() == size()@prev + 1
	 *
	 * @param product the product
	 * @return true, if add successful
	 */
	public boolean add(Product product) {
		if (product == null) {
			throw new IllegalArgumentException(ExceptionMessages.PRODUCT_CANNOT_BE_NULL);
		}

		return this.products.add(product);
	}
	
	/**
	 * Adds the product to this sales data.
	 * 
	 * @precondition none
	 * @postcondition size() == size()@prev + products.size()
	 *
	 * @param products the product
	 * @return true, if add successful
	 */
	public boolean addArrayList(ArrayList<Product> products) {
		for (Product currProduct : products) {
			this.products.add(currProduct);
		}
		return false;
	}

	/**
	 * Finds the product with the specified UPC.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param upc the UPC of the product to be returned
	 * 
	 * @return the product with the specified UPC, returns null if no product with
	 *         the UPC
	 */
	public Product findProduct(String upc) {
		for (Product currProduct : this.products) {
			if (currProduct.getUpc().equals(upc)) {
				return currProduct;
			}
		}
		return null;
	}

	/**
	 * Gets the total revenue.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the total revenue
	 */
	public double getTotalRevenue() {
		double revenue = 0;

		for (Product currProduct : this.products) {
			revenue += currProduct.getRevenue();
		}

		return revenue;
	}
	
	/**
	 * Gets the products.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return this.products;
	}

	/**
	 * Find the UPC of the product with the highest revenue.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the UPC of the product with highest revenue or null if no products
	 */
	public String findUpcWithHighestRevenue() {
		double highestRevenue = -1;
		String upc = null;

		for (Product currProduct : this.products) {
			if (currProduct.getRevenue() > highestRevenue) {
				highestRevenue = currProduct.getRevenue();
				upc = currProduct.getUpc();
			}
		}

		return upc;
	}

	/**
	 * Find the UPC of the product with the lowest revenue.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the UPC of the product with lowest revenue or null if no products
	 */
	public String findUpcWithLowestRevenue() {
		double lowestRevenue = 0;
		String upc = null;
		if (!this.products.isEmpty()) {
			lowestRevenue = this.products.get(0).getRevenue();
			upc = this.products.get(0).getUpc();
		}

		for (Product currProduct : this.products) {
			if (currProduct.getRevenue() < lowestRevenue) {
				lowestRevenue = currProduct.getRevenue();
				upc = currProduct.getUpc();
			}
		}

		return upc;
	}

	/**
	 * Find the highest quantity of all the product whose unit price is within the
	 * specified bounds inclusively.
	 * 
	 * @precondition minUnitPrice <= maxUnitPrice
	 * @postcondition none
	 *
	 * @param minUnitPrice minimum unit price
	 * @param maxUnitPrice maximum unit price
	 * 
	 * @return the highest quantity sold of the product within the specified unit
	 *         prices inclusively or zero if no products
	 */
	public int findHighestQuantityBetween(double minUnitPrice, double maxUnitPrice) {
		if (minUnitPrice > maxUnitPrice) {
			throw new IllegalArgumentException(ExceptionMessages.MINPRICE_GREATER_THAN_MAXPRICE);
		}
		
		int highestQuantity = 0;

		for (Product currProduct : this.products) {
			double currPrice = currProduct.getRevenue() / currProduct.getQuantitySold();
			if (currPrice >= minUnitPrice && currPrice <= maxUnitPrice) {
				if (currProduct.getQuantitySold() > highestQuantity) {
					highestQuantity = currProduct.getQuantitySold();
				}
			}
		}

		return highestQuantity;
	}

	/**
	* Deletes the specified product from the sales data.
	*
	* @precondition none
	* @postcondition if found, size() == size()@prev – 1
	*
	* @param product the product to delete from the sales data.
	*
	* @return true if the product was found and deleted from the
	* sales data, false otherwise
	*/
	public boolean deleteProduct(Product product) {
		Iterator<Product> iter = this.products.iterator();

		while (iter.hasNext()) {
		    Product product1 = iter.next();
		    if (product1.equals(product)) {
		        iter.remove();
		        return true;
		    } 
		}
		return false;
	}
	
	/**
	 * Find the UPC of the product with the highest revenue.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the UPC of the product with highest revenue or null if no products
	 */
	public String findProductWithHighestRevenue() {
		double highestRevenue = -1;
		String description = null;
		String revenue = null;
		String quantitySold = null;

		for (Product currProduct : this.products) {
			if (currProduct.getRevenue() > highestRevenue) {
				highestRevenue = currProduct.getRevenue();
				description = currProduct.getDescription();
				revenue = String.valueOf(currProduct.getRevenue());
				quantitySold = String.valueOf(currProduct.getQuantitySold());	
			}
		}

		return description + FIELD_SEPARATOR + revenue + FIELD_SEPARATOR + quantitySold;
	}

	/**
	 * Find the UPC of the product with the lowest revenue.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the UPC of the product with lowest revenue or null if no products
	 */
	public String findProductWithLowestRevenue() {
		double lowestRevenue = 0;
		String description = null;
		String revenue = null;
		String quantitySold = null;
		if (!this.products.isEmpty()) {
			lowestRevenue = this.products.get(0).getRevenue();
			description = this.products.get(0).getDescription();
			revenue = String.valueOf(this.products.get(0).getRevenue());
			quantitySold = String.valueOf(this.products.get(0).getQuantitySold());
		}

		for (Product currProduct : this.products) {
			if (currProduct.getRevenue() < lowestRevenue) {
				lowestRevenue = currProduct.getRevenue();
				description = currProduct.getDescription();
				revenue = String.valueOf(currProduct.getRevenue());
				quantitySold = String.valueOf(currProduct.getQuantitySold());
			}
		}

		return description + FIELD_SEPARATOR + revenue + FIELD_SEPARATOR + quantitySold;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Store:" + this.storeName + "#products:" + this.size();
	}

}
