package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private UUID id;
    private String street;
    private String number;
    private long postal_code;
    private String city;
    private String state;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
