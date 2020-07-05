package edu.westga.cs1302.coursegui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import edu.westga.cs1302.coursegui.resources.UI;

/**
 * The Class Course.
 * 
 * @author CS1302
 */
public class Course implements Collection<Student> {

	private String name;
//	private final List<Student> students;
	private Map<String, Student> students;

	/**
	 * Instantiates a new course.
	 * 
	 * @precondition name != null
	 * @postcondition getName() == name && size() == 0
	 * 
	 * @param name the name of the course
	 */
	public Course(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.NULL_NAME);
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException(UI.EMPTY_NAME);
		}
		this.name = name;
//		this.students = new ArrayList<Student>();
		this.students = new HashMap<String, Student>();
	}

	/**
	 * Gets the name of this course.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the student with specified id
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the student with specified id.
	 *
	 * @precondition id != null
	 * @postcondition none
	 * 
	 * @param id the id
	 * @return the student with specified id; returns null if there is no student
	 *         with the id
	 */
	public Student getById(String id) {
		if (id == null) {
			throw new IllegalArgumentException(UI.NULL_ID);
		}
//		for (Student student : this.students) {
//			if (student.getId().equals(id)) {
//				return student;
//			}
//		}
//		return null;
		return this.students.get(id);
	}

	/**
	 * Checks whether a student with the specified id exists
	 *
	 * @precondition id != null
	 * @postcondition none
	 * 
	 * @param id the id
	 * @return true, if the course contains the student with the specified id
	 */
	public boolean containsId(String id) {
		if (id == null) {
			throw new IllegalArgumentException(UI.NULL_ID);
		}
//		for (Student student : this.students) {
//			if (student.getId().equals(id)) {
//				return true;
//			}
//		}
//		return false;
		return this.students.containsKey(id);
	}

	/**
	 * Removes the student with the specified id
	 *
	 * @precondition id != null
	 * @postcondition this.size() == this.size()@prev - 1
	 * 
	 * @param id the id
	 * @return true, if the student with the specified id has been removed
	 */
	public boolean removeById(String id) {
		if (id == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		}
//		for (Student student : this.students) {
//			if (student.getId().equals(id)) {
//				return this.students.remove(student);
//			}
//		}
//		return false;
		return this.students.remove(id) != null;
	}

	/**
	 * Adds the student.
	 *
	 * @precondition student != null
	 * @postcondition this.size() == this.size()@prev + 1
	 * 
	 * @param false if the student is already contained in this course or could not
	 *              be added; true otherwise
	 */
	@Override
	public boolean add(Student student) {
		if (student == null) {
			throw new IllegalArgumentException(UI.NULL_STUDENT);
		}
//		if (this.students.containsKey(student.getId())) {
//			return false;
//		} else {
//			this.students.put(student.getId(), student);
//			return true;
//		}
		return this.students.putIfAbsent(student.getId(), student) == null;
	}

	@Override
	public boolean addAll(Collection<? extends Student> students) {
		for (Student student : students) {
			if (student == null) {
				throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
			}
		}
		boolean changed = false;
		for (Student student : students) {
			if (this.add(student)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		this.students.clear();
	}

	@Override
	public boolean contains(Object student) {
		if (student == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		}

		return this.students.containsValue(student);
	}

	@Override
	public boolean containsAll(Collection<?> students) {
		for (Object student : students) {
			if (student == null) {
				throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
			}
		}
		for (Object student : students) {
			if (!this.contains(student)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return this.students.isEmpty();
	}

	@Override
	public Iterator<Student> iterator() {
		return this.students.values().iterator();
	}

	@Override
	public boolean remove(Object student) {
		if (student == null) {
			throw new NullPointerException(UI.NULL_STUDENT);
		}
//		return this.students.remove(student);
		return this.students.remove(((Student) student).getId(), student);
	}

	@Override
	public boolean removeAll(Collection<?> students) {
		for (Object student : students) {
			if (student == null) {
				throw new NullPointerException(UI.COLLECTION_CONTAINS_NULL);
			}
		}
		boolean changed = false;
		for (Object student : students) {
			if (this.remove(student)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> students) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return this.students.size();
	}

	@Override
	public Object[] toArray() {
		return this.students.values().toArray();
	}

	@Override
	public <T> T[] toArray(T[] students) {
		return this.students.values().toArray(students);
	}

	/**
	 * Gets the students that meet the filter requirement
	 * 
	 * @param filter to be applied to the students
	 * @return the collection of students meeting the filter
	 */
	public Collection<Student> filterBy(Predicate<Student> filter) {
		Collection<Student> filteredStudents = new ArrayList<Student>();
		for (Student currStudent : this.students) {
			if (filter.test(currStudent)) {
				filteredStudents.add(currStudent);
			}
		}
		return filteredStudents;
	}
}
