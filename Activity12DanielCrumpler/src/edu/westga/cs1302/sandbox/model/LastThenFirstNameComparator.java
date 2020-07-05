package edu.westga.cs1302.sandbox.model;

import java.util.Comparator;

public class LastThenFirstNameComparator implements Comparator<Student> {
	
	@Override
	public int compare(Student stu1, Student stu2) {
		String lastName1 = stu1.getLastName();
		String lastName2 = stu2.getLastName();
		if (lastName1.equalsIgnoreCase(lastName2)) {
			return stu1.getFirstName().compareTo(stu2.getFirstName());
		} else { 
			return lastName1.compareTo(lastName2);
		}
	}
}
