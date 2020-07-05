package edu.westga.cs1302.retail.view.output;

import edu.westga.cs1302.retail.model.SalesData;

import edu.westga.cs1302.retail.model.Product;

/**
 * The Class ReportGenerator.
 * 
 * @author CS1302
 */
public class ReportGenerator {

	/**
	 * Builds the summary report of the specified sales data. If sales data is
	 * null, instead of throwing an exception, will return a string saying "No
	 * retail data exists.", otherwise builds a summary report of the sales data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param salesData the sales data
	 *
	 * @return A formatted summary string of the sales data.
	 */
	public String buildSummaryReport(SalesData salesData) {
		String summary = "";

		if (salesData == null) {
			summary = "No retail data exists.";
		} else {
			summary = salesData.getStoreName() + System.lineSeparator();
			summary += "#Products: " + salesData.size() + System.lineSeparator();
			summary += "Total revenue: " + String.format("$%,.2f", salesData.getTotalRevenue()) + System.lineSeparator();
		}

		if (salesData.size() > 0) {
			String upcHighestRevenue = salesData.findUpcWithHighestRevenue();
			Product highestRevenueProduct = salesData.findProduct(upcHighestRevenue);
			String upcLowestRevenue = salesData.findUpcWithLowestRevenue();
			Product lowestRevenueProduct = salesData.findProduct(upcLowestRevenue);

			summary += System.lineSeparator();
			summary += "Product with highest revenue: ";
			summary += this.buildProductOutput(highestRevenueProduct) + System.lineSeparator();
			summary += "Product with lowest revenue: ";
			summary += this.buildProductOutput(lowestRevenueProduct) + System.lineSeparator();
			summary += "Product in $25.00 revenue range segments:" +  System.lineSeparator();
			summary += "$0.00 - $25.00 : " + salesData.segmentRangeForProductRevenue(25.00)[0] + System.lineSeparator();
			summary += "$25.01 - $50.00 : " + salesData.segmentRangeForProductRevenue(25.00)[1] + System.lineSeparator();
			summary += "$50.01 - $75.00 : " + salesData.segmentRangeForProductRevenue(25.00)[2] + System.lineSeparator();
			summary += "$75.01 - $100.00 : " + salesData.segmentRangeForProductRevenue(25.00)[3] + System.lineSeparator();
			summary += "$100.01 - $125.00 : " + salesData.segmentRangeForProductRevenue(25.00)[4] + System.lineSeparator();
			summary += "Product in $100.00 revenue range segments:" +  System.lineSeparator();
			summary += "$0.00 - $100.00 : " + salesData.segmentRangeForProductRevenue(100.00)[0] + System.lineSeparator();
			summary += "$100.01 - $200.00 : " + salesData.segmentRangeForProductRevenue(100.00)[1] + System.lineSeparator();
		}

		return summary;
	}

	private String buildProductOutput(Product product) {
		String output = product.getDescription() + " " + String.format("$%,.2f", product.getRevenue()) + " " + product.getQuantitySold();
		return output;
	}
	
}
