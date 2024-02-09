package sarahguarneri.CAPSTONE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sarahguarneri.CAPSTONE.entities.Ticket;

import java.util.UUID;

@Repository
public interface TicketDAO extends JpaRepository<Ticket, UUID> {
}
