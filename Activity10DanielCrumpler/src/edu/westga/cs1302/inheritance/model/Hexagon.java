package edu.westga.cs1302.inheritance.model;

import java.awt.Color;
import java.lang.Math;

/**
 * The class Hexagon
 * @author csuser
 *
 */
public class Hexagon extends Shape {

	private double side;

	/**
	 * Instantiates a new rectangle.
	 * 
	 * @precondition width > 0 && side > 0
	 *
	 * @param width  width of rectangle
	 * @param side side of rectangle
	 */
	public Hexagon(double side) {
		super();

		if (side <= 0) {
			throw new IllegalArgumentException("side must be >0.");
		}

		this.side = side;
	}

	/**
	 * Instantiates a new Hexagon.
	 * 
	 * @precondition side > 0
	 * @postcondition getColor() == color && isFilled() == filled
	 *
	 * @param color  the color of the shape
	 * @param filled Whether shape is filled or not.
	 */
	public Hexagon(double side, Color color, boolean filled) {
		super(color, filled);

		if (side <= 0) {
			throw new IllegalArgumentException("side must be >0.");
		}

		this.side = side;
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
		return ((3 * Math.sqrt(3)) / 2) * (this.side * this.side);
	}

	/**
	 * Gets the side.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the side
	 */
	public double getSide() {
		return this.side;
	}

	/**
	 * Sets the side.
	 *
	 * @precondition side > 0
	 * @postcondition getSide() == side
	 * 
	 * @param side the side to set
	 */
	public void setSide(double side) {
		if (side <= 0) {
			throw new IllegalArgumentException("side must be >0.");
		}

		this.side = side;
	}

	@Override
	public String toString() {
		String output = super.toString();
		output += " Side: " + this.side;
		return output;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double computePerimeter() {
		return this.side * 6;
	}

}
