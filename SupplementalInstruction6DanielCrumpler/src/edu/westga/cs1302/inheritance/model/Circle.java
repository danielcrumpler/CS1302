package edu.westga.cs1302.inheritance.model;

import java.awt.Color;

/**
 * Defines a circle
 * 
 * @author CS1302
 *
 */
public class Circle extends Shape {

	public static final String RADIUS_MUST_BE_GREATER_THAN_ZERO = "radius must be > 0.";
	private int radius;

	/**
	 * Instantiates a new circle.
	 * 
	 * @precondition none
	 * @postcondition getRadius() == radius
	 */
	public Circle() {
		this.radius = 0;
	}

	/**
	 * Instantiates a new circle.
	 *
	 * @precondition radius > 0
	 * @postcondition getRadius() == radius; getColor()==Color.WHITE; isFilled() ==
	 *                false
	 * 
	 * @param radius
	 *            radius of circle
	 */
	public Circle(int radius) {
		super();

		if (radius <= 0) {
			throw new IllegalArgumentException(RADIUS_MUST_BE_GREATER_THAN_ZERO);
		}

		this.radius = radius;
	}

	/**
	 * Instantiates a new circle.
	 *
	 * @precondition radius > 0
	 * @postcondition getRadius() == radius; getColor()==color; isFilled() == filled
	 * 
	 * @param radius
	 *            radius of circle
	 * @param color
	 *            Color to make circle
	 * @param filled
	 *            Whether circle is filled or not
	 */
	public Circle(int radius, Color color, boolean filled) {
		super(color, filled);

		if (radius <= 0) {
			throw new IllegalArgumentException(RADIUS_MUST_BE_GREATER_THAN_ZERO);
		}

		this.radius = radius;
	}

	/**
	 * Calculates the area of a circle
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the computed area of the circle
	 */
	public double computeArea() {
		return Math.PI * this.radius * this.radius;
	}

	/**
	 * Gets the radius
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the radius
	 */
	public int getRadius() {
		return this.radius;
	}

	/**
	 * Sets the radius
	 * 
	 * @precondition radius > 0
	 * @postcondition none
	 * 
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(int radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException(RADIUS_MUST_BE_GREATER_THAN_ZERO);
		}

		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return "Circle with radius " + this.radius;
	}
}
