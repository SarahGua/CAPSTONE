package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
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
