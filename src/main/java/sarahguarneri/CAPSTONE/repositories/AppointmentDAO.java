package sarahguarneri.CAPSTONE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarahguarneri.CAPSTONE.entities.Appointment;

import java.util.UUID;

@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, UUID> {
}
