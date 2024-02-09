package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime dateTime;
    private String status = "AVAILABLE";
    @OneToOne
    @JoinColumn(name = "exhibitor_id")
    private User exhibitorApp;
    @OneToOne
    @JoinColumn(name = "client_id")
    private User client;
}
