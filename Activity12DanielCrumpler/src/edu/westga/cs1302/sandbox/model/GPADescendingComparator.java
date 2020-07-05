package edu.westga.cs1302.sandbox.model;

import java.util.Comparator;

public class GPADescendingComparator implements Comparator<Student> {
	
	@Override
	public int compare(Student stu1, Student stu2) {
		Double stu2GPA = stu2.getGPA();
		return stu2GPA.compareTo(stu1.getGPA());
	}
} 
