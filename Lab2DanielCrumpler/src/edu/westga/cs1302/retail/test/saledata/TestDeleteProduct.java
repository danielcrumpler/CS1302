package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestDeleteProduct {

	@Test
	public void testDeleteOneProductWithOneInDataSet() {
		SalesData store = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals(1, store.size());
		Product product = store.findProduct("1300000466");
		store.deleteProduct(product);
		assertEquals(0, store.size());
	}
	
	@Test
	public void testDeleteOneProductWithMultipleInDataSet() {
		SalesData store = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		store.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		assertEquals(2, store.size());
		Product product = store.findProduct("1300000466");
		store.deleteProduct(product);
		assertEquals(1, store.size());
	}
	
	@Test
	public void testDeleteMultipleProductsWithMultipleInDataSet() {
		SalesData store = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		store.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		assertEquals(2, store.size());
		Product product1 = store.findProduct("1300000466");
		store.deleteProduct(product1);
		Product product2 = store.findProduct("4300095531");
		store.deleteProduct(product2);
		assertEquals(0, store.size());
	}

}
