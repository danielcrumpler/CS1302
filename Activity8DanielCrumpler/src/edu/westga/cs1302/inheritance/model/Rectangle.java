package edu.westga.cs1302.inheritance.model;

import java.awt.Color;

/**
 * The Class Rectangle.
 * 
 * @author CS1302
 */
public class Rectangle extends Shape {

	private double height;
	private double width;

	/**
	 * Instantiates a new rectangle.
	 * 
	 * @precondition width > 0 && height > 0
	 * @postcondition getColor() == Color.WHITE && isFilled() == false && getWidth() == width && getHeight() == height
	 *
	 * @param width  width of rectangle
	 * @param height height of rectangle
	 */
	public Rectangle(double width, double height) {
		super();

		if (width <= 0) {
			throw new IllegalArgumentException("width must be > 0.");
		}

		if (height <= 0) {
			throw new IllegalArgumentException("height must be > 0.");
		}

		this.width = width;
		this.height = height;
	}

	/**
	 * Instantiates a new rectangle.
	 * 
	 * @precondition width > 0 && height > 0
	 * @postcondition getColor() == color && isFilled() == filled
	 *
	 * @param color  the color of the shape.
	 * @param filled Whether shape is filled or not.
	 * @param width the width of the shape.
	 * @param height the height of the shape.
	 */
	public Rectangle(double width, double height, Color color, boolean filled) {
		super(color, filled);

		if (width <= 0) {
			throw new IllegalArgumentException("width must be > 0 .");
		}

		if (height <= 0) {
			throw new IllegalArgumentException("height must be > 0 .");
		}

		this.width = width;
		this.height = height;
	}

	/**
	 * Compute area.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the area
	 */
	public double computeArea() {
		return this.width * this.height;
	}

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
			throw new IllegalArgumentException("height must be >0.");
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
			throw new IllegalArgumentException("width must be >0.");
		}
		this.width = width;
	}

	@Override
	public String toString() {
		String output = super.toString();
		output += " Width: " + this.width + " Height: " + this.height;
		return output;
	}

}
