package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.BadRequestException;
import sarahguarneri.CAPSTONE.payloads.users.NewUserDTO;
import sarahguarneri.CAPSTONE.payloads.users.NewUserResponseDTO;
import sarahguarneri.CAPSTONE.payloads.users.UserLoginDTO;
import sarahguarneri.CAPSTONE.payloads.users.UserLoginResponseDTO;
import sarahguarneri.CAPSTONE.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO logi(@RequestBody UserLoginDTO body){
        String token = authService.authenticationUser(body);
        User user = authService.findByEmail(body);

        return new UserLoginResponseDTO(
                token,
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getImg_url(),
                user.getRole().toString()
        );
    }

    @PostMapping("/register")
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        System.out.println(validation);
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload");
        } else {
            User newUser = authService.save(body);
            return new NewUserResponseDTO(newUser.getId());
        }
    }


}
