package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @OneToMany(mappedBy = "field")
    private List<User> users = new ArrayList<>();
}
