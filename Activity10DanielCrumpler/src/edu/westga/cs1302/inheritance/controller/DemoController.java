package edu.westga.cs1302.inheritance.controller;

import edu.westga.cs1302.inheritance.model.Circle;
import edu.westga.cs1302.inheritance.model.Rectangle;
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
		Rectangle rect = new Rectangle(10, 5);
		System.out.println(rect);
		
		Circle circle = new Circle(10, Color.RED, true);
		System.out.println(circle);
		
	}
	
}
