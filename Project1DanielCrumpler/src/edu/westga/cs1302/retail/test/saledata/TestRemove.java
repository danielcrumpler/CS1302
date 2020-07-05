package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Product;

class TestRemove {

	@Test
	void testRemoveNullFromEmptySalesData() {
		SalesData salesData = new SalesData();
		salesData.remove(null);
		assertEquals(0, salesData.size());
	}

	@Test
	void testRemoveNullFromNonemptySalesData() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		salesData.remove(null);
		assertEquals(1, salesData.size());
	}

	@Test
	void testRemoveNotexistingProductFromNotemptySalesData() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		salesData.add(productA);
		salesData.remove(productB);
		assertAll(() -> assertEquals(1, salesData.size()),
				() -> assertEquals(productA, salesData.findProduct("1300000466")));
	}

	@Test
	void testRemoveOnlyProductFromSalesDataWithOneProduct() {
		SalesData salesData = new SalesData();
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		salesData.add(product);
		salesData.remove(product);
		assertEquals(0, salesData.size());
	}

	@Test
	void testRemoveFirstProductFromSalesDataWithMultipleProducts() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		salesData.remove(productA);
		assertAll(() -> assertEquals(2, salesData.size()),
				() -> assertEquals(productB, salesData.findProduct("4300095531")),
				() -> assertEquals(productC, salesData.findProduct("1300000965")));
	}

	@Test
	void testRemoveMiddleProductFromSalesDataWithMultipleProducts() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		salesData.remove(productB);
		assertAll(() -> assertEquals(2, salesData.size()),
				() -> assertEquals(productA, salesData.findProduct("1300000466")),
				() -> assertEquals(productC, salesData.findProduct("1300000965")));
	}

	@Test
	void testLastProductFromSalesDataWithMultipleProducts() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		salesData.remove(productC);
		assertAll(() -> assertEquals(2, salesData.size()),
				() -> assertEquals(productA, salesData.findProduct("1300000466")),
				() -> assertEquals(productB, salesData.findProduct("4300095531")));
	}
}
