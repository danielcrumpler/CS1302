package edu.westga.cs1302.retail.test.salesdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestGetUPCOfHighestRevenueProduct {

	@Test
	public void testEmptySalesData() {
		SalesData store = new SalesData("store");
		String result = store.getUPCOfHighestRevenueProduct();
		assertEquals("", result);
	}

	@Test
	public void testOneProductInSalesData() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		store.addProduct(product1);

		String result = store.getUPCOfHighestRevenueProduct();
		assertEquals("1300000466", result);
	}

	@Test
	public void testMultipleProductsInSalesDataFirst() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.98, 3);
		store.addProduct(product1);

		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 11.97, 3);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 11.97, 3);
		store.addProduct(product3);

		String result = store.getUPCOfHighestRevenueProduct();
		assertEquals("1300000466", result);
	}
	
	@Test
	public void testMultipleProductsInSalesDataMiddle() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		store.addProduct(product1);

		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 11.98, 3);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 11.97, 3);
		store.addProduct(product3);

		String result = store.getUPCOfHighestRevenueProduct();
		assertEquals("1300000467", result);
	}
	
	@Test
	public void testMultipleProductsInSalesDataLast() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		store.addProduct(product1);

		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 11.97, 3);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 11.98, 3);
		store.addProduct(product3);

		String result = store.getUPCOfHighestRevenueProduct();
		assertEquals("1300000468", result);
	}

}
