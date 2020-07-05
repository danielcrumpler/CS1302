package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestFindProductsWithString {

	@Test
	void testFindNullUpcInEmptySalesData() {
		SalesData salesData = new SalesData();
		assertNull(salesData.findProductsWithString(null));
	}
	
	@Test
	void testFindUpcInEmptySalesData() {
		SalesData salesData = new SalesData();
		assertNull(salesData.findProductsWithString("1111111111"));
	}
	
	@Test
	void testMultipleProducstNoneWithUpc() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 3));
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 0.33, 57));
		ArrayList<Product> products = new ArrayList<Product>();
		assertEquals(products, salesData.findProductsWithString("1111111111"));
	}
	
	@Test
	void testOneProductWithUpc() {
		SalesData salesData = new SalesData();
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 3);
		salesData.add(product);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(product);
		assertEquals(products, salesData.findProductsWithString("1300000466"));
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
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productA);
		assertEquals(products, salesData.findProductsWithString("1300000466"));
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
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productC);
		assertEquals(products, salesData.findProductsWithString("1300000965"));
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
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productB);
		assertEquals(products, salesData.findProductsWithString("4300095531"));
	}

	@Test
	void testMultipleProducstNoneWithDescription() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 3));
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 0.33, 57));
		ArrayList<Product> products = new ArrayList<Product>();
		assertEquals(products, salesData.findProductsWithString("AN ITEM"));
	}
	
	@Test
	void testOneProductWithDescription() {
		SalesData salesData = new SalesData();
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 3);
		salesData.add(product);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(product);
		assertEquals(products, salesData.findProductsWithString("HEINZ KETCHUP 38 OZ"));
	}
	
	@Test
	void testMultipleProductsFirstWithDescription() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productA);
		assertEquals(products, salesData.findProductsWithString("HEINZ KETCHUP 38 OZ"));
	}
	
	@Test
	void testMultipleProductsLastWithDescription() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productC);
		assertEquals(products, salesData.findProductsWithString("JD HICKORY BBQ SAUCE 19OZ"));
	}
	
	@Test
	void testMultipleProductsMiddleWithDescription() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(productB);
		assertEquals(products, salesData.findProductsWithString( "KOOL AID ORANGE 2 QT"));
	}
}
