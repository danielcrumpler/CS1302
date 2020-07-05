package edu.westga.cs1302.sandbox.model;

import java.util.Comparator;

public class GradeComparator implements Comparator<Student> {
	
	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getGrade() > stu2.getGrade()){
			return 1;
		} else if (stu1.getGrade() < stu2.getGrade()) {
			return -1;
		} else {
			return 0;
		}
	}	
}
