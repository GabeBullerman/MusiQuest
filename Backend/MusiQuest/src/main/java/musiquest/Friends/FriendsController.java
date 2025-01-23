//package musiquest.Friends;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class FriendsController {
//
//
//    @Autowired
//    private FriendsRepository friendsRepository;
//
//
//    private String success = "{\"message\":\"success\"}";
//    public String failure = "{\"message\":\"failure\"}";
//
//    @GetMapping(path = "/friends")
//    List<Friends> getAllUsers(){
//        return friendsRepository.findAll();
//    }
//
//    @GetMapping(path = "/friends/{name}")
//    Friends getUserById( @PathVariable int id){
//        return friendsRepository.findById(id);
//    }
//
//    @PostMapping(path = "/friends")
//    public String createUser(@RequestBody Friends user){
//        if (user == null)
//            return failure;
//        friendsRepository.save(user);
//        return success;
//    }
//
//    @PutMapping("/friends/{id}")
//    Friends updateUser(@PathVariable int id, @RequestBody Friends request){
//        Friends user = friendsRepository.findById(id);
//        if(user == null)
//            return null;
//        friendsRepository.save(request);
//        return friendsRepository.findById(id);
//    }
//
//    @DeleteMapping("/friends/{id}")
//    Friends removeUser(@PathVariable int id) {
//        Friends user = friendsRepository.findById(id);
//        if(user == null)
//            return null;
//        friendsRepository.deleteById(id);
//        return user;
//    }
//}
