package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
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
        System.out.println("id da trovare: " + id);
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public void findAndDelete(UUID id){
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByEmail(String email) throws NotFoundException{
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }

    public List<User> findByName(String name){
        return userDAO.findExhibitorsByName(name);
    }

    public List<User> findUsersByField(String description) {
        return userDAO.findByFieldDescription(description);
    }
}
