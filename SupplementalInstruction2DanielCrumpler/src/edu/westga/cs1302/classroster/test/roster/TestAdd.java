package edu.westga.cs1302.classroster.test.roster;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.classroster.model.Roster;
import edu.westga.cs1302.classroster.model.Student;

class TestAdd {

	@Test
	void testAddToEmptyRoster() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 92);
		
		boolean result = roster.add(student1);
		
		assertTrue(result);
		assertEquals(1, roster.size());
	}
	
	@Test
	void testAddMultipleStudents() {
		Roster roster = new Roster();
		Student student1 = new Student("Bugs Bunny", 92);
		Student student2 = new Student("Daffy Duck", 71);
		Student student3 = new Student("Elmer Fudd", 85);
		
		boolean result = roster.add(student1);
		result = roster.add(student2);
		result = roster.add(student3);
		
		assertTrue(result);
		assertEquals(3, roster.size());
	}
	
	@Test
	void testNullStudent() {
		Roster roster = new Roster();
		
		assertThrows(IllegalArgumentException.class, () -> roster.add(null));
	}

}