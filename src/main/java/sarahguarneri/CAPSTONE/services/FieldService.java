package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Appointment;
import sarahguarneri.CAPSTONE.entities.Field;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentDTO;
import sarahguarneri.CAPSTONE.payloads.field.FieldAddUser;
import sarahguarneri.CAPSTONE.payloads.field.NewFieldDTO;
import sarahguarneri.CAPSTONE.repositories.AppointmentDAO;
import sarahguarneri.CAPSTONE.repositories.FieldDAO;

import java.util.List;
import java.util.UUID;

@Service
public class FieldService {

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private UserService userService;

    public List<Field> getAll(){
        return fieldDAO.findAll();
    }

    public Field save(NewFieldDTO body){
        Field newField = new Field(body.description());

        newField.setDescription(body.description());

        return fieldDAO.save(newField);
    }

    public Field findById(int id){
        return fieldDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Field findByIdAndAddUser(int id, FieldAddUser body){
        Field found = findById(id);
        User userFound = userService.findById(body.userId());

        System.out.println(body);
        System.out.println(id);
        System.out.println(found);
        System.out.println(userFound);

//        for(User user : body.getUsers()){
//            found.getUsers().add(user);
//        }

        found.getUsers().add(userFound);
        userFound.setField(found);

        return fieldDAO.save(found);
    }

    public Field update(int id, Field body){
        Field found = findById(id);

        found.setDescription(body.getDescription());

        return fieldDAO.save(found);
    }

    public void findByIdAndDelete(int id){
        Field found = findById(id);
        fieldDAO.delete(found);
    }
}
