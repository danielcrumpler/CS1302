package edu.westga.cs1302.retail.viewmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.datatier.SalesDataFileReader;
import edu.westga.cs1302.retail.datatier.SalesDataFileWriter;
import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.view.output.ReportGenerator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
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
	private SalesData salesData;
	private ReportGenerator report;

	private StringProperty upcProperty;
	private StringProperty descriptionProperty;
	private StringProperty revenueProperty;
	private StringProperty quantityProperty;
	private StringProperty errorProperty;

	private ObjectProperty<Product> selectedProductProperty;

	private ListProperty<Product> productsProperty;
	private StringProperty summaryProperty;

	/**
	 * Instantiates a new retail view model.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public RetailViewModel() {
		this.salesData = new SalesData("Store");
		this.report = new ReportGenerator();

		this.upcProperty = new SimpleStringProperty();
		this.descriptionProperty = new SimpleStringProperty();
		this.revenueProperty = new SimpleStringProperty();
		this.quantityProperty = new SimpleStringProperty();
		this.errorProperty = new SimpleStringProperty();

		this.selectedProductProperty = new SimpleObjectProperty<Product>();

		this.productsProperty = new SimpleListProperty<Product>();
		this.summaryProperty = new SimpleStringProperty();

		this.resetFormFields();
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
	 * Adds a products with specified attributes to the store.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if product added successfully, false otherwise
	 */
	public boolean addProduct() {
		String upc = this.upcProperty.get();
		String description = this.descriptionProperty.get();
		double revenue = Double.parseDouble(this.revenueProperty.get());
		int quantity = Integer.parseInt(this.quantityProperty.get());

		try {
			Product product = new Product(upc, description, revenue, quantity);
			if (this.salesData.add(product)) {
				this.resetFormFields();
				return true;
			}
		} catch (IllegalArgumentException e) {
			this.errorProperty.set(e.getMessage());
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
		try {
			Product product = this.selectedProductProperty.get();
			if (product != null) {
				String description = this.descriptionProperty.get();
				double revenue = Double.parseDouble(this.revenueProperty.get());
				int quantity = Integer.parseInt(this.quantityProperty.get());
			
				product.setDescription(description);
				product.setRevenue(revenue);
				product.setQuantitySold(quantity);

				this.resetFormFields();
				return true;
			}
		} catch (IllegalArgumentException e) {
			this.errorProperty.set(e.getMessage());
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
		Product product = this.selectedProductProperty.get();
		while (this.salesData.deleteProduct(product)) {
			this.resetFormFields();
			return true;
		}
		return false;
	}

	private void updateSummary() {
		String summaryReport = this.report.buildSummaryReport(this.salesData);
		this.summaryProperty.set(summaryReport);
	}

	private void resetFormFields() {
		this.upcProperty.set("");
		this.descriptionProperty.set("");
		this.revenueProperty.set("");
		this.quantityProperty.set("");
		this.errorProperty.set("");
		this.updateSummary();
		this.productsProperty.set(FXCollections.observableArrayList(this.salesData.getProducts()));
	}

	/**
	 * Save the retail sales data to the specified File.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @param file the file to save the retail sales data to
	 */
	public void saveRetailSalesData(File file) {
		try {
			SalesDataFileWriter writer = new SalesDataFileWriter(file);
			writer.write(this.salesData.getProducts());
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
	public void loadRetailSalesData(File file) {
		try {
			SalesDataFileReader reader = new SalesDataFileReader(file);
			ArrayList<Product> store = reader.loadAllProducts();
			this.salesData.addArrayList(store);
		} catch (FileNotFoundException e) {
			this.errorProperty.set(e.getMessage());
		}
		this.resetFormFields();
	}
}
