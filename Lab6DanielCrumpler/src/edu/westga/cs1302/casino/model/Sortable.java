package edu.westga.cs1302.casino.model;

import java.util.Comparator;

/**
 * The Interface Sortable.
 * 
 * @author CS 1302
 * @param <T> default type of objects
 */
public interface Sortable<T> {

	/**
	 * Sort the implementing collection using its natural order.
	 */
	void sort();

	/**
	 * The method is meant to sort a collection according to the passed-in
	 * Comparator for the generic type T.
	 * 
	 * @param comparator Comparator for the generic type T.
	 */
	void sort(Comparator<T> comparator);
}
