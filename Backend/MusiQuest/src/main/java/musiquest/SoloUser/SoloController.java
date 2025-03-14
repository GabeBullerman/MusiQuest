package musiquest.SoloUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * SoloController is charge of paths for SoloUser operations
 * Starting path "/solouser"
 */
@RestController
@RequestMapping(path = "/solouser")
public class SoloController {


	@Autowired
    private SoloUserRepository SolouserRepository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all solo users, GET request, "/solousers"
     * @return List<SoloUSer> soloUsers
     */
    @GetMapping(path = "/solousers")
    List<SoloUser> getAllUsers(){
        return SolouserRepository.findAll();
    }

    /**
     * Gets the user from the user's email, GET request, "/email/{email}"
     * @PathVariable String email
     * @return int userId
     */
    @GetMapping(path = "/email/{email}")
    int getUserByEmail( @PathVariable String email){
        List<SoloUser> temp = SolouserRepository.findAll();

        for(SoloUser x: temp){
            if(x.getEmail().equals(email)){
                return x.getID();
            }
        }

        return -1;
    }
    
    /**
     * Gets the solo user corresponding to an ID, GET request, "/{id}"
     * @PathVariable int id
     * @return SoloUser user
     */
    @GetMapping(path = "/{id}")
    SoloUser getUserById( @PathVariable int id){
        return SolouserRepository.findById(id);
    }


    /**
     * Creates a solo user, POST request, "/createuser"
     * @RequestParam SoloUser user
     * @return String success or failure
     */
    @PostMapping(path = "/createuser")
    String createUser(@RequestBody SoloUser user){
        if (user == null) 
            return failure;
        SolouserRepository.save(user);
        return success + user.getID();
    }
    
    /**
     * Update a solo user, PUT request, "/{id}"
     * @PathVariable int id
     * @RequestBody SoloUser request
     * @return
     */
    @PutMapping("/{id}")
    SoloUser updateUser(@PathVariable int id, @RequestBody SoloUser request){
    	SoloUser user = SolouserRepository.findById(id);
        if(user == null)
            return null;
        //user.setAccountId("SOL-" + user.getName() + user.getEmail().substring(0, user.getEmail().indexOf('@')));
        SolouserRepository.save(request);
        return SolouserRepository.findById(id);
    } 
    
    /**
     * Deletes a solouser from the DB, DELETE request, "/{id}"
     * @pPathVariable int id
     * @return SoloUser removedUser
     */
    @DeleteMapping("/{id}")
    SoloUser removeUser(@PathVariable int id) {
    	SoloUser user = SolouserRepository.findById(id);
    	if(user == null)
            return null;
    	SolouserRepository.deleteById(id);
    	return user;
    }
   



}
