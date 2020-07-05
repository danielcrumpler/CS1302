package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Product;

class TestFindUpcWithLowestRevenue {

	@Test
	void testEmptySalesData() {
		SalesData salesData = new SalesData();
		assertEquals(null, salesData.findUpcWithLowestRevenue());
	}

	@Test
	void testOneProduct() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals("1300000466", salesData.findUpcWithLowestRevenue());
	}
	
	@Test
	void testOneProductWithZeroRevenue() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 0, 57));
		assertEquals("4300095531", salesData.findUpcWithLowestRevenue());
	}

	@Test
	void testLowestRevenueFirstAmongMultipleProducts() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 7.17, 3);
		Product productB = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productC = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		assertEquals("1300000965", salesData.findUpcWithLowestRevenue());
	}
	
	@Test
	void testLowestRevenueLastAmongMultipleProducts() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 7.17, 3);
		Product productB = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productC = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		salesData.add(productB);
		salesData.add(productC);
		salesData.add(productA);
		assertEquals("1300000965", salesData.findUpcWithLowestRevenue());
	}
	
	@Test
	void testLowestRevenueInMiddleAmongMultipleProducts() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 7.17, 3);
		Product productB = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productC = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		salesData.add(productB);
		salesData.add(productA);
		salesData.add(productC);
		assertEquals("1300000965", salesData.findUpcWithLowestRevenue());
	}
}
