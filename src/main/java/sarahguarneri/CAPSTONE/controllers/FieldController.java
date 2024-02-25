package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.Appointment;
import sarahguarneri.CAPSTONE.entities.Field;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentDTO;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentResponseDTO;
import sarahguarneri.CAPSTONE.payloads.field.FieldAddUser;
import sarahguarneri.CAPSTONE.payloads.field.NewFieldDTO;
import sarahguarneri.CAPSTONE.payloads.field.NewFieldResponseDTO;
import sarahguarneri.CAPSTONE.repositories.FieldDAO;
import sarahguarneri.CAPSTONE.services.AppointmentService;
import sarahguarneri.CAPSTONE.services.FieldService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping
    public List<Field> getFields(){
        return fieldService.getAll();
    }

    @GetMapping("/{id}")
    public Field getFieldById(@PathVariable int id){
        return fieldService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewFieldResponseDTO saveField(@RequestBody @Validated NewFieldDTO body){
        Field newField = fieldService.save(body);
        return new NewFieldResponseDTO(newField.getId());
    }

    @PutMapping("/{id}")
    public Field findAndUpdate(@PathVariable int id, @RequestBody FieldAddUser body){
        return fieldService.findByIdAndAddUser(id, body);
    }

    @PutMapping("/{id}/{update}")
    public Field update(@PathVariable int id, @RequestBody Field body){
        return fieldService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findAndDelete(@PathVariable int id){
        fieldService.findByIdAndDelete(id);
    }
}
