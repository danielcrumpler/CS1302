package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestAddArrayList {

	@Test
	void testAddEmptyArrayListToSalesData() {
		ArrayList <Product> store = new ArrayList<Product>();
		SalesData salesData = new SalesData();
		salesData.addArrayList(store);
		assertEquals(0, salesData.size());
	}
	
	@Test
	void testAddProductFromArrayListToSalesData() {
		ArrayList <Product> store = new ArrayList<Product>();
		SalesData salesData = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		salesData.addArrayList(store);
		assertEquals(1, salesData.size());
	}
	
	@Test
	void testAddProductsFromArrayListToSalesData() {
		ArrayList <Product> store = new ArrayList<Product>();
		SalesData salesData = new SalesData();
		store.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		store.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		salesData.addArrayList(store);
		assertEquals(2, salesData.size());
	}

}
