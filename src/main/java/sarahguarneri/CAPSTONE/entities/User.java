package sarahguarneri.CAPSTONE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"password", "authorities", "accountNonExpired", "enabled", "accountNonLocked", "credentialsNonExpired", "username"})
public class User implements UserDetails {
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
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String vat;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_id")
    private Field field;
    private String img_url = "http://res.cloudinary.com/dxjm72os7/image/upload/v1708860620/c0ujnqznypkkhoowjw5z.jpg";
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets = new ArrayList<>();
    @JsonManagedReference
    @OneToOne(mappedBy = "exhibitor")
    private Stand stand;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "Exhibitor")
//    private List<Appointment> appointmentsEx = new ArrayList<>();
//    @JsonIgnore
//    @ManyToMany(mappedBy = "Client")
//    private List<Appointment> appointmentsCl = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "Client")
    private List<Appointment> appointmentsCl = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "Exhibitor")
    private List<Appointment> appointmentsEx = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_email='" + company_email + '\'' +
                ", company_phone_number='" + company_phone_number + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", VAT='" + vat + '\'' +
//                ", field=" + field +
                ", img_url='" + img_url + '\'' +
//                ", appointmentAsExhibitor=" + appointmentAsExhibitor +
//                ", appointmentAsClient=" + appointmentAsClient +
                '}';
    }
}
