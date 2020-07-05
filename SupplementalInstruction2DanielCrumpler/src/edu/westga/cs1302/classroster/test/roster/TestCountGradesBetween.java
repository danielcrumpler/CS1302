package edu.westga.cs1302.classroster.test.roster;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.classroster.model.Roster;
import edu.westga.cs1302.classroster.model.Student;

class TestCountGradesBetween {

	@Test
	void testWhenNoStudents() {
		Roster roster = new Roster();

		int result = roster.countGradesBetween(80, 90);

		assertEquals(0, result);
	}

	@Test
	void testWhenMinGreaterMaxGrade() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 80);
		roster.add(student1);

		int result = roster.countGradesBetween(90, 80);

		assertEquals(0, result);
	}
	
	@Test
	void testWhenOneStudentWithGradeBelowLowThresholdBoundary() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 79);
		roster.add(student1);

		int result = roster.countGradesBetween(80, 90);

		assertEquals(0, result);
	}

	@Test
	void testWhenOneStudentWithGradeAtLowThresholdBoundary() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 80);
		roster.add(student1);

		int result = roster.countGradesBetween(80, 90);

		assertEquals(1, result);
	}

	@Test
	void testWhenOneStudentWithGradeJustAboveLowThresholdBoundary() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 81);
		roster.add(student1);

		int result = roster.countGradesBetween(80, 90);

		assertEquals(1, result);
	}

	@Test
	void testWhenOneStudentWithGradeAboveHighThresholdBoundary() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 91);
		roster.add(student1);

		int result = roster.countGradesBetween(80, 90);

		assertEquals(0, result);
	}

	@Test
	void testWhenOneStudentWithGradeAtHighThresholdBoundary() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 90);
		roster.add(student1);

		int result = roster.countGradesBetween(80, 90);

		assertEquals(1, result);
	}

	@Test
	void testWhenOneStudentWithGradeJustBelowHighThresholdBoundary() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 89);
		roster.add(student1);

		int result = roster.countGradesBetween(80, 90);

		assertEquals(1, result);
	}

	@Test
	void testWhenLowAndHighBoundsEqual() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 80);
		roster.add(student1);

		int result = roster.countGradesBetween(80, 80);

		assertEquals(1, result);
	}

	@Test
	void testMultipleStudentsNoneWithinRange() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 88);
		Student student2 = new Student("Daffy Duck", 71);
		Student student3 = new Student("Elmer Fudd", 85);

		roster.add(student1);
		roster.add(student2);
		roster.add(student3);

		int result = roster.countGradesBetween(90, 100);

		assertEquals(0, result);
	}

	@Test
	void testMultipleStudentsWithSomeWithinRange() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 91);
		Student student2 = new Student("Daffy Duck", 71);
		Student student3 = new Student("Elmer Fudd", 85);

		roster.add(student1);
		roster.add(student2);
		roster.add(student3);

		int result = roster.countGradesBetween(70, 89);

		assertEquals(2, result);
	}

	@Test
	void testMultipleStudentsWithAllWithinRange() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 91);
		Student student2 = new Student("Daffy Duck", 71);
		Student student3 = new Student("Elmer Fudd", 85);

		roster.add(student1);
		roster.add(student2);
		roster.add(student3);

		int result = roster.countGradesBetween(70, 100);

		assertEquals(3, result);
	}

}
