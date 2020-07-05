package edu.westga.cs1302.retail.test.salesdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestAddProductToSalesData {

	@Test
	public void testNullProductToSalesData() {
		SalesData store = new SalesData("store");

		assertThrows(IllegalArgumentException.class, () -> {
			store.addProduct(null);
		});
	}
	
	@Test
	public void testAddProductToSalesData() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		store.addProduct(product1);
		
		assertEquals(1, store.getNumberOfProductsInDataList());
	}
	
	@Test
	public void testAddMultipleProductToSalesData() {
		SalesData store = new SalesData("store");

		Product product1 = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		store.addProduct(product1);
		
		Product product2 = new Product("1300000467", "HEINZ MUSTARD 38 OZ", 11.97, 3);
		store.addProduct(product2);

		Product product3 = new Product("1300000468", "HEINZ RELISH 38 OZ", 11.97, 3);
		store.addProduct(product3);
		
		assertEquals(3, store.getNumberOfProductsInDataList());
	}
}
