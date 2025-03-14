package musiquest.StudentUser;

import musiquest.Users.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import musiquest.Course.Course;
import musiquest.Users.User;

import java.util.ArrayList;
import java.util.List;




/**
 * A student user that has been assigned to a teacher, student user must specify
 * that they are a student at sign up
 */

@Entity
@Table
public class StudentUser extends User{

//	@ManyToMany
//    @JoinTable(
//        name = "student_courses",
//        joinColumns = @JoinColumn(name = "student_id"),
//        inverseJoinColumns = @JoinColumn(name = "course_id")
//    )
//	private List<Course> courses;
	
	
	
	public StudentUser(String name, String email, String password) {
		this.name = name;
		this.emailId = email;
		this.password = password;
	}
	
	public StudentUser() {
	}
	
//	/*
//	 * Returns the list of classes the student is in
//	 */
//	public List<Course> getClasses() {
//		return courses;
//	}
//	
//	/*
//	 * Sets a list of classes as 
//	 */
//	public void setClasses(List<Course> courses) {
//		this.courses = courses;
//	}
//	
//	/*
//	 * Adds a class to the list of classes, Returns a string saying if it was added or not
//	 */
//	public String addCourse(Course newCourse) {
//		if(newCourse == null) {
//			return "Course does not exist";
//		}
//		else if(courses.contains(newCourse)) {
//			return "Already in class";
//		} else {
//			courses.add(newCourse);
//			return "Success";
//		}
//	}
//	
//	/*
//	 * removes a class from the list if possible, returns a string declaring what happened
//	 */
//	public String removeCourse(Course oldCourse) {
//		try {
//			if(oldCourse == null) {
//				return "Course does not exist";
//			}
//			if(!courses.contains(oldCourse)) {
//				return "Not in class";
//			} else {
//				courses.remove(oldCourse);
//				return "Success";
//			}
//		} catch (Exception e) {
//			return "Error occured";
//		}
//		
//	}
//
//	public StudentUser orElse(Object object) {
//		if(object.getClass().equals(this.getClass())) {
//			return (StudentUser) object;
//		} else {
//			return null;
//		}
//	}
//	
//	
//	public List<Integer> getCourseIds() {
//	    List<Integer> courseIds = new ArrayList<>();
//	    
//	    for (Course course : courses) {
//	        courseIds.add(course.getID());
//	    }
//	    
//	    return courseIds;
//	}
	
	
	
	
	
}
