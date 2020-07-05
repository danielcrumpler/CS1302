package edu.westga.cs1302.inheritance.model;

import java.awt.Color;

/**
 * Defines a rectangle
 * 
 * @author CS1302
 */
public class Rectangle extends Shape {

	public static final String WIDTH_MUST_BE_GREATER_THAN_ZERO = "width must be >0.";
	public static final String HEIGHT_MUST_BE_GREATER_THAN_ZERO = "height must be > 0.";
	private double height;
	private double width;

	/**
	 * Instantiates a new rectangle.
	 * 
	 * @precondition width > 0 && height > 0
	 * @postcondition getWidth() == width; getHeight() == height
	 *
	 * @param width  width of rectangle
	 * @param height height of rectangle
	 */
	public Rectangle(double width, double height) {
		super();

		if (width <= 0) {
			throw new IllegalArgumentException(WIDTH_MUST_BE_GREATER_THAN_ZERO);
		}

		if (height <= 0) {
			throw new IllegalArgumentException(HEIGHT_MUST_BE_GREATER_THAN_ZERO);
		}

		this.width = width;
		this.height = height;
	}

	/**
	 * Instantiates a new rectangle.
	 * 
	 * @precondition width > 0 && height > 0
	 * @postcondition getWidth() == width; getHeight() == height; getColor() ==
	 *                color && isFilled() == filled
	 *
	 * @param color  the color of the shape.
	 * @param filled Whether shape is filled or not.
	 * @param width the width of the shape.
	 * @param height the height of the shape.
	 */
	public Rectangle(double width, double height, Color color, boolean filled) {
		super(color, filled);

		if (width <= 0) {
			throw new IllegalArgumentException(WIDTH_MUST_BE_GREATER_THAN_ZERO);
		}

		if (height <= 0) {
			throw new IllegalArgumentException(HEIGHT_MUST_BE_GREATER_THAN_ZERO);
		}

		this.width = width;
		this.height = height;
	}

//	@Override
//	public double computeArea() {
//		return this.width * this.height;
//	}

	/**
	 * Gets the height.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the height
	 */
	public double getHeight() {
		return this.height;
	}

	/**
	 * Sets the height.
	 *
	 * @precondition height > 0
	 * @postcondition getHeight() == height
	 * 
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		if (height <= 0) {
			throw new IllegalArgumentException(HEIGHT_MUST_BE_GREATER_THAN_ZERO);
		}

		this.height = height;
	}

	/**
	 * Gets the width.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the width
	 */
	public double getWidth() {
		return this.width;
	}

	/**
	 * Sets the width.
	 * 
	 * @precondition width > 0
	 * @postcondition getWidth() == width
	 *
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		if (width <= 0) {
			throw new IllegalArgumentException(WIDTH_MUST_BE_GREATER_THAN_ZERO);
		}
		this.width = width;
	}

	@Override
	public String toString() {
		String output = "RECTANGLE " + super.toString();
		output += " Width: " + this.width + " Height: " + this.height;
		return output;
	}

}
