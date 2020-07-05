package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Product;

class TestFindProduct {

	@Test
	void testFindNullUpcInEmptySalesData() {
		SalesData salesData = new SalesData();
		assertNull(salesData.findProduct(null));
	}
	
	@Test
	void testFindNullUpcInNonemptySalesData() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 3));
		assertNull(salesData.findProduct("1111111111"));
	}
	
	@Test
	void testFindUpcInEmptySalesData() {
		SalesData salesData = new SalesData();
		assertNull(salesData.findProduct("1111111111"));
	}
	
	@Test
	void testMultipleProducstNoneWithUpc() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 3));
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 0.33, 57));
		assertNull(salesData.findProduct("1111111111"));
	}
	
	@Test
	void testOneProductWithUpc() {
		SalesData salesData = new SalesData();
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 3);
		salesData.add(product);
		assertEquals(product, salesData.findProduct("1300000466"));
	}
	
	@Test
	void testMultipleProductsFirstWithUpc() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		
		assertEquals(productA, salesData.findProduct("1300000466"));
	}
	
	@Test
	void testMultipleProductsLastWithUpc() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		
		assertEquals(productC, salesData.findProduct("1300000965"));
	}
	
	@Test
	void testMultipleProductsMiddleWithUpc() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		
		assertEquals(productB, salesData.findProduct("4300095531"));
	}
}
