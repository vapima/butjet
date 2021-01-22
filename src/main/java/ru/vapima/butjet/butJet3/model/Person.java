package ru.vapima.butjet.butJet3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "persons")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Column(name = "mail", unique = true,nullable = false)
    private String mail;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotNull
    @Enumerated(EnumType.STRING)
    private State state;
    @NotNull
    private String hashPassword;
    @OneToMany(mappedBy="person", fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Plan> planList;
    @OneToMany(mappedBy="person", fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Acc> accList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(()->role.name());
    }

    @Override
    public String getPassword() {
        return hashPassword;
    }

    @Override
    public String getUsername() {
        return mail;
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
