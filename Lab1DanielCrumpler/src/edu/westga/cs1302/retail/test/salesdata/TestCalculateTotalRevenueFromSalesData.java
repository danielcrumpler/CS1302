package edu.westga.cs1302.retail.test.salesdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestCalculateTotalRevenueFromSalesData {

	@Test
	public void testEmptySalesData() {
		SalesData store = new SalesData("store");
		double result = store.getTotalRevenueOfProductsInDataList();
		assertEquals(0.0, result);
	}

	@Test
	public void testOneProductInSalesData() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		store.addProduct(product1);

		double result = store.getTotalRevenueOfProductsInDataList();
		assertEquals(11.97, result);
	}

	@Test
	public void testMultipleProductsInSalesData() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		store.addProduct(product1);

		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 11.97, 3);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 11.97, 3);
		store.addProduct(product3);

		double result = store.getTotalRevenueOfProductsInDataList();
		assertEquals(35.91, result, 0.001);
	}

}

