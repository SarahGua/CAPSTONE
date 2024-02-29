package sarahguarneri.CAPSTONE.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Role;
import sarahguarneri.CAPSTONE.entities.Ticket;
import sarahguarneri.CAPSTONE.entities.User;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.ticket.NewTicketDTO;
import sarahguarneri.CAPSTONE.repositories.TicketDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TicketService {

    private static final int NUMERO_BIGLIETTI_TOTALI = 100;
    private AtomicInteger availableTickets = new AtomicInteger(NUMERO_BIGLIETTI_TOTALI);

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private UserService userService;

    public List<Ticket> getAllTicket(){
        return ticketDAO.findAll();
    }

    public Ticket save(NewTicketDTO body){
        User client = userService.findById(body.clientId());
        Ticket newticket = new Ticket();

        newticket.setClient(client);

        return ticketDAO.save(newticket);
    }

    public Ticket findById(UUID id){
        return ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Ticket findByIdAndUpdate(UUID id, Ticket body){
        Ticket found = findById(id);

        found.setCost(body.getCost());

        return ticketDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Ticket found = findById(id);
        ticketDAO.delete(found);
    }

    @Transactional
    public Ticket bookTicket(NewTicketDTO body){
        User client = userService.findById(body.clientId());
        System.out.println(client);
        if(body.quantity() > availableTickets.get()){
            throw new IllegalArgumentException();
        }

        Ticket createdTicket = null;
        for(int i = 0; i < body.quantity(); i++){
            Ticket ticket = new Ticket();
            ticket.setClient(client);
            ticketDAO.save(ticket);
            createdTicket = ticket;
        }
        availableTickets.addAndGet(-body.quantity());
        System.out.println(availableTickets);

        return createdTicket;
    }

    public int getAvailableTickets(){
        return availableTickets.get();
    }
}
