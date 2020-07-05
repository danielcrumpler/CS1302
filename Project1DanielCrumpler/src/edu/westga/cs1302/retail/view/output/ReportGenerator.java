package edu.westga.cs1302.retail.view.output;

import java.text.NumberFormat;
import java.util.Locale;


import edu.westga.cs1302.retail.model.Department;
import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Store;

/**
 * The Class ReportGenerator.
 * 
 * @author CS1302
 */
public class ReportGenerator {

	private NumberFormat currencyFormatter;
	private String seperator = ", ";
	private String departmentContaining = "> department containing <";
	private String productsAt = "Products at <";
	private String closingBrackectColon = ">:";
	private String productString = "#Products: ";
	private String totalRevenueString = "Total revenue: ";

	/**
	 * Instantiates a new ReportGenerator.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ReportGenerator() {
		this.currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
	}
	
	/**
	 * Builds the summary report of the specified sales data. If sales data is
	 * null, instead of throwing an exception, will return a string saying "No
	 * retail data exists.", otherwise builds a summary report of the sales data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param department the department
	 * @param range the segment revenue range
	 *
	 * @return A formatted summary string of the sales data.
	 */
	public String buildRevenueSummary(Department department, double range) {
		if (department == null) {
			return "No department selected";
		}
		SalesData salesData = department.getSalesData();
		if (salesData == null) {
			return "No retail data exists.";
		}

		String summary = "";
		summary = department.getName() + System.lineSeparator();
		summary += this.productString + salesData.size() + System.lineSeparator();
		summary += this.totalRevenueString + this.currencyFormatter.format(salesData.getTotalRevenue()) + System.lineSeparator();

		if (salesData.size() > 0 && range > 0) {
			summary += System.lineSeparator();
			summary += this.buildProductCountsByRevenueSegments(range, salesData);
		}

		return summary;
	}
	
	/**
	 * Builds the summary report of the store. If store is
	 * null, instead of throwing an exception, will return a string saying "No
	 * store data exists.", otherwise builds a summary report of the store.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param store the store
	 * @param range the segment revenue range
	 *
	 * @return A formatted summary string of the sales data.
	 */
	public String buildStoreRevenueSummary(Store store, double range) {
		if (store == null) {
			return "No store data exists.";
		}

		String summary = store.getStoreName() + System.lineSeparator();
		int products = 0;
		double revenue = 0.0;
		for (Department department : store.getDepartments()) {
			products += department.getSalesData().size();
			revenue += department.getSalesData().getTotalRevenue();
		}
		summary += this.productString + products + System.lineSeparator();
		summary += this.totalRevenueString + this.currencyFormatter.format(revenue) + System.lineSeparator();
		SalesData data = new SalesData();
		for (Department department : store.getDepartments()) {
			data.addAll(department.getSalesData().getProducts());
		}
		summary += System.lineSeparator();
		summary += this.buildProductCountsByRevenueSegments(range, data);
		return summary;
	}
	
	private String buildProductCountsByRevenueSegments(double segmentRange, SalesData salesData) {
		int[] segmentsCounts = salesData.countProductsByRevenueSegment(segmentRange);		
		if (segmentsCounts == null) {
			return "";
		}
		String cash = "(in $)";
		String segmentSummary = " min revenue " + " max revenue " + " #products " + System.lineSeparator() + this.paddingString(cash) + this.paddingString(cash) + System.lineSeparator();		
		double startRevenue = 0;
		double endRevenue = segmentRange;	
		for (int i = 0; i < segmentsCounts.length; i++) {
			String startRevenueFormatted = this.currencyFormatter.format(startRevenue);
			String endRevenueFormatted = this.currencyFormatter.format(endRevenue);
			String segmentsCountsFormatted = String.valueOf(segmentsCounts[i]);
			segmentSummary += this.paddingString(startRevenueFormatted) + this.paddingString(endRevenueFormatted) + this.paddingString(segmentsCountsFormatted) + System.lineSeparator();
			startRevenue = endRevenue + 0.01;
			endRevenue = (segmentRange * (i + 2)); 
		}
		
		return segmentSummary;
	}
	
	private String paddingString(String line) {
		String padding = " ";
		return padding.repeat(12 - line.length()) + line;
	}
	
	/**
	 *  Builds the summary report of the specified department. If department is
	 * null, instead of throwing an exception, will return a string saying "No
	 * retail data exists.", otherwise builds a summary report of the sales data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param department department to be searched
	 * @param searchTerm the term to search for
	 * 
	 * @return A formatted summary string of the sales data in specified department.
	 */
	public String buildDepartmentSummary(Department department, String searchTerm) {
		if (department == null) {
			return "department cannot be null";
		}
		if (searchTerm == null) {
			return "searchTerm cannot be null";
		}
		if (searchTerm.isEmpty()) {
			return "searchTerm cannot be empty";
		}
		SalesData salesData = department.getSalesData();
		String summary = "";
		if (salesData.findProductsWithString(searchTerm).size() == 0) {
			summary = "No products with <" + searchTerm + "> at the <" + department.getName() + "> department";
		}
		if (salesData.findProductsWithString(searchTerm).size() > 0) {
			summary = this.productsAt + department.getName() + this.departmentContaining + searchTerm + closingBrackectColon + System.lineSeparator();
			for (Product currProduct : salesData.findProductsWithString(searchTerm)) {
				summary += currProduct.getUpc() + this.seperator + currProduct.getDescription() + this.seperator + this.currencyFormatter.format(currProduct.getRevenue()) + this.seperator + currProduct.getQuantitySold() + System.lineSeparator();
			}
		}
		return summary;
	}
	
	/**
	 *  Builds the summary report of the all departments. If department is
	 * null, instead of throwing an exception, will return a string saying "No
	 * retail data exists.", otherwise builds a summary report of the sales data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param store store to be searched
	 * @param searchTerm the term to search for
	 * 
	 * @return A formatted summary string of the sales data in specified department.
	 */
	public String includeAllDepartments(Store store, String searchTerm) {
		if (store == null) {
			return "store cannot be null";
		}
		if (searchTerm == null) {
			return "searchTerm can not be null";
		}
		if (searchTerm.isEmpty()) {
			return "searchTerm can not be empty";
		}
		String summary = "";
		for (Department department : store.getDepartments()) {
			if (department.getSalesData().findProductsWithString(searchTerm).size() == 0) {
				summary += "No products at <" + department.getName() + this.departmentContaining + searchTerm + closingBrackectColon + System.lineSeparator();
				summary += System.lineSeparator();
			}
			if (department.getSalesData().findProductsWithString(searchTerm).size() > 0) {
				summary += this.productsAt + department.getName() + this.departmentContaining + searchTerm + closingBrackectColon + System.lineSeparator();
				for (Product currProduct : department.getSalesData().findProductsWithString(searchTerm)) {
					summary += currProduct.getUpc() + this.seperator + currProduct.getDescription() + this.seperator + this.currencyFormatter.format(currProduct.getRevenue()) + this.seperator + currProduct.getQuantitySold() + System.lineSeparator();
				}
				summary += System.lineSeparator();
			}
		}
		return summary;
	}
}
