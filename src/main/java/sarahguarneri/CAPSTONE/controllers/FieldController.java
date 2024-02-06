package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.Appointment;
import sarahguarneri.CAPSTONE.entities.Field;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentDTO;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentResponseDTO;
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

    @GetMapping("/{fieldId}")
    public Field getFieldById(@PathVariable int id){
        return fieldService.findById(id);
    }

    @PostMapping
    public NewFieldResponseDTO saveField(@RequestBody @Validated NewFieldDTO body){
        Field newField = fieldService.save(body);
        return new NewFieldResponseDTO(newField.getId());
    }

    @PutMapping("/{fieldId}")
    public Field findAndUpdate(@PathVariable int id, @RequestBody Field body){
        return fieldService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{fieldId}")
    public void findAndDelete(@PathVariable int id){
        fieldService.findByIdAndDelete(id);
    }
}
