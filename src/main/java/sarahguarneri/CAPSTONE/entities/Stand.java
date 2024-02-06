package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Stand {
    @Id
    @GeneratedValue
    private UUID id;
    private double cost;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User exhibitor;
}
