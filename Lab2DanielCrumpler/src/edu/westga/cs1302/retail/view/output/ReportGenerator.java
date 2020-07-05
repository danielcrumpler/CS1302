package edu.westga.cs1302.retail.view.output;

import edu.westga.cs1302.retail.model.SalesData;

/**
 * The Class ReportGenerator.
 * 
 * @author CS1302
 */
public class ReportGenerator {

	public static final String SPACE_DOWN = "\n";
	public static final String CLOSING = ">";
	
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
		String revenueList = "";

		if (salesData == null) {
			summary = "No retail data exists.";
		} else {
			summary = "<" + salesData.getStoreName() + CLOSING + SPACE_DOWN 
					+ "#Products: <#" + salesData.getProducts() + CLOSING + SPACE_DOWN 
					+ "Total revenue: <" + salesData.getTotalRevenue() + CLOSING + SPACE_DOWN;
			if (salesData.size() > 0) {
				revenueList = "Highest Revenue Product: <" + salesData.findProductWithHighestRevenue() + CLOSING + SPACE_DOWN
							+ "Lowest Revenue Product: <" + salesData.findProductWithLowestRevenue() + CLOSING + SPACE_DOWN;
			}	
		}

		return summary + revenueList;
	}
}
