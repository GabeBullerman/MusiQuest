package musiquest.TeacherUser;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import musiquest.Course.Course;
import musiquest.Course.CourseRepository;

import java.util.Optional;

/**
 * TeacherUser Controller used to operate on TeacherUsers
 * Starting path "/teacheruser"
 */
@RestController
@RequestMapping("/teacheruser")
public class TeacherController {
	
	@Autowired
    private TeacherUserRepository TeacheruserRepository;
	
	@Autowired 
	private CourseRepository courseRepository;


    private String success = "{\"message\":\"success\"}";
    public String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all TeacherUsers, GET request, "/getallusers"
     * @return List<TeacherUser> teachers
     */
    @GetMapping(path = "/getallusers")
    List<TeacherUser> getAllUsers(){
        return TeacheruserRepository.findAll();
    }

    /**
     * Gets a specific teacher from an ID number, GET request, "/{id}"
     * @PathVariable int id
     * @return TeacherUser teacher
     */
    @GetMapping(path = "/{id}")
    TeacherUser getUserById( @PathVariable int id){
        return TeacheruserRepository.findById(id);
    }
    
    /**
     * Gets the studentIds for a specific course, GET request, "/{courseId}/getStudents/"
     * @PathVariable int courseId
     * @return List<Integer> studentIds
     */
    @GetMapping(path = "/{courseId}/getStudents/")
    List<Integer> getStudentIds(@PathVariable int courseId) {
    	return courseRepository.findById(courseId).getStudentIds();
    }
    
    /**
     * Gets the courseIds for a TeacherUser, GET request, "/courseids"
     * @RequestParam int teacherId
     * @return List<Integer> courseIds
     */
    @GetMapping(path = "/courseids")
    List<Integer> getCourseIds(@RequestParam int teacherId) {
    	List<Integer> courseIds = new ArrayList<>();
    	for(Course course:courseRepository.findAll()) {
    		if(course.getTeacher().getID() == teacherId) {
    			courseIds.add(course.getID());
    		}
    	}
    	return courseIds;
    }

    /**
     * Creates a Teacher User, POST request, "/createuser"
     * @RequestBody TeacherUser user
     * @return String success or failure
     */
    @PostMapping(path = "/createuser")
	public String createUser(@RequestBody TeacherUser user){
        if (user == null)
            return failure;
        TeacheruserRepository.save(user);
        return success;
    }

    /**
     * updates a TeacherUser, PUT request, "/updateuser/{id}"
     * @PathVariable int id
     * @RequestBody TeacherUser request
     * @return TeacherUser updatedTeacher
     */
    @PutMapping(path = "/updateuser/{id}")
    TeacherUser updateUser(@PathVariable int id, @RequestBody TeacherUser request){
    	TeacherUser user = TeacheruserRepository.findById(id);
        if(user == null)
            return null;
        TeacheruserRepository.save(request);
        return TeacheruserRepository.findById(id);
    }
    
//    @PutMapping(path = "/createcourse")
//    String createCourse(@PathVariable int id) {
//    	TeacherUser user = TeacheruserRepository.findById(id);
//    	user.createCourse(new Course(user));
//    	return success;
//    }
    
    
	

}
