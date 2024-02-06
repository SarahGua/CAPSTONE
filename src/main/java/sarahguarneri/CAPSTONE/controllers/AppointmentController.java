package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.Appointment;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentDTO;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentResponseDTO;
import sarahguarneri.CAPSTONE.services.AppointmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable UUID id){
        return appointmentService.findById(id);
    }

    @PostMapping
    public NewAppointmentResponseDTO saveStand(@RequestBody @Validated NewAppointmentDTO body){
        Appointment newAppointment = appointmentService.save(body);
        return new NewAppointmentResponseDTO(newAppointment.getId());
    }

    @PutMapping("/{appointmentId}")
    public Appointment findAndUpdate(@PathVariable UUID id, @RequestBody Appointment body){
        return appointmentService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{appointmentId}")
    public void findAndDelete(@PathVariable UUID id){
        appointmentService.findByIdAndDelete(id);
    }
}
