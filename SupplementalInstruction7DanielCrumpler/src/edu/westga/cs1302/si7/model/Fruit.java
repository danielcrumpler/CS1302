package edu.westga.cs1302.si7.model;

/**
 * This class will model a piece of fruit by keeping track of 
 * its unit price and weight
 *  
 * @author CS 1302
 */
public class Fruit implements Calcuable {
	private double unitPrice;
	private double weight;
	
	/**
	 * Creates a Fruit object by storing its unit price and weight
	 * 
	 * @param	unitPrice	The unit price of this fruit
	 * @param	weight	The weight of the fruit
	 * 
	 * @precondition:	unitPrice > 0
	 * @precondition:	weight > 0
	 * 
	 * @postcondition:	An Art object that is ageInYears years old
	 * 					with a popularity of popularity
	 * 
	 */
	public Fruit(double unitPrice, double weight) {
		if (unitPrice <= 0) {
			throw new IllegalArgumentException("Unit price must be greater than 0");
		}
		if (weight <= 0) {
			throw new IllegalArgumentException("Weight must be greater than 0");
		}
		this.unitPrice = unitPrice;
		this.weight = weight;
	}

	@Override
	public double calculate() {
		return this.unitPrice * this.weight;
	}

	@Override
	public String getDescription() {
		return this.weight + " pounds of some fruit";
	}
	
}
