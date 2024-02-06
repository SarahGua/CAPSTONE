package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //GET ALL http://localhost:3001/user
    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    //GET http://localhost:3001/user/:id
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID id){
        return userService.findById(id);
    }

    //POST http://localhost:3001/user + BODY

    //PUT http://localhost:3001/user/:id + BODY

    //DELETE http://localhost:3001/user/:id


}
