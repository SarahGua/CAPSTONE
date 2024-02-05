package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone_number;
    private String company_name;
    private String company_email;
    private String company_phone_number;
    private long VAT;
    @OneToOne(mappedBy = "user")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;
    private String img_url;
    private Role role;
}
