package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;
import edu.westga.cs1302.retail.model.Product;

class TestAddAll {

	@Test
	void testAddNullProductList() {
		SalesData salesData = new SalesData();
		assertThrows(IllegalArgumentException.class, () -> salesData.addAll(null));
	}

	@Test
	void testAddEmptyProductListToEmptySalesData() {
		SalesData salesData = new SalesData();
		ArrayList<Product> products = new ArrayList<Product>();
		salesData.addAll(products);
		assertEquals(0, salesData.size());
	}

	@Test
	void testAddEmptyProductListToSalesData() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		ArrayList<Product> products = new ArrayList<Product>();
		salesData.addAll(products);
		assertEquals(1, salesData.size());
	}

	@Test
	void testAddProductListWithOneProductsToEmptySalesData() {
		SalesData salesData = new SalesData();
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		salesData.addAll(products);
		assertEquals(1, salesData.size());
	}

	@Test
	void testAddProductListWithOneProductsToSalesData() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		salesData.addAll(products);
		assertEquals(2, salesData.size());
	}
	
	@Test
	void testAddProductListWithMultipleProductsToEmptySalesData() {
		SalesData salesData = new SalesData();
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		products.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		salesData.addAll(products);
		assertEquals(2, salesData.size());
	}

	@Test
	void testAddProductListWithMultipleProductsToSalesData() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productB);
		products.add(productC);
		salesData.addAll(products);
		assertAll(() -> assertEquals(3, salesData.size()),
				() -> assertEquals(productA, salesData.findProduct("1300000466")),
				() -> assertEquals(productB, salesData.findProduct("4300095531")),
				() -> assertEquals(productC, salesData.findProduct("1300000965")));
	}
	
	@Test
	void testAddProductListWithMultipleProductsToSalesDataWithMatchingUPCandDescription() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.00, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productB);
		products.add(productC);
		salesData.addAll(products);
		assertAll(() -> assertEquals(3, salesData.size()),
				() -> assertEquals(productA, salesData.findProduct("1300000466")),
				() -> assertEquals(productB, salesData.findProduct("4300095531")),
				() -> assertEquals(36.00, salesData.findProduct("4300095531").getRevenue()),
				() -> assertEquals(productC, salesData.findProduct("1300000965")));
	}
	
	@Test
	void testAddProductListWithMultipleProductsToSalesDataWithOnlyMatchingUPC() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.00, 57);
		Product productB2 = new Product("4300095531", "KOOL AID ORANGE 1 QT", 18.00, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productB2);
		products.add(productC);
		salesData.addAll(products);
		assertAll(() -> assertEquals(3, salesData.size()),
				() -> assertEquals(productA, salesData.findProduct("1300000466")),
				() -> assertEquals(productB, salesData.findProduct("4300095531")),
				() -> assertEquals(18.00, salesData.findProduct("4300095531").getRevenue()),
				() -> assertEquals(productC, salesData.findProduct("1300000965")));
	}
}
