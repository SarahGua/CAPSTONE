package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Field;
import sarahguarneri.CAPSTONE.entities.Role;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.BadRequestException;
import sarahguarneri.CAPSTONE.exceptions.UnauthorizedException;
import sarahguarneri.CAPSTONE.payloads.users.admin.NewUserAdminDTO;
import sarahguarneri.CAPSTONE.payloads.users.client.NewUserClientDTO;
import sarahguarneri.CAPSTONE.payloads.users.exhibitor.NewUserDTO;
import sarahguarneri.CAPSTONE.payloads.users.UserLoginDTO;
import sarahguarneri.CAPSTONE.repositories.UserDAO;
import sarahguarneri.CAPSTONE.security.JWTTools;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticationUser(UserLoginDTO body){

        User user = userService.findByEmail(body.email());

        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(body.password());

        if(bcrypt.matches(body.password(), user.getPassword())){
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }

    public User save(NewUserDTO body){

        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso");
        });

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setPhone_number(body.phone_number());
//        newUser.setCompany_name(body.company_name());
//        newUser.setCompany_email(body.company_email());
//        newUser.setCompany_phone_number(body.company_phone_number());
//        newUser.setVAT(body.VAT());
//        newUser.setAddress(body.address());
//        Field field = fieldService.findById(body.idField());
//        newUser.setField(field);

        if(body.role().equalsIgnoreCase("exhibitor")){
            newUser.setRole(Role.EXHIBITOR);
        } else if (body.role().equalsIgnoreCase("client")){
            newUser.setRole(Role.CLIENT);
        } else {
            newUser.setRole(Role.ADMIN);
        }

        return userDAO.save(newUser);
    }

    public User saveAdmin(NewUserAdminDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso");
        });

        User newUser = new User();
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.ADMIN);

//        if(body.role().equalsIgnoreCase("exhibitor")){
//            newUser.setRole(Role.EXHIBITOR);
//        } else if (body.role().equalsIgnoreCase("client")){
//            newUser.setRole(Role.CLIENT);
//        } else {
//            newUser.setRole(Role.ADMIN);
//        }

        return userDAO.save(newUser);
    }

    public User saveClient(NewUserClientDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso");
        });

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setPhone_number(body.phone_number());
        newUser.setRole(Role.CLIENT);

        return userDAO.save(newUser);
    }

    public User findByIdAndUpdate(UUID id, User body){
        User found = userService.findById(id);

        if(body.getName() != null){
            found.setName(body.getName());
        }

        if(body.getSurname() != null){
            found.setSurname(body.getSurname());
        }

        if(body.getEmail() != null){
            found.setEmail(body.getEmail());
        }

//        found.setPassword(bcrypt.encode(body.getPassword()));

        if(body.getPhone_number() != null){
            found.setPhone_number(body.getPhone_number());
        }

        if(body.getCompany_name() != null){
            found.setCompany_name(body.getCompany_name());
        }

        if(body.getCompany_email() != null){
            found.setCompany_email(body.getCompany_email());
        }

        if(body.getCompany_phone_number() != null){
            found.setCompany_phone_number(body.getCompany_phone_number());
        }

        if(body.getVAT() != null){
            System.out.println(body.getVAT());
            found.setVAT(body.getVAT());
        }

        if(body.getAddress() != null){
            found.setAddress(body.getAddress());
        }

        if(body.getField() != null){
            found.setField(body.getField());
        }

        return userDAO.save(found);
    }
}
