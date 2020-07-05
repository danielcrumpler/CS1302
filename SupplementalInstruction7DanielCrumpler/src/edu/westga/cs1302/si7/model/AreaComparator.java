package edu.westga.cs1302.si7.model;

import java.util.Comparator;

public class AreaComparator implements Comparator<Carpet>{

	@Override
	public int compare(Carpet carpet1, Carpet carpet2) {
		if (carpet1.getArea() > carpet2.getArea()){
			return 1;
		} else if (carpet1.getArea() < carpet2.getArea()) {
			return -1;
		} else {
			return 0;
		}
	}
}
