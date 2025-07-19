package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.CreateUserRequest;
import com.consultapuntos.puntos.Dto.ChangePasswordRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(CreateUserRequest request);
    ResponseEntity<?> changePasswordByCodeuser(String codeuser, ChangePasswordRequest request);
    ResponseEntity<?> inactivateUser(String codeuser);
    ResponseEntity<?> activateUser(String codeuser);
}