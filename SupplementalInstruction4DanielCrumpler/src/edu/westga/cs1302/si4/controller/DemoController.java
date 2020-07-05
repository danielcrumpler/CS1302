package edu.westga.cs1302.si4.controller;

import java.util.Random;
import java.util.Scanner;

/**
 * The Class DemoController.
 * 
 * @author CS1302
 */
public class DemoController {

	/**
	 * Demos functionality
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void run() {
		int[] values = this.createArray();
		this.printArray(values);
		this.sortArray(values);
		this.printArray(values);
	}

	/**
	 * creates and array with random nonnegative values less than 1000
	 * 
	 * @return an array with random values
	 */
	private int[] createArray() {
		int size = this.promptUserForArraySize();
		
		int[] values = new int[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			values[i] = rand.nextInt(1000);
		}
		return values;
	}

	/**
	 * sorts the passed in array
	 * 
	 * @param values the array to be sorted
	 */
	private void sortArray(int[] values) {
		for (int i = 1; i < values.length; i++) {
			for (int j = i; j > 0; j--) {
				if (values[j] < values [j - 1]) {
					int temp = values[j];
					values[j] = values[j - 1];
					values[j - 1] = temp;
			     }
			}
		}
	}
	
	/**
	 * prompts the user to enter an array size until a valid size has been entered
	 * and then returns the value
	 * 
	 * @return a positive integer value
	 */
	private int promptUserForArraySize() {
		int size = 0;
		try (Scanner input = new Scanner(System.in)) {
			while (size <= 0) {
				System.out.print("Enter an array size > 0: ");
				String line = input.nextLine();
				try {
					size = Integer.parseInt(line);
				} catch (NumberFormatException e) {
					System.out.println("Not a number number");
				}
			}
		}
		return size;
	}
	
	/**
	 * prints the content of the specified array
	 * 
	 * @param values the array to be printed
	 */
	private void printArray(int[] values) {
		System.out.print("Array content: ");
		for (int value : values) {
			System.out.print(" " + value);
		}
		System.out.println();
	}
}
