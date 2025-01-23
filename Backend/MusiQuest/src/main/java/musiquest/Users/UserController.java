package musiquest.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * UserController to operate on all users
 * Starting Path "/user"
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserRepository userRepository;


    private String success = "{\"message\":\"success\"}";
    public String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all users, GET request, "/getallusers"
     * @return
     */
    @GetMapping(path = "/getallusers")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    
    /**
     * Gets a user by its ID, GET request, "/{id}"
     * @PathVariable int id
     * @return User
     */
    @GetMapping(path = "/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    
    /**
     * Updates a specific user by its ID , PUT request, "/updateuser/{id}"
     * @PathVariable int id
     * @RequestBody User request
     * @return User
     */
    @PutMapping("/updateuser/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
    	User user = userRepository.findById(id);
        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }
    
    /**
     * Gets a specific user by checking name and password with DB, GET request, "/finduser"
     * @RequestParam String name
     * @RequestParam String password
     * @return User
     */
    @GetMapping("/finduser")
    User findUserByNameAndPassword(@RequestParam String name, @RequestParam String password) {
    	for(User user: userRepository.findAll()) {
    		if(user.getName().equals(name) && user.getPassword().equals(password)) {
    			return user;
    		}
    	}
    	return null;
    }
	
	
	
}
