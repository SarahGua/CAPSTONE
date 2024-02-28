package sarahguarneri.CAPSTONE.services;

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

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private UserService userService;

    public List<Ticket> getAllTicket(){
        return ticketDAO.findAll();
    }

    public Ticket save(NewTicketDTO body){
        User client = userService.findById(body.clientId());

        int availableTicket = 100;
        int quantityRequested = body.quantity();

        Ticket newTicket = new Ticket();

        if(quantityRequested > 0 && quantityRequested < availableTicket){

            for(int i = 0; i < quantityRequested; i++) {
                newTicket.setClient(client);
                int newAvailableQuantity = availableTicket - quantityRequested;
                System.out.println(newAvailableQuantity);
            }
        } else {
            throw new RuntimeException();
        }

        return ticketDAO.save(newTicket);
    }

//    public int getAvailableTickets(){
//        List<Ticket> allTIckets = getAllTicket();
//        int totalTickets = allTIckets.size();
//        int soldTickets = allTIckets.stream().mapToInt(Ticket::getSoldTickets).sum();
//        return totalTickets - soldTickets;
//    }

    public Ticket findById(UUID id){
        return ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Ticket findByIdAndUpdate(UUID id, Ticket body){
        Ticket found = findById(id);

//        found.setMaxTickets(body.getMaxTickets());
        found.setCost(body.getCost());

        return ticketDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Ticket found = findById(id);
        ticketDAO.delete(found);
    }
}
