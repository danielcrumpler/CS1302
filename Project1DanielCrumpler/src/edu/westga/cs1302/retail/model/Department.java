package edu.westga.cs1302.retail.model;

import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class Department.
 * 
 * Maintains the sales data of a department.
 * 
 * @author CS1302
 */
public class Department {
	private String name;
	private SalesData salesData;

	/**
	 * Instantiates a new department object.
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public Department() {
		this.name = "unknown";
		this.salesData = new SalesData();
	}

	/**
	 * Instantiates a new department object.
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * @postcondition getName().equals(name) && size() == 0
	 *
	 * @param name the department's name
	 */
	public Department(String name) {
		if (name == null) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_NULL);
		}

		if (name.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_EMPTY);
		}

		this.name = name;
		this.salesData = new SalesData();
	}

	/**
	 * Gets the name.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * @postcondition getName().equals(name)
	 *
	 * @param name the department's name to set
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_NULL);
		}

		if (name.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_EMPTY);
		}

		this.name = name;
	}

	/**
	 * Gets the sales data.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the sales data
	 */
	public SalesData getSalesData() {
		return this.salesData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
