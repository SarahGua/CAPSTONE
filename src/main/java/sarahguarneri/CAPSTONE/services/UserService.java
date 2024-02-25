package sarahguarneri.CAPSTONE.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.repositories.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Cloudinary cloudinary;

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User findById(UUID id){
        System.out.println("id da trovare: " + id);
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<User> findByIdlist(UUID id){
        List<User> users = new ArrayList<>();
        users.add(userDAO.findById(id).orElseThrow(() -> new NotFoundException(id)));
        return users;
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

    public String uploadPicture(MultipartFile file, UUID id) throws IOException {
       String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

        Optional<User> found = userDAO.findById(id);
        if(found.isPresent()){
            User user = found.get();
            user.setImg_url(url);
            userDAO.save(user);
            System.out.println(url);
            return url;
        } else {
            throw new RuntimeException("User not found");
        }

    }
}
