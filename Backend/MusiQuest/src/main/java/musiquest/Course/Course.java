package musiquest.Course;

import java.util.ArrayList;
import java.util.List;

//import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import musiquest.StudentUser.StudentUser;
import musiquest.TeacherUser.TeacherUser;

/**
 * Course object that a Teacher User can create to store a list of Student to have access to 
 * monitor a students progress
 */
@Entity
@Table
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	/**
	 * Teacher in charge of class
	 */
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher")
	private TeacherUser teacher;
	
	/**
	 * Course name of the course
	 */
	@Column
	private String courseName;
	
	/**
	 * The students in the class
	 */
	@ManyToMany
	private List<StudentUser> students;
	
	/**
	 * Constructor 
	 * @param teacher
	 */
	public Course(TeacherUser teacher, String courseName) {
		this.teacher = teacher;
		this.courseName = courseName;
	}
	
	public Course() {}
	
	/**
	 * Returns the ID of the entity
	 * @returns ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Sets the ID of the entity 
	 * @param int ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	
	/**
	 * Sets the course with a new Teacher User
	 * @param TeacherUser
	 */
	public void setTeacher(TeacherUser newTeacher) {
		this.teacher = newTeacher;
	}
	
	/**
	 * Gets the teacher of the class
	 * @returns TeacherUser
	 */
	public TeacherUser getTeacher() {
		return teacher;
	}
	
	/**
	 * Adds a student to the course
	 * @param StudentUser
	 * @return String status message
	 */
	public String addStudent(StudentUser newStudent) {
		
		if(newStudent == null) {
			return "Invalid Student";
		}
		else if(students.contains(newStudent)) {
			return "Student already in class";
		} else {
			students.add(newStudent);
			return "Student Added to Class";
		}
	}
	
	/**
	 * Removes a student from the course
	 * @param StudentUser
	 * @return String status message
	 */
	public String removeStudent(StudentUser student) {
		if(student == null) {
			return "Invalid Student";
		} else if(!students.contains(student)) {
			return "Student not in course";
		} else {
			students.remove(student);
			return "Student Removed";
		}
	}
	
	
	/**
	 * Returns the students of the course
	 * @return List<StudentUser> students
	 */
	public List<StudentUser> getStudents() {
		return students;
	}
	
	/**
	 * Imports a list of students to the course
	 * @param List<StudentUser> newStudents
	 */
	public void importStudents(List<StudentUser> newStudents) {
		if(newStudents == null) {
			this.students = newStudents;
		}
		this.students = newStudents;
	}

	
	/**
	 * Changes the course name
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
		
	}
	
	/**
	 * Gets the course name
	 * @return String courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Get student IDs
	 * @return List<Integer> studentIds
	 */
	public List<Integer> getStudentIds() {
	    List<Integer> studentIds = new ArrayList<>();
	    
	    for (StudentUser student : students) {
	        studentIds.add(student.getID());
	    }
	    
	    return studentIds;
	}

}
