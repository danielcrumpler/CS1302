package edu.westga.cs1302.retail.resources;

/**
 * The Class ExceptionMessages.
 * 
 * Defines string messages for exception messages for retail application.
 * 
 * @author CS1302
 */
public class ExceptionMessages {

	public static final String INVALID_NUMBERDECIMALS = "number decimals must be >= 0.";

	public static final String UPC_CANNOT_BE_NULL = "UPC cannot be null.";
	public static final String UPC_CANNOT_BE_EMPTY = "UPC cannot be empty.";
	public static final String DESCRIPTION_CANNOT_BE_NULL = "description cannot be null.";
	public static final String DESCRIPTION_CANNOT_BE_EMPTY = "description cannot be empty.";
	public static final String INVALID_REVENUE = "revenue must be >= 0.";
	public static final String INVALID_QUANTITY = "quantity must be > 0.";

	public static final String SALES_FILE_CANNOT_BE_NULL = "sales file cannot be null.";
	public static final String PRODUCTS_CANNOT_BE_NULL = "list of products cannot be null.";
	public static final String PRODUCT_CANNOT_BE_NULL = "product cannot be null.";
	public static final String STORE_NAME_CANNOT_BE_EMPTY = "store name cannot be empty.";
	public static final String STORE_NAME_CANNOT_BE_NULL = "store name cannot be null.";
	public static final String MINPRICE_GREATER_THAN_MAXPRICE = "the minimum unit price cannot be greater than the maximum unit price";
}
