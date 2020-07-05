package edu.westga.cs1302.sandbox.model;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Student> {
	
	@Override
	public int compare(Student stu1, Student stu2) {
		String name1 = stu1.getLastName();
		String name2 = stu2.getLastName();
		return name1.compareTo(name2);
	}
}
