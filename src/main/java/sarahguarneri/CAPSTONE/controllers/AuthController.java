package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.BadRequestException;
import sarahguarneri.CAPSTONE.payloads.users.admin.NewUserAdminDTO;
import sarahguarneri.CAPSTONE.payloads.users.client.NewUserClientDTO;
import sarahguarneri.CAPSTONE.payloads.users.exhibitor.NewUserDTO;
import sarahguarneri.CAPSTONE.payloads.users.exhibitor.NewUserResponseDTO;
import sarahguarneri.CAPSTONE.payloads.users.UserLoginDTO;
import sarahguarneri.CAPSTONE.payloads.users.UserLoginResponseDTO;
import sarahguarneri.CAPSTONE.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body){
        String token = authService.authenticationUser(body);
        return new UserLoginResponseDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        System.out.println("Request body" + body);
        System.out.println("validation errors:" + validation);
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload");
        } else {
            if(body.role().equalsIgnoreCase("admin")){
                NewUserAdminDTO adminDTO = new NewUserAdminDTO(body.email(), body.password());
                User newUser = authService.saveAdmin(adminDTO);
                return new NewUserResponseDTO(newUser.getId());
            } else if(body.role().equalsIgnoreCase("client")){
                NewUserClientDTO clientDTO = new NewUserClientDTO(body.name(), body.surname(), body.email(), body.password(), body.phone_number());
                User newUser = authService.saveClient(clientDTO);
                return new NewUserResponseDTO(newUser.getId());
            } else {
                User newUser = authService.save(body);
                return new NewUserResponseDTO(newUser.getId());
            }

        }
    }
}
