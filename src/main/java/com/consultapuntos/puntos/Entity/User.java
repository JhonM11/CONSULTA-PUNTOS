package com.consultapuntos.puntos.Entity;

import com.consultapuntos.puntos.Repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDateTime fecharegistro;
    private String mail;

    @Column(unique = true)
    private String codeuser;

    private String state; // "A" (activo) o "I" (inactivo)
    private String name;
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Transient
    @Autowired
    private UserRepository userRepository;

    @PrePersist
    public void prePersist() {
        if (fecharegistro == null) {
            this.fecharegistro = LocalDateTime.now();
        }
        if (state == null) {
            this.state = "A";
        }
        if (codeuser == null || codeuser.isEmpty()) {
            this.codeuser = generateUniqueCode();
        }
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        } while (userRepository != null && userRepository.existsByCodeuser(code));
        return code;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + this.role.getName());
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}