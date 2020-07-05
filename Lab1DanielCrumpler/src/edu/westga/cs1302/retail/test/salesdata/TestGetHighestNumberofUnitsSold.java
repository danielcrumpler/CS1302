package edu.westga.cs1302.retail.test.salesdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;


class TestGetHighestNumberofUnitsSold {

	@Test
	void testWhenNoProducts() {
		SalesData store = new SalesData("store");

		int result = store.getHighestQuantityofUnitsSold(3.10, 5.01);

		assertEquals(0, result);
	}

	@Test
	void testWhenMinGreaterMaxUnitPrice() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 11);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(10.00, 5.00);

		assertEquals(0, result);
	}
	
	@Test
	void testWhenOneProductWithUnitPriceBelowLowThresholdBoundary() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 11);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(0, result);
	}

	@Test
	void testWhenOneProductWithUnitPriceAtLowThresholdBoundary() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 10);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(10, result);
	}

	@Test
	void testWhenOneProductWithUnitPriceJustAboveLowThresholdBoundary() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 9);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(9, result);
	}

	@Test
	void testWhenOneProductWithUnitPriceAboveHighThresholdBoundary() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 4);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(0, result);
	}

	@Test
	void testWhenOneProductWithUnitPriceAtHighThresholdBoundary() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 5);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(5, result);
	}

	@Test
	void testWhenOneProductWithUnitPriceJustBelowHighThresholdBoundary() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 6);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(6, result);
	}

	@Test
	void testWhenLowAndHighBoundsEqual() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 8);
		store.addProduct(product1);

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(8, result);
	}

	@Test
	void testMultipleProductsNoneWithinRange() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 3);
		store.addProduct(product1);

		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 50.00, 4);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 50.00, 11);
		store.addProduct(product3);;

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(0, result);
	}

	@Test
	void testMultipleProductsWithSomeWithinRange() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 5);
		store.addProduct(product1);

		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 50.00, 6);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 50.00, 11);
		store.addProduct(product3);;

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(6, result);
	}

	@Test
	void testMultipleProductsWithAllWithinRange() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 50.00, 6);
		store.addProduct(product1);

		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 50.00, 7);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 50.00, 9);
		store.addProduct(product3);;

		int result = store.getHighestQuantityofUnitsSold(5.00, 10.00);

		assertEquals(9, result);
	}
}
