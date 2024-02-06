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
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String street;
    private String street_number;
    private long postal_code;
    private String city;
    private String state;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
