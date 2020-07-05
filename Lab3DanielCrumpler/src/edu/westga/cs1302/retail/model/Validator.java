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
	 * Validates UPC. Removes trailing and leading spaces from the UPC. Checks if the
	 * resulting string is a valid UPC and sets a suitable error message. A valid
	 * UPC consists of decimal digits only and contains at least one digit.
	 *
	 * @precondition none
	 * @postcondition getUpcError().isEmpty() if the passed in upc is valid;
	 *                otherwise getUpcError() returns a suitable error message
	 * 
	 * @param upc the UPC to be validated
	 * @return upc without leading and trailing spaces if it is a valid UPC; null
	 *         otherwise
	 */
	public String validateUpc(String upc) {
		upc.strip();
		if (!upc.matches("[0-9]+")) {
			this.upcError = ExceptionMessages.INVALID_UPC;
			return null;
		}	
		return upc;
	}

	/**
	 * Validates description. Removes trailing and leading spaces from description.
	 * Checks if the resulting string is a valid product description and sets a
	 * suitable error message. A valid product description is a string that is not
	 * null and not empty.
	 *
	 * @precondition none
	 * @postcondition getDescriptionError().isEmpty() if the passed in description
	 *                is valid; otherwise getDescriptionError() returns a suitable
	 *                error message
	 *
	 * @param description the description
	 * @return description without leading and trailing spaces if it is a valid
	 *         product description; null otherwise
	 */
	public String validateDescription(String description) {
		if (description == null) {
			this.descriptionError = ExceptionMessages.DESCRIPTION_CANNOT_BE_NULL;
			return null;
		}
		if (description.isEmpty()) {
			this.descriptionError = ExceptionMessages.DESCRIPTION_CANNOT_BE_EMPTY;
			return null;
		}
		return description.strip();
	}

	/**
	 * Validates revenue. Removes trailing and leading spaces from revenue. Checks if
	 * the resulting string represents a valid revenue value and sets a suitable
	 * error message. A valid revenue has to be a number >= 0 and can contain at most
	 * two digits after the decimal point.
	 *
	 * @precondition none
	 * @postcondition getRevenueError().isEmpty() if the passed in string represents
	 *                a valid revenue; otherwise getRevenueError() returns a
	 *                suitable error message
	 *
	 * @param revenueString the string representing a revenue
	 * @return the value represented by revenueString after leading and trailing
	 *         spaces have been removed; null if revenueString does not represent a
	 *         valid revenue
	 */
	public Double validateRevenue(String revenueString) {
		revenueString.strip();
		try {
			if (revenueString.matches("\\d+")) {
				this.revenueError = ExceptionMessages.INVALID_DECIMAL;
				return null;
			}
			if (revenueString.matches("\\d+\\.\\d{3,}")) {
				this.revenueError = ExceptionMessages.TOO_MANY_DECIMAL_DIGITS;
				return null;
			}	
			if (revenueString.matches("\\d+\\.\\d{0,1}")) {
				this.revenueError = ExceptionMessages.NOT_ENOUGH_DECIMAL_DIGITS;
				return null;
			}	
			if (!revenueString.matches("\\d+\\.\\d{2}")) {
				this.revenueError = ExceptionMessages.INVALID;
				return null;
			} else {
				double revenue = Double.parseDouble(revenueString);
				return revenue;
			}
		} catch (NumberFormatException e) {
			this.revenueError.equals(e.getMessage());
		}
		return null;
	}

	/**
	 * Validates quantity. Removes trailing and leading spaces from quantity. Checks
	 * if the resulting string represents a valid quantity and sets a suitable error
	 * message. A valid quantity has to be an integer number > 0.
	 *
	 * @precondition none
	 * @postcondition getQuantityError().isEmpty() if the passed in string
	 *                represents a valid quantity; otherwise getQuantityError()
	 *                returns a suitable error message
	 * 
	 * @param quantityString the string representing a quantity
	 * @return the value represented by quantityString after leading and trailing
	 *         spaces have been removed; null if quantityString does not represent a
	 *         valid quantity
	 */
	public Integer validateQuantity(String quantityString) {
		quantityString.strip();
		try {
			if (quantityString.contentEquals("0")) {
				this.quantityError = ExceptionMessages.INVALID_POSITIVE_INTEGER;
				return null;
			}	
			if (!quantityString.matches("[0-9]+")) {
				this.quantityError = ExceptionMessages.INVALID;
				return null;
			} else {
				int quantity = Integer.parseInt(quantityString);
				return quantity;
			}
		} catch (NumberFormatException e) {
			this.quantityError.equals(e.getMessage());
		}
		return null;
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
