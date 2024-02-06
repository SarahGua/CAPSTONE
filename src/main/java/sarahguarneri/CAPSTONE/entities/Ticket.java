package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private UUID id;
    private double cost;
    private int maxPeople;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
}
