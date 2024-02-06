package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.payloads.users.NewUserDTO;
import sarahguarneri.CAPSTONE.payloads.users.NewUserResponseDTO;
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
    @PostMapping
    public NewUserResponseDTO saveUser(@RequestBody @Validated NewUserDTO body){
        User newUser = userService.save(body);
        return new NewUserResponseDTO(newUser.getId());
    }

    //PUT http://localhost:3001/user/:id + BODY
    @PutMapping("/{userId}")
    public User findAndUpdate(@PathVariable UUID id, @RequestBody User body){
        return userService.findByIdAndUpdate(id, body);
    }

    //DELETE http://localhost:3001/user/:id
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID id){
        userService.findAndDelete(id);
    }


}
