package edu.westga.cs1302.sandbox.model;

/**
 * The Class Hexagon
 * 
 * @author CS1302
 */
public class Hexagon implements ShapeOperations {

	private double side;

	/**
	 * Instantiates a new hexagon.
	 * 
	 * @precondition none
	 * @postcondition a hexagon with side length 1 is created
	 */
	public Hexagon() {
		this.side = 1;
	}

	/**
	 * Instantiates a new hexagon.
	 *
	 * @precondition side > 0
	 * @postcondition a hexagon with the specified side length is created
	 * 
	 * @param side the side
	 */
	public Hexagon(double side) {
		if (side <= 0) {
			throw new IllegalArgumentException("side must be > 0.");
		}
		this.side = side;
	}

	/**
	 * Gets the area of the hexagon.
	 * 
	 * @return the area
	 */
	@Override
	public double getArea() {
		return 1.5 * Math.sqrt(3) * side * side;
	}

	/**
	 * Gets the perimeter of the hexagon.
	 * 
	 * @return the area
	 */
	@Override
	public double getPerimeter() {
		return 6 * side;
	}

}
