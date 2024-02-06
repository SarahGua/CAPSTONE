package sarahguarneri.CAPSTONE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarahguarneri.CAPSTONE.entities.Address;

@Repository
public interface AddressDAO extends JpaRepository<Address, Integer> {
}
