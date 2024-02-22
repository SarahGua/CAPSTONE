package sarahguarneri.CAPSTONE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sarahguarneri.CAPSTONE.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = 'EXHIBITOR' AND u.company_name = :name")
    List<User> findExhibitorsByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.field.description = :description")
    List<User> findByFieldDescription(@Param("description") String description);
}

