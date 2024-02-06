package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "exhibitor_id")
    private User exhibitorApp;
    @OneToOne
    @JoinColumn(name = "client_id")
    private User client;
}
