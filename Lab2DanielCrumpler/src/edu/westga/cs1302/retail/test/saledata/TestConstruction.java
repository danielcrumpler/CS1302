package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;

class TestConstruction {
	@Test
	void testDefaultSalesData() {
		SalesData salesData = new SalesData();

		assertAll(() -> assertEquals(0, salesData.size()), () -> assertNull(salesData.getStoreName()));
	}

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, () -> new SalesData(null));
	}
	
	@Test
	void testEmptyName() {
		assertThrows(IllegalArgumentException.class, () -> new SalesData(""));
	}
	
	@Test
	void testValidName() {
		SalesData salesData = new SalesData("Wolfie's Test Store");
		
		assertAll(() -> assertEquals(0, salesData.size()), () -> assertEquals("Wolfie's Test Store", salesData.getStoreName()));
	}

}
