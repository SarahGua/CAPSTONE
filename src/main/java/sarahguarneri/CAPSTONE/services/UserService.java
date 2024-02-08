package sarahguarneri.CAPSTONE.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Address;
import sarahguarneri.CAPSTONE.entities.Role;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.users.NewUserDTO;
import sarahguarneri.CAPSTONE.repositories.AddressDAO;
import sarahguarneri.CAPSTONE.repositories.UserDAO;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AddressDAO addressDAO;

//    @Autowired
//    private PasswordEncoder bcrypt;

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User findById(UUID id){
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public void findAndDelete(UUID id){
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByEmail(String email) throws NotFoundException{
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }

}
