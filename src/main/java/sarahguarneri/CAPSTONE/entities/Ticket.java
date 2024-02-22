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
    private int maxTickets = 10;
    private int requiredNumb = 1;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", cost=" + cost +
                ", maxTickets=" + maxTickets +
                ", requiredNumb=" + requiredNumb +
                '}';
    }
}
