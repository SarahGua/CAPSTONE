package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewAppointmentResponseDTO saveAppointment(@RequestBody @Validated NewAppointmentDTO body){
        Appointment newAppointment = appointmentService.save(body);
        return new NewAppointmentResponseDTO(newAppointment.getId());
    }

    @PostMapping("/book")
    public NewAppointmentResponseDTO bookAppointment(@RequestParam UUID clientId, @RequestParam UUID exhibitorId, @RequestParam UUID appointmentId){
        Appointment appointment = appointmentService.bookAppointment(clientId, exhibitorId, appointmentId);
        return new NewAppointmentResponseDTO(appointmentId);
    }

    @PutMapping("/{appointmentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Appointment findAndUpdate(@PathVariable UUID id, @RequestBody Appointment body){
        return appointmentService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{appointmentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findAndDelete(@PathVariable UUID id){
        appointmentService.findByIdAndDelete(id);
    }
}
