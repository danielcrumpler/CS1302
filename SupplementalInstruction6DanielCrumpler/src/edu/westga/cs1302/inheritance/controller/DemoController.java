package edu.westga.cs1302.inheritance.controller;

import java.util.ArrayList;

import edu.westga.cs1302.inheritance.model.Circle;
import edu.westga.cs1302.inheritance.model.Rectangle;
import edu.westga.cs1302.inheritance.model.Shape;
import java.awt.Color;

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
		Shape myShape = new Rectangle(5, 8, Color.RED, false);
		// System.out.println("Width: " + myShape.getWidth());
		//System.out.println("Area: " + myShape.computeArea());

		Shape shape = new Shape(Color.BLUE, false);
		System.out.println(shape);

		Rectangle rect = new Rectangle(10, 5);
		System.out.println(rect);

		Circle circle = new Circle(10, Color.RED, true);
		System.out.println(circle);

		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(shape);
		shapes.add(rect);
		shapes.add(circle);
//
//		for (Shape currShape : shapes) {
//			System.out.println(currShape);
//			System.out.println("Area: " + currShape.computeArea());
//		}
		


		
		//Polymorphism
//		Shape circleGreen = new Circle(5, Color.GREEN, true);
//		Circle circleBlue = new Circle(5, Color.BLUE, true);
//		circleGreen.setFilled(false); 
//		circleBlue.setFilled(false); 
//
//		double areaGreen = circleGreen.computeArea(); 
//		double areaBlue = circleBlue.computeArea(); 
//
//		int radiusGreen = circleGreen.getRadius();
//		int radiusBlue = circleBlue.getRadius(); 

		
		
	}

}
