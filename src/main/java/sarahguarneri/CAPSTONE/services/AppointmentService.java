package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Appointment;
import sarahguarneri.CAPSTONE.entities.Ticket;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentDTO;
import sarahguarneri.CAPSTONE.payloads.ticket.NewTicketDTO;
import sarahguarneri.CAPSTONE.repositories.AppointmentDAO;
import sarahguarneri.CAPSTONE.repositories.TicketDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private UserService userService;

    public List<Appointment> getAllAppointments(){
        return appointmentDAO.findAll();
    }

    public Appointment save(NewAppointmentDTO body){
        Appointment newAppointment = new Appointment();

        newAppointment.setDate(body.date());
        newAppointment.setTime(body.time());

        return appointmentDAO.save(newAppointment);
    }

    public Appointment findById(UUID id){
        return appointmentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Appointment findByIdAndUpdate(UUID id, Appointment body){
        Appointment found = findById(id);

        found.setDate(body.getDate());
        found.setTime(body.getTime());

        return appointmentDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Appointment found = findById(id);
        appointmentDAO.delete(found);
    }

    public Appointment bookAppointment(UUID exhibitoId, UUID clientId, String dateTime, String time){
        Appointment appointment = new Appointment();
        appointment.setExhibitorApp(userService.findById(exhibitoId));
        appointment.setClient(userService.findById(clientId));
        appointment.setDate(dateTime);
        appointment.setTime(time);
        appointment.setStatus("NOT AVAILABLE");
        return appointmentDAO.save(appointment);
    }

    public Appointment bookAppointmentAvailable(UUID appointmentId, UUID clientId){
        Appointment appointment = appointmentDAO.findById(appointmentId).orElseThrow(() -> new NotFoundException("Appuntamento con id " + appointmentId + " non trovato"));

        if(!appointment.getStatus().equals("AVAILABLE")){
            throw new RuntimeException("Appointment not available");
        }

        User client = userService.findById(clientId);
        appointment.setClient(client);
        appointment.setStatus("BOOKED");

        return appointmentDAO.save(appointment);
    }
}
