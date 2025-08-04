package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@EnableWebSecurity
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .filter(user -> "A".equalsIgnoreCase(user.getState()))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inactivo o no encontrado: " + username));
    }

}