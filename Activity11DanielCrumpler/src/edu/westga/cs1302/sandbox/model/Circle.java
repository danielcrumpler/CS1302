package edu.westga.cs1302.sandbox.model;

/**
 * The class Circle
 * 
 * @author CS1302
 */
public class Circle implements ShapeOperations {

	private double radius;

	/**
	 * Creates a new Circle with the given radius
	 * 
	 * @precondition: radius > 0
	 * @postcondition: a circle with the given radius is created.
	 * 
	 * @param radius the radius of the circle. Must be greater than zero.
	 */
	public Circle(double radius) {
		if (radius <= 0.0) {
			throw new IllegalArgumentException("A Circle's radius must be greater than zero.");
		}

		this.radius = radius;
	}

	/**
	 * Gets the area of the Circle.
	 * 
	 * @return the area
	 */
	@Override
	public double getArea() {
		return Math.PI * this.radius * this.radius;
	}

	/**
	 * Gets the perimeter of the circle.
	 * 
	 * @return the area
	 */
	@Override
	public double getPerimeter() {
		return 2 * Math.PI * this.radius;
	}

}
