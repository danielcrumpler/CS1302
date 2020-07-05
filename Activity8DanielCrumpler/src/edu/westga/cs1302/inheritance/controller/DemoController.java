package edu.westga.cs1302.inheritance.controller;

import java.awt.Color;

import edu.westga.cs1302.inheritance.model.Circle;
import edu.westga.cs1302.inheritance.model.Rectangle;

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
		Rectangle rect = new Rectangle(10, 5);
		System.out.println(rect);
		
		Circle circle = new Circle(5.0, Color.BLUE, false);
		System.out.println(circle);
	}
}
