package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.security.JWTTools;
import sarahguarneri.CAPSTONE.services.AuthService;
import sarahguarneri.CAPSTONE.services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTTools jwtTools;

    //GET ALL http://localhost:3001/user
    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    //GET http://localhost:3001/user/:id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        System.out.println("id da trovare pt.2: " + id.toString());
        return userService.findById(id);
    }

    @GetMapping("/token/{token}")
    public User getUserByToken(@PathVariable String token){
        String id = jwtTools.extractIdFromToken(token);
        UUID idUUID = UUID.fromString(id);
        return userService.findById(idUUID);
    }

    //PUT http://localhost:3001/user/:id + BODY
    @PutMapping("/{id}")
    public User findAndUpdate(@PathVariable UUID id, @RequestBody User body){
        return authService.findByIdAndUpdate(id, body);
    }

    //DELETE http://localhost:3001/user/:id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable UUID id){
        userService.findAndDelete(id);
    }

    //GET http://localhost:3001/user/exhibitorByName?name=exampleName
    @GetMapping("/exhibitorByName")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EXHIBITOR', 'CLIENT)")
    public List<User> getByName(@RequestParam String name){
        return userService.findByName(name);
    }

    //GET http://localhost:3001/user/fieldUsers?description=exampleDescription
    @GetMapping("/fieldUsers")
    public List<User> getUsersByField(@RequestParam String description) {
        return userService.findUsersByField(description);
    }

    //endpoint per caricamento immagine
    @PostMapping("/{id}/uploadAvatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID id) throws IOException {
        System.out.println(file);
        return userService.uploadPicture(file, id);
    }
}
