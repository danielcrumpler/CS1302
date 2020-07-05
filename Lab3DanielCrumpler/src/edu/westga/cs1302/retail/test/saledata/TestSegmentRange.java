package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.model.SalesData;

class TestSegmentRange {

	@Test
	void testSegmentLessThenZero() {
		SalesData salesData = new SalesData();
		assertThrows(IllegalArgumentException.class, () -> salesData.segmentRangeForProductRevenue(-1.0));
	}

	@Test
	void testSegment() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 18.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		int[] result = salesData.segmentRangeForProductRevenue(25.00);
		assertEquals(result[0], 3);
	}
	
	@Test
	void testSegmentTwo() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 11.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 180.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		int[] result = salesData.segmentRangeForProductRevenue(25.00);
		assertEquals(result[0], 2);
	}
	
	@Test
	void testSegmentThree() {
		SalesData salesData = new SalesData();
		Product productA = new Product("1300000466", "HEINZ KETCHUP 38 OZ", 101.97, 3);
		Product productB = new Product("4300095531", "KOOL AID ORANGE 2 QT", 108.81, 57);
		Product productC = new Product("1300000965", "JD HICKORY BBQ SAUCE 19OZ", 2.39, 1);
		salesData.add(productA);
		salesData.add(productB);
		salesData.add(productC);
		int[] result = salesData.segmentRangeForProductRevenue(25.00);
		assertEquals(result[0], 1);
	}
}
