package edu.westga.cs1302.classroster.test.roster;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.classroster.model.Roster;
import edu.westga.cs1302.classroster.model.Student;

class TestCountStudentsThatContain {

	@Test
	void testWhenNoStudents() {
		Roster roster = new Roster();

		int result = roster.countStudentsThatContain("John");

		assertEquals(0, result);
	}
	
	@Test
	void testWhenOneStudentAndMatch() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 91);
		roster.add(student1);

		int result = roster.countStudentsThatContain("Bun");

		assertEquals(1, result);
	}
	
	@Test
	void testWhenMultipleStudentsAndOneMatches() {
		Roster roster = new Roster();
		Student student1 = new Student("Daffy Duck", 71);
		Student student2 = new Student("Bugs Bunny", 91);
		Student student3 = new Student("Elmer Fudd", 85);

		roster.add(student1);
		roster.add(student2);
		roster.add(student3);

		int result = roster.countStudentsThatContain("Bun");

		assertEquals(1, result);
	}
	
	@Test
	void testWhenMultipleStudentsAndNoneMatches() {
		Roster roster = new Roster();
		Student student1 = new Student("Daffy Duck", 71);
		Student student2 = new Student("Bugs Bunny", 91);
		Student student3 = new Student("Elmer Fudd", 85);

		roster.add(student1);
		roster.add(student2);
		roster.add(student3);

		int result = roster.countStudentsThatContain("Honey");

		assertEquals(0, result);
	}
	
	@Test
	void testWhenMultipleStudentsAndOneMatchesWithCase() {
		Roster roster = new Roster();
		Student student1 = new Student("Daffy Duck", 71);
		Student student2 = new Student("Bugs Bunny", 91);
		Student student3 = new Student("Elmer Fudd", 85);

		roster.add(student1);
		roster.add(student2);
		roster.add(student3);

		int result = roster.countStudentsThatContain("bUn");

		assertEquals(1, result);
	}
}
