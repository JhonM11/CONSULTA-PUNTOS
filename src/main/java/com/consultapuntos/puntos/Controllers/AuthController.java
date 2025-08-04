package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.AuthRequest;
import com.consultapuntos.puntos.Dto.AuthResponse;
import com.consultapuntos.puntos.Entity.User;
import com.consultapuntos.puntos.Repository.UserRepository;
import com.consultapuntos.puntos.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.consultapuntos.puntos.Security.Config.ApiRoutes.AUTH;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(AUTH)
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Buscar usuario por username
            User user = userRepository.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            // Verificar si el usuario está activo
            if (!"A".equalsIgnoreCase(user.getState())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("error", "Usuario no existe o no está activo"));
            }

            // Verificar contraseña
            if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Credenciales inválidas"));
            }

            // Generar token JWT
            final String jwt = jwtUtil.generateToken(user);

            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Usuario no existe o no está activo"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error en el servidor: " + e.getMessage()));
        }
    }



}