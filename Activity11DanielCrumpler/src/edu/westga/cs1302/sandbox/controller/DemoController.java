package edu.westga.cs1302.sandbox.controller;

import java.util.ArrayList;

import edu.westga.cs1302.sandbox.model.Circle;
import edu.westga.cs1302.sandbox.model.Hexagon;
import edu.westga.cs1302.sandbox.model.Robot;
import edu.westga.cs1302.sandbox.model.ShapeOperations;
import edu.westga.cs1302.sandbox.model.Square;

/**
 * The Class DemoController.
 * 
 * @author CS 1302
 */
public class DemoController {
	
	/**
	 * Demos functionality
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void run() {
		// create some 2D shapes
		Circle bigCircle = new Circle(100.2);
		Circle smallCircle = new Circle(1.54);
		Hexagon aHexagon = new Hexagon(3);
		Square bigSquare = new Square(94.5);
		Square smallSquare = new Square(2.1);

		Robot aRobot = new Robot(2, 3, 4);

		// find their total area (clumsy way)
		double totalAreaClumsy = bigCircle.getArea() + smallCircle.getArea() + aHexagon.getArea() + bigSquare.getArea()
				+ smallSquare.getArea() + aRobot.getArea();

		// find their total area (elegant way) using an ArrayList of ShapeOperations
		ArrayList<ShapeOperations> shapeOps = new ArrayList<ShapeOperations>();
		shapeOps.add(bigCircle);
		shapeOps.add(smallCircle);
		shapeOps.add(aHexagon);
		shapeOps.add(bigSquare);
		shapeOps.add(smallSquare);
		shapeOps.add(aRobot);
		
		double totalAreaElegant = 0;
		for (ShapeOperations shapeOp : shapeOps) {
			totalAreaElegant += shapeOp.getArea();
		}

		// print out their total area
		System.out.println("Total area clumsy: " + totalAreaClumsy);
		System.out.println("Total area elegant: " + totalAreaElegant);
	}

}
