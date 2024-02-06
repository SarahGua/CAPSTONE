package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Appointment;
import sarahguarneri.CAPSTONE.entities.Field;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentDTO;
import sarahguarneri.CAPSTONE.payloads.field.NewFieldDTO;
import sarahguarneri.CAPSTONE.repositories.AppointmentDAO;
import sarahguarneri.CAPSTONE.repositories.FieldDAO;

import java.util.List;
import java.util.UUID;

@Service
public class FieldService {

    @Autowired
    private FieldDAO fieldDAO;

    public List<Field> getAll(){
        return fieldDAO.findAll();
    }

    public Field save(NewFieldDTO body){
        Field newField = new Field();

        newField.setDescription(body.description());

        return fieldDAO.save(newField);
    }

    public Field findById(int id){
        return fieldDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Field findByIdAndUpdate(int id, Field body){
        Field found = findById(id);

        found.setDescription(body.getDescription());

        return fieldDAO.save(found);
    }

    public void findByIdAndDelete(int id){
        Field found = findById(id);
        fieldDAO.delete(found);
    }
}
