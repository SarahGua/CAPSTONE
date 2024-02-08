package sarahguarneri.CAPSTONE.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Enumerated(EnumType.STRING)
    private Role role;
    private long VAT;
    @OneToOne(mappedBy = "user")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;
    private String img_url;
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets = new ArrayList<>();
    @OneToOne(mappedBy = "exhibitor")
    private Stand stand;
    @OneToOne(mappedBy = "exhibitorApp")
    private Appointment appointmentAsExhibitor;
    @OneToOne(mappedBy = "client")
    private Appointment appointmentAsClient;
//
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

}
