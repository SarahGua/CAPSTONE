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
        Ticket newTicket = new Ticket();

        User client = userService.findById(body.clientId());
        System.out.println(body.clientId());

        newTicket.setClient(client);
//        newTicket.setRequiredNumb(body.requiredNumb());

        System.out.println(newTicket);


        return ticketDAO.save(newTicket);
    }

//    public Ticket purchase(NewTicketDTO body){
//        User user = userService.findById(body.clientId());
//
//        if(user.getRole() != Role.CLIENT){
//            throw new RuntimeException("Solo i clienti possono comprare i biglietti");
//        }
//
//        Ticket ticket = new Ticket();
//
//        ticket.setMaxTickets(body.maxTickets());
//        ticket.setCost(body.cost());
//        ticket.setRequiredNumb(body.requiredNumb());
//
//        int currentMaxNumb = ticket.getMaxTickets();
//
//        if(currentMaxNumb >= body.requiredNumb()){
//            ticket.setMaxTickets(currentMaxNumb - body.requiredNumb());
//        } else {
//            throw  new RuntimeException("Tikets not available");
//        }
//
//        return ticketDAO.save(ticket);
//    }

//    public List<Ticket> purchase(List<NewTicketDTO> tickets) {
//        List<Ticket> purchasedTickets = new ArrayList<>();
//
//        for (NewTicketDTO ticketData : tickets) {
//            User user = userService.findById(ticketData.clientId());
//
//            if (user.getRole() != Role.CLIENT) {
//                throw new RuntimeException("Solo i clienti possono comprare i biglietti");
//            }
//
//            int requiredNumb = ticketData.requiredNumb();
//            int maxTickets = ticketData.maxTickets();
//
//
//            if (stockManager.getTotalAvailableTickets() >= requiredNumb) {
//                for (int i = 0; i < requiredNumb; i++) {
//                    Ticket ticket = new Ticket();
//                    ticket.setMaxTickets(maxTickets);
//                    ticket.setCost(ticketData.getCost());
//
//                    // Riduci il numero di biglietti disponibili nel magazzino
//                    stockManager.reduceAvailableTickets(1);
//
//                    purchasedTickets.add(ticketDAO.save(ticket));
//                }
//            } else {
//                throw new RuntimeException("Non ci sono abbastanza biglietti disponibili");
//            }
//        }
//
//        return purchasedTickets;
//    }

    public Ticket findById(UUID id){
        return ticketDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Ticket findByIdAndUpdate(UUID id, Ticket body){
        Ticket found = findById(id);

        found.setMaxTickets(body.getMaxTickets());
        found.setCost(body.getCost());

        return ticketDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Ticket found = findById(id);
        ticketDAO.delete(found);
    }

//    public Ticket purchaseTicket(newTicketDTO body){
//        User user = userService.findById(body.getUserId);
//
//        if(user.getRole() != Role.CLIENT){
//            throw new RuntimeException("Only clients can buy tickets");
//        }
//
//        Ticket ticket = new Ticket();
//        ticket.setMaxTickets(body.ge);
//        int currentMaxNumb = ticket.getMaxTickets();
//        if(currentMaxNumb >= quantity){
//            ticket.setMaxTickets(currentMaxNumb - quantity);
//            return ticketDAO.save(ticket);
//        } else{
//            throw new RuntimeException("Tickets not available");
//        }
//    }
}
