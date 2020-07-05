package edu.westga.cs1302.lengthconverter.model;

/**
 * Enum USLengthUnit.
 * 
 * @author CS 1302
 */
public enum USLengthUnit {
	
	IN(0.0254), 
	FT(0.3048), 
	YD(0.9144), 
	MI(1609.34);

	private final double meterConversionFactor;

	USLengthUnit(double factor) {
		this.meterConversionFactor = factor;
	}

	/**
	 * returns the length in this unit scaled to meter
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param length the length in this unit
	 * @return the length scaled to meter
	 */
	public double scaleToMeter(double length) {
		return this.meterConversionFactor * length;
	}
}
