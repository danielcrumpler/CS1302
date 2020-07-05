package edu.westga.cs1302.inheritance.model;

import java.awt.Color;

/**
 * The Class Circle.
 * 
 * @author CS1302
 */
public class Circle extends Shape {

	private double radius;

	/**
	 * Instantiates a new circle.
	 * 
	 * @precondition none
	 * @postcondition getRadius() == 0.0 && getColor() == Color.WHITE && isFilled()
	 *                == false
	 */
	public Circle() {
		this.radius = 0.0;
	}

	/**
	 * Instantiates a new rectangle.
	 * 
	 * @precondition radius > 0
	 * @postcondition getRadius() == radius && getColor() == color && isFilled() ==
	 *                filled
	 *
	 * @param radius the radius of the circle.
	 * @param color  the color of the shape.
	 * @param filled Whether shape is filled or not.
	 */
	public Circle(double radius, Color color, boolean filled) {
		super(color, filled);
		if (radius <= 0) {
			throw new IllegalArgumentException("radius must be > 0.");
		}
		this.radius = radius;
	}

	/**
	 * Gets the radius.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the radius
	 */
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Sets the radius.
	 *
	 * @precondition radius > 0
	 * @postcondition getRadius() == radius
	 * 
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException("radius must be >0.");
		}
		this.radius = radius;
	}

	/**
	 * Calculates the area of the circle
	 * 
	 * @return the area of the circle
	 */
	public double getArea() {
		return this.radius * this.radius * Math.PI;
	}

	@Override
	public String toString() {
		return ("Circle with radius " + this.radius);
	}
}
