package edu.westga.cs1302.retail.viewmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs1302.retail.model.Validator;
import edu.westga.cs1302.retail.datatier.SalesDataFileReader;
import edu.westga.cs1302.retail.datatier.SalesDataFileWriter;
import edu.westga.cs1302.retail.file.FileCreationOnStart;
import edu.westga.cs1302.retail.model.Department;
import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Store;
import edu.westga.cs1302.retail.view.output.ReportGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * The Class RetailViewModel.
 * 
 * @author CS1302
 */
public class RetailViewModel {
	private Store store;
	private ReportGenerator report;
	private FileCreationOnStart file;
	
	private ListProperty<Department> departmentsProperty;
	private StringProperty departmentNameProperty;
	private ObjectProperty<Department> selectedDepartmentProperty;
	
	private StringProperty upcProperty;
	private StringProperty descriptionProperty;
	private StringProperty revenueProperty;
	private StringProperty quantityProperty;
	
	private StringProperty errorProperty;
	private StringProperty upcErrorProperty;
	private StringProperty descriptionErrorProperty;
	private StringProperty revenueErrorProperty;
	private StringProperty 	quantityErrorProperty;
	
	private ObjectProperty<Product> selectedProductProperty;

	private ListProperty<Product> productsProperty;
	private StringProperty summaryProperty;
	private BooleanProperty outdatedSummaryProperty;

	/**
	 * Instantiates a new retail view model.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public RetailViewModel() {
		this.store = new Store("Wolfie's Market");
		this.report = new ReportGenerator();

		this.departmentsProperty = new SimpleListProperty<Department>();
		this.departmentNameProperty = new SimpleStringProperty();
		this.selectedDepartmentProperty = new SimpleObjectProperty<Department>();
		
		this.upcProperty = new SimpleStringProperty();
		this.descriptionProperty = new SimpleStringProperty();
		this.revenueProperty = new SimpleStringProperty();
		this.quantityProperty = new SimpleStringProperty();
		
		this.errorProperty = new SimpleStringProperty();
		this.upcErrorProperty = new SimpleStringProperty();
		this.descriptionErrorProperty = new SimpleStringProperty();
		this.revenueErrorProperty = new SimpleStringProperty();
		this.quantityErrorProperty = new SimpleStringProperty();
		
		this.selectedProductProperty = new SimpleObjectProperty<Product>();

		this.productsProperty = new SimpleListProperty<Product>();
		this.summaryProperty = new SimpleStringProperty();
		this.outdatedSummaryProperty = new SimpleBooleanProperty();
		
		try {
			this.file = new FileCreationOnStart();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}	
		
		this.resetDepartments();
	}

	
	/**
	 * departments property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of departments property
	 */
	public ListProperty<Department> departmentsProperty() {
		return this.departmentsProperty;
	}
	
	/**
	 * Department name property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the department name property
	 */
	public StringProperty departmentNameProperty() {
		return this.departmentNameProperty;
	}
	
	/**
	 * Selected department property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the selected department property
	 */
	public ObjectProperty<Department> selectedDepartmentProperty() {
		return this.selectedDepartmentProperty;
	}
	
	/**
	 * UPC property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the UPC property
	 */
	public StringProperty upcProperty() {
		return this.upcProperty;
	}

	/**
	 * Description property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description property
	 */
	public StringProperty descriptionProperty() {
		return this.descriptionProperty;
	}

	/**
	 * Revenue property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the revenue property
	 */
	public StringProperty revenueProperty() {
		return this.revenueProperty;
	}

	/**
	 * Quantity property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the quantity property
	 */
	public StringProperty quantityProperty() {
		return this.quantityProperty;
	}

	/**
	 * error property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the error property
	 */
	public StringProperty errorProperty() {
		return this.errorProperty;
	}

	/**
	 * UPC error property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the UPC error property
	 */
	public StringProperty upcErrorProperty() {
		return this.upcErrorProperty;
	}

	/**
	 * Description error property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description error property
	 */
	public StringProperty descriptionErrorProperty() {
		return this.descriptionErrorProperty;
	}

	/**
	 * Revenue error property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the revenue error property
	 */
	public StringProperty revenueErrorProperty() {
		return this.revenueErrorProperty;
	}

	/**
	 * Quantity error property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the quantity error property
	 */
	public StringProperty quantityErrorProperty() {
		return this.quantityErrorProperty;
	}

	/**
	 * Selected product property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the selected product property
	 */
	public ObjectProperty<Product> selectedProductProperty() {
		return this.selectedProductProperty;
	}

	/**
	 * products property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of products property
	 */
	public ListProperty<Product> productsProperty() {
		return this.productsProperty;
	}

	/**
	 * Summary property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the summary property
	 */
	public StringProperty summaryProperty() {
		return this.summaryProperty;
	}
	
	/**
	 * Outdated summary property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the update summary property
	 */
	public BooleanProperty outdatedSummaryProperty() {
		return this.outdatedSummaryProperty;
	}

	/**
	 * Add department to the store.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if department successfully added; false otherwise
	 */
	public boolean addDepartment() {
		String departmentName = this.departmentNameProperty.get();
		if (this.store.addDepartment(departmentName)) {
			this.resetDepartments();
			this.addToDepartmentFile();
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a products with specified attributes to the store.
	 *
	 * @return true if product added successfully, false otherwise
	 */
	public boolean addProduct() {
		Department department = this.selectedDepartmentProperty.get();
		Validator validator = new Validator();
		String upc = validator.validateUpc(this.upcProperty.get());
		String description = validator.validateDescription(this.descriptionProperty.get());
		Double revenue = validator.validateRevenue(this.revenueProperty.get());
		Integer quantity = validator.validateQuantity(this.quantityProperty.get());
		this.upcErrorProperty.set(validator.getUpcError());
		this.descriptionErrorProperty.set(validator.getDescriptionError());
		this.revenueErrorProperty.set(validator.getRevenueError());
		this.quantityErrorProperty.set(validator.getQuantityError());
		
		if (!validator.foundError()) {
			try {
				Product product = new Product(upc, description, revenue, quantity);
				SalesData salesData = department.getSalesData(); 
				if (salesData.add(product)) {
					this.resetFormFields();
					return true;
				}
				this.errorProperty.set("Product with same UPC and different discription exists");
			} catch (IllegalArgumentException e) {
				this.errorProperty.set(e.getMessage());
			}
		}

		return false;
	}

	/**
	 * Update selected product with new description, revenue, and quantity
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if successful
	 */
	public boolean updateProduct() {
		Product product = this.selectedProductProperty.get();
		if (product != null) {
			String description = this.descriptionProperty.get();
			double revenue = Double.parseDouble(this.revenueProperty.get());
			int quantity = Integer.parseInt(this.quantityProperty.get());

			try {
				product.setDescription(description);
				product.setRevenue(revenue);
				product.setQuantitySold(quantity);

				this.resetFormFields();
				return true;
			} catch (IllegalArgumentException e) {
				this.errorProperty.set(e.getMessage());
			}
		}

		return false;
	}

	/**
	 * Remove selected product from the sales data.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if product successfully deleted, false otherwise
	 */
	public boolean deleteProduct() {
		Department department = this.selectedDepartmentProperty.get();
		Product product = this.selectedProductProperty.get();

		SalesData salesData = department.getSalesData();
		if (salesData.remove(product)) {
			this.resetFormFields();
			return true;
		}

		return false;
	}

	/**
	 * Updates the summary report to display the number products by revenue range
	 * for the selected department
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param range the revenue range
	 */
	public void updateRevenueSummaryForDepartment(double range) {
		Department department = this.selectedDepartmentProperty.get();
		String summary = this.report.buildRevenueSummary(department, range);
		this.summaryProperty.set(summary);
	}

	/**
	 * Updates the summary report to display the number products by revenue range
	 * for all departments
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param range the revenue range
	 */
	public void updateRevenueSummaryForStore(double range) {
		String summary = this.report.buildStoreRevenueSummary(this.store, range);
		this.summaryProperty.set(summary);
	}
	
	/**
	 * Updates the summary report to display the specified products 
	 * for the selected department
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param searchTerm the search term
	 */
	public void updateProductSummaryForDepartment(String searchTerm) {
		Department department = this.selectedDepartmentProperty.get();
		String summary = this.report.buildDepartmentSummary(department, searchTerm);
		this.summaryProperty.set(summary);
	}

	/**
	 * Updates the summary report to display the specified products 
	 * for all departments
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param searchTerm the search term
	 */
	public void updateProductSummaryForStore(String searchTerm) {
		String summary = this.report.includeAllDepartments(this.store, searchTerm);
		this.summaryProperty.set(summary);
	}

	/**
	 * Resets all form fields.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void resetFormFields() {
		this.upcProperty.set("");
		this.descriptionProperty.set("");
		this.revenueProperty.set("");
		this.quantityProperty.set("");
		
		this.errorProperty.set("");
		this.upcErrorProperty.set("");
		this.descriptionErrorProperty.set("");
		this.revenueErrorProperty.set("");
		this.quantityErrorProperty.set("");

		this.outdatedSummaryProperty.set(true);
		Department department = this.selectedDepartmentProperty.get();
		if (department != null) {
			this.productsProperty.set(FXCollections.observableArrayList(department.getSalesData().getProducts()));
		}
	}

	private void resetDepartments() {
		this.departmentNameProperty.set("");
		this.departmentsProperty.set(FXCollections.observableArrayList(this.store.getDepartments()));
	}
	
	/**
	 * Saves the retail sales data to the specified File.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @param file the file to save the retail sales data to
	 */
	public void saveSalesData(File file) {
		SalesDataFileWriter writer = new SalesDataFileWriter(file);
		try {
			writer.writeStore(this.store);
		} catch (FileNotFoundException e) {
			this.errorProperty.set(e.getMessage());
		}
	}

	/**
	 * Loads the retail sales data file using the specified File object.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param file The file to load the retail sales data from
	 */
	public void loadSalesData(File file) {
		SalesDataFileReader reader = new SalesDataFileReader(file);
		try {
			reader.loadAllProductsIntoStore(this.store);
			this.resetFormFields();
			this.resetDepartments();
		} catch (FileNotFoundException e) {
			this.errorProperty.set(e.getMessage());
		}
		this.addToDepartmentFile();
	}
	
	private void addToDepartmentFile() {
		try {
			this.file.write(this.store);
		} catch (FileNotFoundException e) {
			this.errorProperty.set(e.getMessage());
		}	
	}

}
