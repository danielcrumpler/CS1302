package edu.westga.cs1302.si7.controller;

import java.util.ArrayList;
import java.util.Collections;

import edu.westga.cs1302.si7.model.AreaComparator;
import edu.westga.cs1302.si7.model.Carpet;
import edu.westga.cs1302.si7.model.Cart;
import edu.westga.cs1302.si7.model.Fruit;

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
		Cart aCart = new Cart();
		aCart.add(new Fruit(0.99, 3));
		aCart.add(new Carpet(5, 6, 10));
		aCart.add(new Fruit(2.99, 1));
		aCart.add(new Carpet(1, 3, 3));
		aCart.add(new Carpet(9, 2, 2));
		
		System.out.println("ITEMS IN CART: ");
		System.out.println(aCart.getDescription());
		
		System.out.println("TOTAL CART COST: " + aCart.calculate());
		
		System.out.println("CARPET DATA BY PRICE:");
		ArrayList<Carpet> carpets1 = new ArrayList<Carpet>();
		carpets1.addAll(aCart.getCarpets());
		Collections.sort(carpets1);
		for (Carpet currCarpet : carpets1) {
			System.out.println(currCarpet.calculate());
		}
		System.out.println("MIN: " + Collections.min(aCart.getCarpets()).calculate());
		System.out.println("MAX: " + Collections.max(aCart.getCarpets()).calculate());
		
		System.out.println("CARPET DATA BY SIZE");
		ArrayList<Carpet> carpets2 = new ArrayList<Carpet>();
		carpets2.addAll(aCart.getCarpets());
		Collections.sort(carpets2, new AreaComparator());
		for (Carpet currCarpet : carpets2) {
			System.out.println(currCarpet.getArea());
		}
		System.out.println("MIN: " + Collections.min(aCart.getCarpets()).getArea());
		System.out.println("MAX: " + Collections.max(aCart.getCarpets()).getArea());
	}

}
