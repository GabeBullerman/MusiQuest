package musiquest.Course;

import org.springframework.web.bind.annotation.RestController;

import musiquest.Leaderboard.LeaderboardEntry;
import musiquest.SoloUser.SoloUser;
import musiquest.StudentUser.StudentUser;
import musiquest.StudentUser.StudentUserRepository;
import musiquest.TeacherUser.TeacherUser;
import musiquest.TeacherUser.TeacherUserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for Courses, "/courses"
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentUserRepository studentRepository;
	
	@Autowired
	private TeacherUserRepository teacherRepository;
	
	private String success = "{\"message\":\"success\"}";
    public String failure = "{\"message\":\"failure\"}";
    
    /**
     * Create a course with course name, POST request, "/createcourse/{courseName}"
     * @RequestParam teacherId
     * @PathVariable courseName
     * @return String success or failure
     */
    @PostMapping(path = "/createcourse/{courseName}")
	public String createCourse(@RequestParam int teacherId, @PathVariable String courseName){
    	if(teacherId < 1) {
    		return failure;
    	}
    	
        courseRepository.save(new Course(teacherRepository.findById(teacherId), courseName));
        teacherRepository.save(teacherRepository.findById(teacherId));
        return success;
    }
    
    /**
     * Gets all courses
     * @return List<Course>
     */
    @GetMapping(path = "/getallcourses")
    List<Course> getAllUsers(){
        return courseRepository.findAll();
    }
    
    @Autowired
    private CourseService courseService;

    /**
     * Adds a student to a course, PUT request, "/{courseId}/addStudent/{studentId}"
     * @param int courseId
     * @param int studentId
     * @return String success or failure
     */
    @PutMapping("/{courseId}/addStudent/{studentId}")
    public String addStudentToCourse(@PathVariable int courseId, @PathVariable int studentId) {
//        Course course = courseRepository.findById(courseId).orElse(null);
//        StudentUser student = studentRepository.findById(studentId).orElse(null);

        //if (course != null && student != null) {
            Course updatedCourse = courseService.addStudentToCourse(courseId, studentId);
            courseRepository.save(updatedCourse);
            //if (updatedCourse != null) {
                return success;
            //}
        //}
        //return failure;
    }
}
	

