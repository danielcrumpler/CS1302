package edu.westga.cs1302.retail.test.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.test.TestingConstants;

public class TestConstruction {

	@Test
	void testValidConstruction() {
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		assertAll(() -> assertEquals("1300000466", product.getUpc()),
				() -> assertEquals("HEINZ KETCHUP 38 OZ", product.getDescription()),
				() -> assertEquals(11.97, product.getRevenue(), TestingConstants.DELTA),
				() -> assertEquals(3, product.getQuantitySold()));
	}

	@Test
	void testNullUpc() {
		IllegalArgumentException exc = assertThrows(IllegalArgumentException.class,
				() -> new Product(null, "HEINZ KETCHUP 38 OZ", 11.97, 3));
		assertEquals(Product.UPC_CANNOT_BE_NULL, exc.getMessage());
	}

	@Test
	void testEmptyUpc() {
		assertThrows(IllegalArgumentException.class, () -> new Product("", "HEINZ KETCHUP 38 OZ", 11.97, 3));
	}

	@Test
	void testNullDescription() {
		IllegalArgumentException exc = assertThrows(IllegalArgumentException.class,
				() -> new Product("1300000466", null, 11.97, 3));
		assertEquals(Product.DESCRIPTION_CANNOT_BE_NULL, exc.getMessage());
	}

	@Test
	void testEmptyDescription() {
		assertThrows(IllegalArgumentException.class, () -> new Product("1300000466", "", 11.97, 3));
	}

	@Test
	void testRevenueJustBelowLowerBound() {
		assertThrows(IllegalArgumentException.class, () -> new Product("1300000466", "HEINZ KETCHUP 38 OZ", -0.01, 3));
	}

	@Test
	void testRevenueAtLowerBound() {
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 0, 3);
		assertEquals(0, product.getRevenue(), TestingConstants.DELTA);
	}

	@Test
	void testRevenueJustAboveLowerBound() {
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 0.01, 3);
		assertEquals(0.01, product.getRevenue(), TestingConstants.DELTA);
	}

	@Test
	void testQuantitySoldJustBelowLowerBound() {
		assertThrows(IllegalArgumentException.class, () -> new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, -1));
	}

	@Test
	void testQuantitySoldAtLowerBound() {
		assertThrows(IllegalArgumentException.class, () -> new Product("1300000466", "HEINZ KETCHUP 38 OZ", 0, 0));
	}

	@Test
	void testQuantitySoldJustAboveLowerBound() {
		Product product = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 3.99, 1);
		assertEquals(1, product.getQuantitySold());
	}
}
