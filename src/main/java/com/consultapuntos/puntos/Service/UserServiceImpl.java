package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.CreateUserRequest;
import com.consultapuntos.puntos.Dto.ChangePasswordRequest;
import com.consultapuntos.puntos.Dto.UserContextResponse;
import com.consultapuntos.puntos.Dto.UsersResponse;
import com.consultapuntos.puntos.Entity.Role;
import com.consultapuntos.puntos.Entity.User;
import com.consultapuntos.puntos.Exception.*;
import com.consultapuntos.puntos.Repository.RoleRepository;
import com.consultapuntos.puntos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UsersResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UsersResponse(
                        user.getUsername(),
                        user.getName(),
                        user.getLastname(),
                        user.getMail(),
                        user.getCodeuser(),
                        user.getRole().getName(),
                        user.getState()
                ))
                .toList();
    }


    @Override
    public ResponseEntity<?> updateUserRoleByCodeuser(String codeuser, Long roleId) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getCodeuser().equals(codeuser))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Rol no válido"));

        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Rol actualizado correctamente"));
    }




    @Override
    public ResponseEntity<?> createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }

        Optional<Role> roleOpt = roleRepository.findByName(request.getRole());
        if (roleOpt.isEmpty()) {
            throw new RoleNotFoundException("Rol no válido");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getUsername())); // contraseña = username por defecto
        user.setMail(request.getMail());
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setState("A");
        user.setRole(roleOpt.get());

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Usuario creado exitosamente"));
    }

    private boolean isPasswordValid(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&   // Mayúscula
                password.matches(".*[a-z].*") &&   // Minúscula
                password.matches(".*\\d.*");       // Número
    }



    @Override
    public ResponseEntity<?> changePasswordByCodeuser(String codeuser, ChangePasswordRequest request) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getCodeuser().equals(codeuser))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        String current = request.getCurrentPassword();
        String nueva = request.getNewPassword();

        if (!passwordEncoder.matches(current, user.getPassword())) {
            throw new InvalidPasswordException("Contraseña actual incorrecta");
        }

        if (passwordEncoder.matches(nueva, user.getPassword())) {
            throw new PasswordReuseException("La nueva contraseña no puede ser igual a la anterior");
        }

        if (!isPasswordValid(nueva)) {
            throw new InvalidPasswordFormatException("La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número");
        }

        user.setPassword(passwordEncoder.encode(nueva));
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Contraseña actualizada correctamente"));
    }



    @Override
    public ResponseEntity<?> inactivateUser(String codeuser) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getCodeuser().equals(codeuser))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if ("I".equalsIgnoreCase(user.getState())) {
            throw new UserAlreadyInStateException("El usuario ya se encuentra inactivo");
        }

        user.setState("I");
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Usuario inactivado correctamente"));
    }



    @Override
    public ResponseEntity<?> activateUser(String codeuser) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getCodeuser().equals(codeuser))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if ("A".equalsIgnoreCase(user.getState())) {
            throw new UserAlreadyInStateException("El usuario ya se encuentra activo");
        }

        user.setState("A");
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Usuario activado correctamente"));
    }


    @Override
    public ResponseEntity<?> getUserContext() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        UserContextResponse response = new UserContextResponse(
                user.getUsername(),
                user.getName(),
                user.getLastname(),
                user.getMail(),
                user.getCodeuser(),
                user.getRole().getName()
        );

        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> resetPasswordByCodeuser(String codeuser) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getCodeuser().equals(codeuser))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        user.setPassword(passwordEncoder.encode(user.getUsername()));
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Contraseña restablecida exitosamente"));
    }



}

