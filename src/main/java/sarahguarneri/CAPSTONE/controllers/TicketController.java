package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.Ticket;
import sarahguarneri.CAPSTONE.payloads.ticket.NewTicketDTO;
import sarahguarneri.CAPSTONE.payloads.ticket.NewTicketReponseDTO;
import sarahguarneri.CAPSTONE.services.TicketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getTickets(){
        return ticketService.getAllTicket();
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable UUID id){
        return ticketService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewTicketReponseDTO saveStand(@RequestBody @Validated NewTicketDTO body){
        Ticket newStand = ticketService.save(body);
        return new NewTicketReponseDTO(newStand.getId());
    }

    @PutMapping("/{ticketId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ticket findAndUpdate(@PathVariable UUID id, @RequestBody Ticket body){
        return ticketService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{ticketId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findAndDelete(@PathVariable UUID id){
        ticketService.findByIdAndDelete(id);
    }

}
