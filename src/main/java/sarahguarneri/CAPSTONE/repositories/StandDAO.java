package sarahguarneri.CAPSTONE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarahguarneri.CAPSTONE.entities.Stand;

import java.util.UUID;

@Repository
public interface StandDAO extends JpaRepository<Stand, UUID> {
}
