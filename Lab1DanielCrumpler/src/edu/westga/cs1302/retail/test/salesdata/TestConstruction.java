package edu.westga.cs1302.retail.test.salesdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;

public class TestConstruction {

	@Test
	void testValidConstruction() {
		SalesData salesData = new SalesData("StoreName1");
		assertAll(() -> assertEquals("StoreName1", salesData.getName()));
	}

	@Test
	void testNullSalesData() {
		IllegalArgumentException exc = assertThrows(IllegalArgumentException.class,
				() -> new SalesData(null));
		assertEquals(SalesData.STORE_NAME_CANNOT_BE_NULL, exc.getMessage());
	}

	@Test
	void testEmptyUpc() {
		assertThrows(IllegalArgumentException.class, () -> new SalesData(""));
	}
}
