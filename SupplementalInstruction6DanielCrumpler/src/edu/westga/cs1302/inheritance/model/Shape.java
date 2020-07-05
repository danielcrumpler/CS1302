package edu.westga.cs1302.inheritance.model;

import java.awt.Color;

/**
 * Defines a shape.
 * 
 * @author CS1302
 *
 */
public class Shape {

	private Color color;
	private boolean filled;

	/**
	 * Instantiates a new shape.
	 * 
	 * @precondition none
	 * @postcondition getColor() == Color.WHITE && isFilled() == false
	 * 
	 */
	public Shape() {
		this.color = Color.WHITE;
		this.filled = false;
	}

	/**
	 * Instantiates a new shape.
	 * 
	 * @precondition none
	 * @postcondition getColor() == color && isFilled() == filled
	 *
	 * @param color
	 *            the color of the shape
	 * @param filled
	 *            Whether shape is filled or not.
	 */
	public Shape(Color color, boolean filled) {
		this.color = color;
		this.filled = filled;
	}

//	/**
//	 * Computes the area of the shape
//	 * 
//	 * @precondition none
//	 * @postcondition none
//	 * 
//	 * @return The area of the shape
//	 */
//	public double computeArea() {
//		return 0;
//	}
	

	/**
	 * Gets the color.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Sets the color.
	 *
	 * @precondition none
	 * @postcondition getColor() == color
	 * 
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Checks if is filled.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if filled, false otherwise
	 */
	public boolean isFilled() {
		return this.filled;
	}

	/**
	 * Sets if shape if filled
	 *
	 * @precondition none
	 * @postcondition getFilled() == filled
	 * 
	 * @param filled
	 *            Whether shape is filled or not
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	@Override
	public String toString() {
		return "Shape [color=" + color + ", filled=" + filled + "]";
	}

}
