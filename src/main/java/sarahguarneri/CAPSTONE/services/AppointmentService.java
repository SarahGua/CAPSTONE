package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Appointment;
import sarahguarneri.CAPSTONE.entities.Ticket;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.appointment.NewAppointmentDTO;
import sarahguarneri.CAPSTONE.payloads.ticket.NewTicketDTO;
import sarahguarneri.CAPSTONE.repositories.AppointmentDAO;
import sarahguarneri.CAPSTONE.repositories.TicketDAO;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    public List<Appointment> getAllAppointments(){
        return appointmentDAO.findAll();
    }

    public Appointment save(NewAppointmentDTO body){
        Appointment newAppointment = new Appointment();

        newAppointment.setDate(body.date());

        return appointmentDAO.save(newAppointment);
    }

    public Appointment findById(UUID id){
        return appointmentDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Appointment findByIdAndUpdate(UUID id, Appointment body){
        Appointment found = findById(id);

        found.setDate(body.getDate());

        return appointmentDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Appointment found = findById(id);
        appointmentDAO.delete(found);
    }
}
