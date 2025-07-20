package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Entity.Role;
import com.consultapuntos.puntos.Entity.TipoConexion;
import com.consultapuntos.puntos.Entity.User;
import com.consultapuntos.puntos.Repository.RoleRepository;
import com.consultapuntos.puntos.Repository.TipoConexionRepository;
import com.consultapuntos.puntos.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TipoConexionRepository tipoConexionRepository;

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

        roleRepository.findByName("COORDINADOR").orElseGet(() -> {
            Role role = new Role();
            role.setName("COORDINADOR");
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

        List<String> defaultConexiones = List.of("SATELITAL", "RED INALAMBRICA", "GPRS", "VPN");
        for (String name : defaultConexiones) {
            boolean exists = tipoConexionRepository.existsByNameIgnoreCase(name);
            if (!exists) {
                TipoConexion conexion = new TipoConexion();
                conexion.setName(name);
                conexion.setCode(tipoConexionRepository.findAll().stream()
                        .map(TipoConexion::getCode)
                        .max(Integer::compareTo)
                        .orElse(0) + 1);
                tipoConexionRepository.save(conexion);
            }
        }
    }
}
