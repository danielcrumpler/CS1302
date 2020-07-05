package edu.westga.cs1302.retail.resources;

/**
 * The Class ExceptionMessages.
 * 
 * Defines string messages for exception messages for retail application.
 * 
 * @author CS1302
 */
public class ExceptionMessages {

	public static final String INVALID_NUMBERDECIMALS = "number decimals must be >= 0";

	public static final String UPC_CANNOT_BE_NULL = "UPC cannot be null";
	public static final String UPC_CANNOT_BE_EMPTY = "UPC cannot be empty";
	public static final String UPC_CANNOT_HAVE_NONDIGITS = "UPC can only contain decimal digits";
	public static final String DESCRIPTION_CANNOT_BE_NULL = "description cannot be null";
	public static final String DESCRIPTION_CANNOT_BE_EMPTY = "description cannot be empty";
	public static final String INVALID_REVENUE = "revenue must be >= 0";
	public static final String INVALID_QUANTITY = "quantity must be > 0";

	public static final String SALES_FILE_CANNOT_BE_NULL = "sales file cannot be null";
	public static final String PRODUCTS_CANNOT_BE_NULL = "list of products cannot be null";
	public static final String PRODUCT_CANNOT_BE_NULL = "product cannot be null";
	public static final String INVALID_SEGMENT_RANGE = "segment range must be >= 0.01";
	public static final String MINPRICE_GREATER_THAN_MAXPRICE = "the minimum unit price cannot be greater than the maximum unit price";
	
	public static final String STORE_NAME_CANNOT_BE_EMPTY = "store name cannot be empty";
	public static final String STORE_NAME_CANNOT_BE_NULL = "store name cannot be null";
	public static final String DEPARTMENT_CANNOT_BE_NULL = "department cannot be null";
	
	public static final String SALESDATA_CANNOT_BE_NULL = "sales data cannot be null";
	public static final String DEPARTMENT_NAME_CANNOT_BE_NULL = "department name cannot be null";
	public static final String DEPARTMENT_NAME_CANNOT_BE_EMPTY = "department name cannot be empty";
	
	public static final String REQUIRED = "required";
	public static final String INVALID = "invalid value";
	public static final String INVALID_UPC = "must contain digits only";
	public static final String INVALID_DECIMAL = "must be a decimal number >= 0";
	public static final String TOO_MANY_DECIMAL_DIGITS = "at most two decimal digits";
	public static final String INVALID_POSITIVE_INTEGER = "must be a whole number > 0";
}
