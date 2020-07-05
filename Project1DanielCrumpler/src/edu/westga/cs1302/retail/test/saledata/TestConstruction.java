package edu.westga.cs1302.retail.test.saledata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.retail.model.SalesData;

class TestConstruction {
	@Test
	void testDefaultSAlesData() {
		SalesData salesData = new SalesData();
		assertEquals(0, salesData.size());
	}
}
