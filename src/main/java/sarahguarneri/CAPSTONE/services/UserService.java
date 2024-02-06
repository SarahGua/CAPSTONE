package sarahguarneri.CAPSTONE.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Address;
import sarahguarneri.CAPSTONE.entities.Role;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.users.NewUserDTO;
import sarahguarneri.CAPSTONE.repositories.UserDAO;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User findById(UUID id){
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

//    public User save(NewUserDTO body){
//        userDAO.findByEmail(body.email()).ifPresent(user -> {
//            try{
//                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso");
//            } catch (BadRequestException e){
//                throw new RuntimeException(e);
//            }
//        });
//
//        User newUser = new User();
//        newUser.setName(body.name());
//        newUser.setSurname(body.surname());
//        newUser.setEmail(body.email());
//        newUser.setPassword(body.password());
//        newUser.setPhone_number(body.phone_number());
//        newUser.setRole(Role.valueOf(body.role()));
//        newUser.setCompany_name(body.company_name());
//        newUser.setCompany_email(body.company_email());
//        newUser.setCompany_phone_number(body.company_phone_number());
//        newUser.setVAT(body.VAT());
//        newUser.setAddress(body.idAddress());
//
//    }
}
