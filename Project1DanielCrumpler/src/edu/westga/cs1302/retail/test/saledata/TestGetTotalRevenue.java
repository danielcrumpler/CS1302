package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.test.TestingConstants;

class TestGetTotalRevenue {
	@Test
	void testEmptySalesData() {
		SalesData salesData = new SalesData();
		assertEquals(0, salesData.getTotalRevenue(), TestingConstants.DELTA);
	}

	@Test
	void testSalesDataWithOneProduct() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals(11.97, salesData.getTotalRevenue(), TestingConstants.DELTA);
	}

	@Test
	void testSalesDataWithMultipleProducts() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		salesData.add(new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1));
		assertEquals(33.17, salesData.getTotalRevenue(), TestingConstants.DELTA);
	}
}
