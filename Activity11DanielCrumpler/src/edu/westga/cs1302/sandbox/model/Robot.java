package edu.westga.cs1302.sandbox.model;

/**
 * The class Robot
 * 
 * @author CS1302
 */
public class Robot implements ShapeOperations {

	private Square head;
	private Square body;
	private Square bottom;

	/**
	 * Creates a new Robot with the given lengths of its Square objects
	 * 
	 * @precondition headLength > 0 && bodyLength > 0 && bottomLength > 0
	 * @postcondition a robot object is created.
	 * 
	 * @param headLength   the length of the head
	 * @param bodyLength   the length of the body
	 * @param bottomLength the length of the bottom
	 */
	public Robot(double headLength, double bodyLength, double bottomLength) {
		if (headLength <= 0.0) {
			throw new IllegalArgumentException("headLength must be greater than zero");
		}
		if (bodyLength <= 0.0) {
			throw new IllegalArgumentException("bodyLength must be greater than zero");
		}
		if (bottomLength <= 0.0) {
			throw new IllegalArgumentException("bottomLength must be greater than zero");
		}

		this.head = new Square(headLength);
		this.body = new Square(bodyLength);
		this.bottom = new Square(bottomLength);
	}

	/**
	 * Gets the area of the Robot.
	 * 
	 * @return the area
	 */
	@Override
	public double getArea() {
		return this.head.getArea() + this.body.getArea() + this.bottom.getArea();
	}

	/**
	 * Gets the perimeter of the Robot.
	 * 
	 * @return the perimeter
	 */
	@Override
	public double getPerimeter() {
		return this.head.getPerimeter() + this.body.getPerimeter() + this.bottom.getPerimeter();
	}

}
