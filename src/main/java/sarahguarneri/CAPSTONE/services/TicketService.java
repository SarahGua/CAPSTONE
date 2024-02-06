package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Stand;
import sarahguarneri.CAPSTONE.entities.Ticket;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.stand.NewStandDTO;
import sarahguarneri.CAPSTONE.payloads.ticket.NewTicketDTO;
import sarahguarneri.CAPSTONE.repositories.TicketDAO;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    public List<Ticket> getAllTicket(){
        return ticketDAO.findAll();
    }

    public Ticket save(NewTicketDTO body){
        Ticket newTicket = new Ticket();

        newTicket.setMaxPeople(body.maxPeople());
        newTicket.setCost(body.cost());

        return ticketDAO.save(newTicket);
    }

    public Ticket findById(UUID id){
        return ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Ticket findByIdAndUpdate(UUID id, Ticket body){
        Ticket found = findById(id);

        found.setMaxPeople(body.getMaxPeople());
        found.setCost(body.getCost());

        return ticketDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Ticket found = findById(id);
        ticketDAO.delete(found);
    }
}
