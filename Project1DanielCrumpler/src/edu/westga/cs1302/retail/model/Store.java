package edu.westga.cs1302.retail.model;

import java.util.ArrayList;

import edu.westga.cs1302.retail.resources.ExceptionMessages;

/**
 * The Class Store.
 * 
 * Maintains the sales data by department of a store
 * 
 * @author CS1302
 */
public class Store {
	private String storeName;
	private ArrayList<Department> departments;

	/**
	 * Instantiates a new store object.
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public Store() {
		this.storeName = null;
		this.departments = new ArrayList<Department>();
	}

	/**
	 * Instantiates a new store object.
	 * 
	 * @precondition storeName != null && !storeName.isEmpty()
	 * @postcondition getStoreName().equals(storeName) && size() == 0
	 *
	 * @param storeName the store name
	 */
	public Store(String storeName) {
		if (storeName == null) {
			throw new IllegalArgumentException(ExceptionMessages.STORE_NAME_CANNOT_BE_NULL);
		}

		if (storeName.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.STORE_NAME_CANNOT_BE_EMPTY);
		}

		this.storeName = storeName;
		this.departments = new ArrayList<Department>();
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
	 * Number departments included in this store.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of departments included in this store
	 */
	public int size() {
		return this.departments.size();
	}
	
	/**
	 * Adds a new department with the specified name.
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * @postcondition findDepartment(name) != null 
	 *
	 * @param name the name of the new department
	 * 
	 * @return true, if adding new department; false, if department already exists
	 */
	public boolean addDepartment(String name) {
		if (name == null) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_NULL);
		}
		
		if (name.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_EMPTY);
		}

		for (Department currDepartment : this.departments) {
			if (name.equalsIgnoreCase(currDepartment.getName())) {
				return false;
			}
		}
		
		Department department = new Department(name);
		return this.departments.add(department);
	}

	/**
	 * Finds department with the specified department name
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param departmentName the department name 
	 * 
	 * @return the department if found; null otherwise
	 */
	public Department findDepartment(String departmentName) {
		if (departmentName == null) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_NULL);
		}

		for (Department currDepartment : this.departments) {
			if (departmentName.equalsIgnoreCase(currDepartment.getName())) {
				return currDepartment;
			}
		}

		return null;
	}

	
	/**
	 * Adds the specified product to the specified department
	 * 
	 * @precondition departmentName != null && product != null
	 * @postconditon findDepartment(departmentName) contains product
	 *
	 * @param departmentName the department name
	 * @param product        the product
	 * 
	 * @return true, if product successfully added; false, otherwise
	 */
	public boolean addProduct(String departmentName, Product product) {
		if (departmentName == null) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_NULL);
		}

		if (product == null) {
			throw new IllegalArgumentException(ExceptionMessages.PRODUCT_CANNOT_BE_NULL);
		}
		
		Department department = this.findDepartment(departmentName);
		if (department != null) {
			return department.getSalesData().add(product);
		}

		return false;
	}

	/**
	 * Removes the specified product from the department with the specified name.
	 * 
	 * @precondition departmentName != null && product != null
	 * @postcondition findDepartment(departmentName) does not contain product
	 *
	 * @param departmentName the department name
	 * @param product        the product to be removed
	 * 
	 * @return true, if successful
	 */
	public boolean removeProduct(String departmentName, Product product) {
		if (departmentName == null) {
			throw new IllegalArgumentException(ExceptionMessages.DEPARTMENT_NAME_CANNOT_BE_NULL);
		}

		if (product == null) {
			throw new IllegalArgumentException(ExceptionMessages.PRODUCT_CANNOT_BE_NULL);
		}

		Department department = this.findDepartment(departmentName);
		if (department != null) {
			return department.getSalesData().remove(product);
		}

		return false;
	}

	/**
	 * Gets the departments.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the departments
	 */
	public ArrayList<Department> getDepartments() {
		return this.departments;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Store:" + this.storeName + " #products:" + this.size();
	}

}
