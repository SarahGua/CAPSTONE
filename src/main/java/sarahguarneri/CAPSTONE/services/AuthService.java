package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Address;
import sarahguarneri.CAPSTONE.entities.Role;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.BadRequestException;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.exceptions.UnauthorizedException;
import sarahguarneri.CAPSTONE.payloads.users.NewUserDTO;
import sarahguarneri.CAPSTONE.payloads.users.UserLoginDTO;
import sarahguarneri.CAPSTONE.repositories.AddressDAO;
import sarahguarneri.CAPSTONE.repositories.UserDAO;
import sarahguarneri.CAPSTONE.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private AddressDAO addressDAO;

    public String authenticationUser(UserLoginDTO body){
        User user =  userService.findByEmail(body.email());

        if(bcrypt.matches(body.password(), user.getPassword())){
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }

    public User findByEmail(UserLoginDTO body){
        return userService.findByEmail(body.email());
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
        newUser.setCompany_name(body.company_name());
        newUser.setCompany_email(body.company_email());
        newUser.setCompany_phone_number(body.company_phone_number());
        newUser.setVAT(body.VAT());

        Address address = addressDAO.findById(body.idAddress())
                .orElseThrow(() -> new NotFoundException("Address not found"));

        newUser.setAddress(address);

        if(body.role().equalsIgnoreCase("exhibitor")){
            newUser.setRole(Role.EXHIBITOR);
        } else if (body.role().equalsIgnoreCase("client")){
            newUser.setRole(Role.CLIENT);
        } else {
            newUser.setRole(Role.ADMIN);
        }

        return userDAO.save(newUser);
    }
}
