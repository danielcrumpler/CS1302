package edu.westga.cs1302.retail.model;

import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class Validator.
 * 
 * @author CS1302
 */
public class Validator {

	private String upcError;
	private String descriptionError;
	private String revenueError;
	private String quantityError;
	
	/**
	 * Instantiates a new validator.
	 * 
	 * @precondition none
	 * @postcondition getUpcError().isEmpty() && getDescriptionError().isEmpty() &&
	 *                getRevenueError().isEmpty() && getQuantityError().isEmpty() 
	 */
	public Validator() {
		this.reset();
	}

	/**
	 * Gets the UPC error.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the UPC error 
	 */
	public String getUpcError() {
		return this.upcError;
	}

	/**
	 * Gets the description error.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description error
	 */
	public String getDescriptionError() {
		return this.descriptionError;
	}

	/**
	 * Gets the revenue error.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the revenue error
	 */
	public String getRevenueError() {
		return this.revenueError;
	}

	/**
	 * Gets the quantity error.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the quantity error 
	 */
	public String getQuantityError() {
		return this.quantityError;
	}

	/**
	 * Found error.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if a preceding call to a validation method detected an error
	 */
	public boolean foundError() {
		return !this.upcError.isEmpty() || !this.descriptionError.isEmpty() || !this.revenueError.isEmpty()
				|| !this.quantityError.isEmpty();
	}

	/**
	 * Validate UPC. 
	 * Removes trailing and leading spaces from UPC. Checks if the resulting 
	 * string is a valid UPC and sets a suitable error message. A valid UPC consists of decimal digits only and contains at least one digit.
	 *
	 * @precondition none
	 * @postcondition getUpcError().isEmpty() if the passed in upc is valid;
	 *                otherwise getUpcError() returns an suitable error message
	 * 
	 * @param upc the UPC to be validated
	 * @return upc without leading and trailing spaces if it is a valid UPC; null otherwise
	 */
	public String validateUpc(String upc) {
		upc = upc.trim();
		if (upc == null || upc.isEmpty()) {
			this.upcError = ExceptionMessages.REQUIRED;
			return null;
		} 
		if (!upc.matches("\\d+")) {
			this.upcError = ExceptionMessages.INVALID_UPC;
			return null;
		} else {
			this.upcError = "";
			return upc;
		}
	}
	
	/**
	 * Validate description. 
	 * Removes trailing and leading spaces from description. Checks if the resulting 
	 * string is a valid product description and sets a suitable error message.
	 *
	 * @precondition none
	 * @postcondition getDescriptionError().isEmpty() if the passed in description
	 *                is valid; otherwise getDescriptionError() returns an suitable
	 *                error message
	 *
	 * @param description the description
	 * @return description without leading and trailing spaces if it is a valid
	 *         product description; null otherwise
	 */
	public String validateDescription(String description) {
		description = description.trim();
		if (description == null || description.isEmpty()) {
			this.descriptionError = ExceptionMessages.REQUIRED;
			return null;
		} else {
			this.descriptionError = "";
			return description;
		}
	}
	
	/**
	 * Validate revenue. Removes trailing and leading spaces from revenue. Checks if the
	 * resulting string represents a valid revenue value and sets a suitable error
	 * message. A valid revenue as to be a number >= 0 and can contain at most two
	 * decimal digits.
	 *
	 * @precondition none
	 * @postcondition getRevenueError().isEmpty() if the passed in string represents a
	 *                valid revenue; otherwise getRevenueError() returns an suitable
	 *                error message
	 *
	 * @param revenueString the string representing a revenue
	 * @return the value represented by revenueString after leading and trailing spaces have
	 *         been removed; null if revenueString does not represent a valid revenue
	 */
	public Double validateRevenue(String revenueString) {
		Double revenue = null;
		this.revenueError = "";
		revenueString = revenueString.trim();
		
		if (revenueString == null || revenueString.isEmpty()) {
			this.revenueError = ExceptionMessages.REQUIRED;
			return null;
		}

		if (revenueString.matches("\\d*\\.\\d{3,}")) {
			this.revenueError = ExceptionMessages.TOO_MANY_DECIMAL_DIGITS;
			return null;
		}
		
		if (!revenueString.matches("\\d*(\\.\\d{1,2})?")) {
			this.revenueError = ExceptionMessages.INVALID_DECIMAL;
			return null;
		}
		
		try {
			revenue = Double.parseDouble(revenueString);
		} catch (NumberFormatException e) {
			this.revenueError = ExceptionMessages.INVALID;
			return null;
		}

		return revenue;
	}
	
	/**
	 * Validate quantity.
	 * Removes trailing and leading spaces from quantity. Checks if the resulting
	 * string represents a valid quantity and sets a suitable error
	 * message. A valid quantity has to be an integer number > 0.
	 *
	 * @precondition none
	 * @postcondition getQuantityError().isEmpty() if the passed in string
	 *                represents a valid quantity; otherwise
	 *                getQuantityError() returns an suitable error message
	 * 
	 * @param quantityString the string representing a quantity
	 * @return the value represented by quantityString after leading and trailing spaces
	 *         have been removed; null if quantityString does not represent a valid
	 *         quantity
	 */
	public Integer validateQuantity(String quantityString) {
		Integer quantity = null;
		this.quantityError = "";
		quantityString = quantityString.trim();

		if (quantityString == null || quantityString.isEmpty()) {
			this.quantityError = ExceptionMessages.REQUIRED;
			return null;
		}
		if (!quantityString.matches("[1-9]\\d*")) {
			this.quantityError = ExceptionMessages.INVALID_POSITIVE_INTEGER;
			return null;
		}

		try {
			quantity = Integer.parseInt(quantityString);
		} catch (NumberFormatException e) {
			this.quantityError = ExceptionMessages.INVALID;
			return null;
		}

		return quantity;
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		this.upcError = "";
		this.descriptionError = "";
		this.revenueError = "";
		this.quantityError = "";
	}
}
