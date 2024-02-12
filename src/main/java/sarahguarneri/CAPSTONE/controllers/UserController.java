package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.payloads.users.NewUserDTO;
import sarahguarneri.CAPSTONE.payloads.users.NewUserResponseDTO;
import sarahguarneri.CAPSTONE.services.AuthService;
import sarahguarneri.CAPSTONE.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

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
//    @PostMapping
//    public NewUserResponseDTO saveUser(@RequestBody @Validated NewUserDTO body){
//        User newUser = authService.save(body);
//        return new NewUserResponseDTO(newUser.getId());
//    }

    //PUT http://localhost:3001/user/:id + BODY
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findAndUpdate(@PathVariable UUID id, @RequestBody User body){
        return authService.findByIdAndUpdate(id, body);
    }

    //DELETE http://localhost:3001/user/:id
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable UUID id){
        userService.findAndDelete(id);
    }

    //GET http://localhost:3001/user/exhibitorByName?name=exampleName
    @GetMapping("/exhibitorByName")
    public List<User> getByName(@RequestParam String name){
        return userService.findByName(name);
    }

    //GET http://localhost:3001/user/fieldUsers?description=exampleDescription
    @GetMapping("/fieldUsers")
    public List<User> getUsersByField(@RequestParam String description) {
        return userService.findUsersByField(description);
    }
}
