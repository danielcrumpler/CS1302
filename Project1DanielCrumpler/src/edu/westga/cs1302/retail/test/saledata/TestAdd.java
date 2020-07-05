package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Product;

class TestAdd {

	@Test
	void testAddNullProduct() {
		SalesData salesData = new SalesData();
		assertThrows(IllegalArgumentException.class, () -> salesData.add(null));
	}
	
	@Test
	void testAddProductToEmptySalesData() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals(1, salesData.size());
	}
	
	@Test
	void testAddMultiplesProductsToSalesData() {
		SalesData store = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		store.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		assertEquals(2, store.size());
	}
	
	@Test
	void testAddMultiplesProductsToSalesDataSameUPCAndDescription() {
		SalesData store = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		store.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.00, 57));
		store.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.00, 57));
		assertAll(
			() -> assertEquals(2, store.size()),
			() -> assertEquals(36.00, store.findProduct("4300095531").getRevenue()));
		
	}
	
	@Test
	void testAddMultiplesProductsToSalesDataSameUPCDiffrentDescription() {
		SalesData store = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		store.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		store.add(new Product("4300095531", "KOOL AID ORANGE 1 QT", 18.81, 57));
		assertAll(
				() -> assertEquals(2, store.size()),
				() -> assertEquals(18.81, store.findProduct("4300095531").getRevenue()));
	}
}
