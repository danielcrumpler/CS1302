package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Product;

class TestFindProductWithLowestRevenue {

	@Test
	void testEmptySalesData() {
		SalesData salesData = new SalesData();
		assertEquals("null,null,null", salesData.findProductWithLowestRevenue());
	}

	@Test
	void testOneProduct() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals("HEINZ KETCHUP 38 OZ,11.97,3", salesData.findProductWithLowestRevenue());
	}
	
	@Test
	void testOneProductWithZeroRevenue() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 0, 57));
		assertEquals("KOOL AID ORANGE 2 QT,0.0,57", salesData.findProductWithLowestRevenue());
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
		assertEquals("JD HICKORY BBQ SAUCE 19OZ,7.17,3", salesData.findProductWithLowestRevenue());
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
		assertEquals("JD HICKORY BBQ SAUCE 19OZ,7.17,3", salesData.findProductWithLowestRevenue());
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
		assertEquals("JD HICKORY BBQ SAUCE 19OZ,7.17,3", salesData.findProductWithLowestRevenue());
	}
}

