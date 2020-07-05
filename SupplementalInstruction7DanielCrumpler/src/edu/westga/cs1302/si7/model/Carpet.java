package edu.westga.cs1302.si7.model;

/**
 * This class models a piece of carpet by keeping track of its
 * unit price, length, and width
 *  
 * @author CS 1302
 */
public class Carpet implements Calcuable, Comparable<Carpet> {
	private double unitPrice;
	private double length;
	private double width;
	
	/**
	 * Creates a piece of carpet with the given unit price, length, and width
	 * 
	 * @param	unitPrice	The unit price of this carpet
	 * @param	length		The length of this carpet
	 * @param	width		The width of this carpet
	 * 
	 * @precondition:	unit price > 0
	 * @precondition:	length > 0
	 * @precondition:	width > 0
	 * 
	 * @postcondition:	A piece of carpet with given unit price, length, and width
	 * 
	 */
	public Carpet(double unitPrice, double length, double width) {
		if (unitPrice <= 0.0) {
			throw new IllegalArgumentException("Unit price must be greater than 0");
		}
		if (length <= 0.0) {
			throw new IllegalArgumentException("The length must be greater than 0");
		}
		if (width <= 0.0) {
			throw new IllegalArgumentException("The width must be greater than 0");
		}
		this.unitPrice = unitPrice;
		this.length = length;
		this.width = width;
	}
	
	public double getArea() {
		return this.length * this.width;
	}

	@Override
	public double calculate() {
		double area = this.length * this.width;
		return this.unitPrice * area;
	}

	@Override
	public String getDescription() {
		return "A " + this.length + " by " + this.width + " piece of carpet";
	}
	
	@Override
	public int compareTo(Carpet otherCarpet) {
		if (this.calculate() > otherCarpet.calculate()) {
			return 1;
		} else if (this.calculate() < otherCarpet.calculate()) {
			return -1;
		} else {
			return 0;
		}
	}

}
