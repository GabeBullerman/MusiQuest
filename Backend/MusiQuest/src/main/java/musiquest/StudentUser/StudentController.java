package musiquest.StudentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
 * StudentUser Controller used to operate on StudentUser
 * Starting path "/studentuser"
 */
@RestController
@RequestMapping(path = "/studentuser")
public class StudentController {

	@Autowired
    private StudentUserRepository StudentuserRepository;
	
	@Autowired 
	private CourseRepository courseRepository;


    private String success = "{\"message\":\"success\"}";
    public String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all StudentUsers, GET request, "/studentusers"
     * @return List<StudentUser> studentUsers
     */
    @GetMapping(path = "/studentusers")
    List<StudentUser> getAllUsers(){
        return StudentuserRepository.findAll();
    }
    
    /**
     * Gets a Student User from an ID number, GET request, "/{id}"
     * @PathVariable int id
     * @return StudentUser student
     */
    @GetMapping(path = "/{id}")
    StudentUser getUserById( @PathVariable int id){
        return StudentuserRepository.findById(id);
    }
    

    /**
     * Creates a new StudentUser, POST request, "/createuser"
     * @RequestBody StudentUser user
     * @return String success or failure
     */
    @PostMapping(path = "/createuser")
	public String createUser(@RequestBody StudentUser user){
        if (user == null)
            return failure;
        StudentuserRepository.save(user);
        return success;
    }
    
    /**
     * Gets the courseIds from a studentId, GET request, "/getcourseids"
     * @RequestParam int studentId
     * @return List<Integer> courseIds
     */
    @GetMapping(path = "/getcourseids")
    public List<Integer> getCourseIds(@RequestParam int studentId) {
    	List<Integer> courseIds = new ArrayList<>();
    	
    	for(Course course: courseRepository.findAll()) {
    		if(course.getStudentIds().contains(studentId)) {
    			courseIds.add(course.getID());
    		}
    	}
    	
    	return courseIds;
    }

    /**
     * Update a StudentUser, PUT request, "/{id}"
     * @PathVariable int id
     * @RequestBody StudentUser request
     * @return StudentUser updatedUser
     */
    @PutMapping("/{id}")
    StudentUser updateUser(@PathVariable int id, @RequestBody StudentUser request){
    	StudentUser user = StudentuserRepository.findById(id);
        if(user == null)
            return null;
        StudentuserRepository.save(request);
        return StudentuserRepository.findById(id);
    }
	

}
