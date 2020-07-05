package edu.westga.cs1302.sandbox.model;

/**
 * The class Square
 * 
 * @author CS1302
 */
public class Square implements ShapeOperations {

	private double length;

	/**
	 * Instantiates a new square.
	 *
	 * @precondition length > 0
	 * @postcondition a square with the specified side length is created
	 * 
	 * @param length the length
	 */
	public Square(double length) {
		if (length <= 0) {
			throw new IllegalArgumentException("length must be > 0.");
		}
		this.length = length;
	}

	/**
	 * Gets the area of the Square.
	 * 
	 * @return the area
	 */
	@Override
	public double getArea() {
		return this.length * this.length;
	}

	/**
	 * Gets the perimeter of the Square.
	 * 
	 * @return the perimeter
	 */
	@Override
	public double getPerimeter() {
		return 4 * this.length;
	}
}
