package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestFindHighestQuantityBetween {
	@Test
	void testMinPriceGreaterThanMaxPrice() {
		SalesData salesData = new SalesData();
		assertThrows(IllegalArgumentException.class, () -> salesData.findHighestQuantityBetween(2.0, 1.0));
	}
	
	@Test
	void testEmptySalesData() {
		SalesData salesData = new SalesData();
		assertEquals(0, salesData.findHighestQuantityBetween(1.0, 2.0));
	}
	
	@Test
	void testOneProductWithinRange() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals(3, salesData.findHighestQuantityBetween(3.00, 4.00));
	}
	
	@Test
	void testOneProductWithinRangeWhereMinPriceSameAsMaxPrice() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals(3, salesData.findHighestQuantityBetween(3.99, 3.99));
	}
	
	@Test
	void testOneProductJustBelowMinPrice() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals(0, salesData.findHighestQuantityBetween(4.00, 5.00));
	}
	
	@Test
	void testOneProductJustAboveMaxPrice() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 12.00, 3));
		assertEquals(0, salesData.findHighestQuantityBetween(3.00, 3.99));
	}
	
	@Test
	void testMultiProductsNoneWithinRange() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3)); 
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 0.99, 3)); 
		salesData.add(new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1)); 
		assertEquals(0, salesData.findHighestQuantityBetween(0.00, 0.10));
	}
	
	@Test
	void testHighestQuantityProductAtMinPriceBoundary() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "KETCHUP A", 12.00, 3));
		salesData.add(new Product("1300000467", "KETCHUP B", 16.00, 4));
		salesData.add(new Product("1300000468", "KETCHUP C", 5.00, 1));
		assertEquals(4, salesData.findHighestQuantityBetween(4.00, 10.00));
	}
	
	@Test
	void testHighestQuantityProductAtMaxPriceBoundary() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "KETCHUP A", 12.00, 3));
		salesData.add(new Product("1300000467", "KETCHUP B", 16.00, 4));
		salesData.add(new Product("1300000468", "KETCHUP C", 30.00, 6));
		assertEquals(6, salesData.findHighestQuantityBetween(4.00, 5.00));
	}
	

	@Test
	void testHighestQuantityProductInRangeMiddle() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "KETCHUP A", 12.00, 3));
		salesData.add(new Product("1300000467", "KETCHUP B", 16.00, 4));
		salesData.add(new Product("1300000468", "KETCHUP C", 50.00, 10));
		salesData.add(new Product("1300000468", "KETCHUP D", 36.00, 6));
		assertEquals(10, salesData.findHighestQuantityBetween(4.00, 6.00));
	}
	
}
