package edu.westga.cs1302.retail.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class SalesData.
 * 
 * @author CS1302
 */
public class SalesData {
	private ArrayList<Product> products;

	/**
	 * Instantiates a new sales data object.
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public SalesData() {
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
	 * @postcondition size() == size()@prev + 1 && getProducts().contains(product)
	 *
	 * @param product the product
	 * @return true, if add successful
	 */
	public boolean add(Product product) {
		if (product == null) {
			throw new IllegalArgumentException(ExceptionMessages.PRODUCT_CANNOT_BE_NULL);
		}
		for (Product currProduct : this.products) {
			if (currProduct.getUpc().equals(product.getUpc())) {
				if (currProduct.getDescription().toLowerCase().equals(product.getDescription().toLowerCase())) {
					currProduct.setRevenue(currProduct.getRevenue() + product.getRevenue());
					currProduct.setQuantitySold(currProduct.getQuantitySold() + product.getQuantitySold());
					return true;
				}
				return false;
			}
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
		for (int i = 0; i < products.size(); i++) {
			this.add(products.get(i));
		}
		return true;
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
	 * @param minUnitPrice lower bound for unit price
	 * @param maxUnitPrice upper bound for unit price
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
	 * Creates an array that holds the count of the number of products in each
	 * segment starting from 0 to segmentRange up to the last range which
	 * includes the highest revenue product.
	 * 
	 * @precondition segmentRange >= 0.01
	 * @postcondition none
	 *
	 * @param segmentRange the range of the revenue values
	 * @return Array with number of products of this sales data that are in each
	 *         segment. Returns null if this sales data is empty.
	 */
	public int[] countProductsByRevenueSegment(double segmentRange) {
		if (segmentRange < 0.01) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_SEGMENT_RANGE);
		}
		
		if (this.size() == 0) {
			return null;
		}

		double maxRevenue = this.findHighestProductRevenue();
		int segments = (int) Math.ceil(maxRevenue / segmentRange);
		if (segments == 0) {
			segments = 1;
		}
		int[] productCount = new int[segments];

		for (Product currProduct : this.products) {
			double currRevenue = currProduct.getRevenue();
			int segmentNumber = (int) ((currRevenue - 0.005) / segmentRange);
			productCount[segmentNumber]++;
		}

		return productCount;
	}
	
	/**
	 * Finds the product with the specified UPC.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param string the string of the product to be returned
	 * 
	 * @return the array list of products with the specified UPC or Description
	 */
	public ArrayList<Product> findProductsWithString(String string) {
		if (this.products.isEmpty()) {
			return null;
		}
		String[] inputs = string.split("&");
		ArrayList<Product> found = new ArrayList<Product>();
		String regex = "";
		for (String currInput : inputs) {
			regex += "(?=.*?(" + currInput + "))";
		}
		
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		
		for (Product currProduct : this.products) {
			Matcher matcher = pattern.matcher(currProduct.getUpc() + " " + currProduct.getDescription());
			if (matcher.find()) {
				found.add(currProduct);
			}	
		}
		return found;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sales data with " + this.size() + " products";
	}
	
	private double findHighestProductRevenue() {
		double highestRevenue = -1;
		
		String upc = this.findUpcWithHighestRevenue();
		
		if (upc != null) {
			Product product = this.findProduct(upc);
			highestRevenue = product.getRevenue();
		}
		return highestRevenue;
	}

}
