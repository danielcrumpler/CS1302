package edu.westga.cs1302.retail.model;

import java.util.ArrayList;

import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class SalesData.
 * 
 * @author CS1302
 */
public class SalesData {
	private String storeName;
	private ArrayList<Product> products;

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
	 * @postcondition getProducts().contains(product) && size() == size()@prev + 1
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
	 * Adds all the products to this sales data.
	 * 
	 * @precondition products != null
	 * @postcondition size() == size()@prev + products.size()
	 *
	 * @param products the products to be added to this sales data.
	 * 
	 * @return true, if add successful
	 */
	public boolean addAll(ArrayList<Product> products) {
		if (products == null) {
			throw new IllegalArgumentException(ExceptionMessages.PRODUCTS_CANNOT_BE_NULL);
		}

		return this.products.addAll(products);
	}

	/**
	 * Deletes the specified product from the sales data.
	 * 
	 * @precondition none
	 * @postcondition !getProducts().contains(product)
	 * 
	 * @param product the product to delete from the sales data.
	 * 
	 * @return true if the product was found and deleted from the sales data, false
	 *         otherwise
	 */
	public boolean remove(Product product) {
		return this.products.remove(product);
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
	 * Finds the product with the specified UPC.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param upc the upc of the product to be returned
	 * 
	 * @return the product with the specified UPC, returns null if no product with
	 *         the UPC
	 */
	public Product findProduct(String upc) {
		for (Product currProduct : this.products) {
			if (currProduct.getUpc().contentEquals(upc)) {
				return currProduct;
			}
		}
		return null;
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
	 * @param minUnitPrice minimum allowed amount
	 * @param maxUnitPrice maximum allowed amount
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
	 * Creates a array of products revenue in segments
	 * 
	 * @precondition segmentRange <= 0.0
	 * @postcondition none
	 * 
	 * @param segmentRange the range of the segments
	 * @return an array of integers of products in segments
	 */
	public int[] segmentRangeForProductRevenue(double segmentRange) {
		if (segmentRange <= 0.0) {
			throw new IllegalArgumentException("segmentRange must be > 0.");
		}
		double highestRevenue = -1;
		for (Product currProduct : this.products) {
			if (currProduct.getRevenue() > highestRevenue) {
				highestRevenue = currProduct.getRevenue();
			}
		}
		int indexTotal = 0;
		for (double segmentRangeMin = 0.0; segmentRangeMin < highestRevenue; segmentRangeMin += segmentRange) {
			indexTotal += 1;	
		}
		int[] array = new int[indexTotal];
		for (int index = 0; index < indexTotal; index++) {
			for (Product currProduct : this.products) {	
				double currRevenue = currProduct.getRevenue();
				if (currRevenue >= (index * segmentRange) && currRevenue <= ((segmentRange * index) + segmentRange)) {
					array[index] += 1;
				}	
			}
		}
		return array;
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
