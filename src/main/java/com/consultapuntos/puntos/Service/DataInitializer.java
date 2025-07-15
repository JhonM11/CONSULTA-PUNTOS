package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Entity.Role;
import com.consultapuntos.puntos.Entity.User;
import com.consultapuntos.puntos.Repository.RoleRepository;
import com.consultapuntos.puntos.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
            Role role = new Role();
            role.setName("ADMIN");
            return roleRepository.save(role);
        });

        roleRepository.findByName("AUXILIAR").orElseGet(() -> {
            Role role = new Role();
            role.setName("AUXILIAR");
            return roleRepository.save(role);
        });

        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setMail("admin@record.com.co");
            admin.setName("Luis");
            admin.setLastname("petro");
            admin.setRole(adminRole);
            admin.setState("A");
            userRepository.save(admin);
        }
    }
}
