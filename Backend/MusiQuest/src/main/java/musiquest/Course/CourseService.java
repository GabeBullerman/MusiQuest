package musiquest.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import musiquest.StudentUser.StudentUser;
import musiquest.StudentUser.StudentUserRepository;
import musiquest.TeacherUser.TeacherUser;
import musiquest.TeacherUser.TeacherUserRepository;

import java.util.ArrayList;
import java.util.List;



/**
 * Course Service used with Course Repository
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherUserRepository teacherUserRepository;

    @Autowired
    private StudentUserRepository studentUserRepository;

    /**
     * Create Course called inside of controller
     * @param teacherId
     * @param courseName
     * @return Course newCourse
     */
    public Course createCourse(int teacherId, String courseName) {
        TeacherUser teacher = teacherUserRepository.findById(teacherId);
        if (teacher == null) {
            // Handle the case where the teacher is not found
            return null;
        }

        Course course = new Course();
        course.setTeacher(teacher);
        course.setCourseName(courseName);

        return courseRepository.save(course);
    }

    /**
     * Add student to course, used inside of Course Controller
     * @param courseId
     * @param studentId
     * @return Course updatedCourse
     */
    public Course addStudentToCourse(int courseId, int studentId) {
        Course course = courseRepository.findById(courseId);
        StudentUser student = studentUserRepository.findById(studentId);

        if (course == null || student == null) {
            // Handle the case where the course or student is not found
            return null;
        }

        course.addStudent(student);
        return courseRepository.save(course);
    }
    
    /**
     * Gets all of the course IDs
     * @return List<Integer> courseIds
     */
    public List<Integer> getCourseIds() {
        List<Integer> courseIds = new ArrayList<>();
        
        for (Course course : courseRepository.findAll()) {
            courseIds.add(course.getID());
        }
        
        return courseIds;
    }
    
    /**
     * Gets the course IDs for a specific teacher
     * @param int teacherId
     * @return List<Integer> courseIds
     */
    public List<Integer> getCourseIdsFromTeacher(int teacherId) {
    	List<Integer> courseIds = new ArrayList<>();
    	TeacherUser user = teacherUserRepository.findById(teacherId);
    	for(Course course:courseRepository.findAll()) {
    		if(user.getID() == teacherId) {
    			courseIds.add(course.getID());
    		}
    	}
    	return courseIds;
    }
    
    /**
     * Get student IDs for a specific course
     * @param int courseId
     * @return List<Integer> studentIds
     */
    public List<Integer> getStudentIdsFromCourse(int courseId) {
    	return courseRepository.findById(courseId).getStudentIds();
    }

    // Other methods
}
