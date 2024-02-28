package sarahguarneri.CAPSTONE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private double cost = 30.00;
    private int quantity;
    private int availableQuantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
}
