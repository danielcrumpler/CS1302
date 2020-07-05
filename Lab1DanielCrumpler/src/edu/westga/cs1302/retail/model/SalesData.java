package edu.westga.cs1302.retail.model;

import java.util.ArrayList;

/**
 * The Class Product.
 * 
 * @author CS1302
 */
public class SalesData {

	public static final String STORE_NAME_CANNOT_BE_NULL = "StoreName cannot be null";
	public static final String STORE_NAME_CANNOT_BE_EMPTY = "StoreName cannot be empty";
	private String storeName;
	private ArrayList<Product> dataList;
	
	/**
	 * Create a new empty store
	 * 
	 * @precondition storeName != null && !storeName.isEmpty()
	 * @postcondition size() == 0 && getProducts().size() == 0
	 * 
	 * @param storeName the stores's name
	 */
	public SalesData(String storeName) {
		if (storeName == null) {
			throw new IllegalArgumentException(SalesData.STORE_NAME_CANNOT_BE_NULL);
		}
		if (storeName.isEmpty()) {
			throw new IllegalArgumentException(SalesData.STORE_NAME_CANNOT_BE_EMPTY);
		}
		
		this.storeName = storeName;
		this.dataList = new ArrayList<Product>();
	}	
	
	/**
	 * Return the collection of products at the store.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the collection of products at the store
	 */
	public ArrayList<Product> getProducts() {
		return this.dataList;
	}

	/**
	 * Returns the name of this store.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the store
	 */
	public String getName() {
		return this.storeName;
	}

	/**
	 * Return the number of products at the store.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of products at the store
	 */
	public int getNumberOfProductsInDataList() {
		return this.dataList.size();
	}
	
	/**
	 * Add a new product to the sales data
	 * 
	 * @precondition products != null
	 * @postcondition getNumberOfProductsInDataList() == getNumberOfProductsInDataList()@pre +
	 *                1
	 * 
	 * @param products product to be added to the dataList at the store
	 * @return boolean for if product was added
	 */
	public boolean addProduct(Product products) {
		if (products == null) {
			throw new IllegalArgumentException("animal must not be null");
		}
		this.dataList.add(products);
		if (this.dataList.contains(products)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the Total Revenue of Products at the store.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return revenue total
	 */
	public double getTotalRevenueOfProductsInDataList() {
		double revenue = 0.0;
		for (Product data :this.dataList) {
			revenue += data.getRevenue();
		}
		return revenue;
	}
	
	/**
	 * Returns the UPC of highest revenue product at the store.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return upc of product
	 */
	public String getUPCOfHighestRevenueProduct() {
		double revenue = Integer.MIN_VALUE;
		String upc = "";
		for (Product data :this.dataList) {
			if (data.getRevenue() > revenue) {
				revenue = data.getRevenue();
				upc = data.getUpc();
			}	
		}
		return upc;
	}
	
	/**
	 * Returns the highest quantity of units sold within given range
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param lowerRange lower bound of unit price
	 * @param higherRange upper bound of unit price
	 * 
	 * @return the highest quantity of units sold for range
	 */
	public int getHighestQuantityofUnitsSold(double lowerRange, double higherRange) {
		int units = 0;
		for (Product data :this.dataList) {
			if ((data.getRevenue() / data.getQuantitySold()) >= lowerRange && (data.getRevenue() / data.getQuantitySold()) <= higherRange && data.getQuantitySold() > units) {
				units = data.getQuantitySold();
			}	
		}
		return units;
	}
	
	@Override
	public String toString() {
		return "SalesData:" + this.storeName + "#products: " + this.getNumberOfProductsInDataList();
	}
}
