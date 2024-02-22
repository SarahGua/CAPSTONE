package sarahguarneri.CAPSTONE.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private UUID id;
    private String date;
    private String time;
    private String status = "AVAILABLE";
    @ManyToMany
    @JoinTable(
            name = "appointment_exhibitor",
            joinColumns = @JoinColumn(name = "appointmentEx_id"),
            inverseJoinColumns = @JoinColumn(name = "exhibitor_id")
    )
    private List<User> Exhibitor = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "appointment_client",
            joinColumns = @JoinColumn(name = "appointmentCl_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<User> Client = new ArrayList<>();
}
