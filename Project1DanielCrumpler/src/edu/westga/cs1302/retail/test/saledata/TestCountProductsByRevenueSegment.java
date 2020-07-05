package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestCountProductsByRevenueSegment {

	@Test
	void testNegativeSegmentRange() {
		SalesData salesData = new SalesData();
		assertThrows(IllegalArgumentException.class, () -> salesData.countProductsByRevenueSegment(-10));
	}

	@Test
	void testSegmentRangeJustBelowLowerBound() {
		SalesData salesData = new SalesData();
		assertThrows(IllegalArgumentException.class, () -> salesData.countProductsByRevenueSegment(0.009));
	}

	@Test
	void testSegmentRangeAtLowerBound() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1234", "SINGLE GUMMI BEAR", 0.02, 2));
		salesData.add(new Product("0000", "FREEBIE", 0, 131));
		int[] counts = salesData.countProductsByRevenueSegment(0.01);
		assertAll(() -> assertEquals(2, counts.length), () -> assertEquals(1, counts[0]),
				() -> assertEquals(1, counts[1]));
	}

	@Test
	void testOneSegmnent() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		salesData.add(new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1));
		int[] counts = salesData.countProductsByRevenueSegment(20.00);
		assertAll(() -> assertEquals(1, counts.length), () -> assertEquals(3, counts[0]));
	}

	@Test
	void testAllProductsInLastSegmnent() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		int[] counts = salesData.countProductsByRevenueSegment(10.00);
		assertAll(() -> assertEquals(2, counts.length), () -> assertEquals(0, counts[0]),
				() -> assertEquals(2, counts[1]));
	}

	@Test
	void testRevenuesAtUpperSegmentBound() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("10", "10 GUMMI BEAR PACK", 1.00, 1));
		salesData.add(new Product("20", "20 GUMMI BEAR PACK", 2.00, 1));
		salesData.add(new Product("30", "30 GUMMI BEAR PACK", 3.00, 1));
		salesData.add(new Product("40", "50 JELLY BEAN PACK", 3.00, 1));
		int[] counts = salesData.countProductsByRevenueSegment(1.00);
		assertAll(() -> assertEquals(3, counts.length), () -> assertEquals(1, counts[0]),
				() -> assertEquals(1, counts[1]), () -> assertEquals(2, counts[2]));
	}

	@Test
	void testRevenuesAtLowerSegmentBound() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("00", "FREEBIE", 0, 131));
		salesData.add(new Product("10", "10 GUMMI BEAR PACK", 1.01, 1));
		salesData.add(new Product("20", "20 GUMMI BEAR PACK", 2.01, 1));
		salesData.add(new Product("40", "50 JELLY BEAN PACK", 2.01, 1));
		int[] counts = salesData.countProductsByRevenueSegment(1.00);
		assertAll(() -> assertEquals(3, counts.length), () -> assertEquals(1, counts[0]),
				() -> assertEquals(1, counts[1]), () -> assertEquals(2, counts[2]));
	}

	@Test
	void testMultipleProductsInMultipleSegmnent() {
		SalesData salesData = new SalesData();
		salesData.add(new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3));
		salesData.add(new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57));
		salesData.add(new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1));
		salesData.add(new Product("85087100606", "2 SISTERS FIESTA SALSA 16 OZ", 17.94, 6));
		salesData.add(new Product("82927445418", "MEOW MIX ORIGINAL 18 OZ", 2.29, 1));
		salesData.add(new Product("81032601292", "FIORA 3PLY PAPER TOWEL 6 RLL", 19.47, 3));
		int[] counts = salesData.countProductsByRevenueSegment(5.00);
		assertAll(() -> assertEquals(4, counts.length), () -> assertEquals(2, counts[0]),
				() -> assertEquals(0, counts[1]), () -> assertEquals(1, counts[2]), 
				() -> assertEquals(3, counts[3]));
	}
}
