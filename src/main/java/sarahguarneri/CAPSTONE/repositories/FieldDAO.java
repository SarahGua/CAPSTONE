package sarahguarneri.CAPSTONE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarahguarneri.CAPSTONE.entities.Field;

@Repository
public interface FieldDAO extends JpaRepository<Field, Integer> {
}
