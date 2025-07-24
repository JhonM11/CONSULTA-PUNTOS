package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.CreateUserRequest;
import com.consultapuntos.puntos.Dto.ChangePasswordRequest;
import com.consultapuntos.puntos.Dto.UsersResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> createUser(CreateUserRequest request);
    ResponseEntity<?> changePasswordByCodeuser(String codeuser, ChangePasswordRequest request);
    ResponseEntity<?> inactivateUser(String codeuser);
    ResponseEntity<?> activateUser(String codeuser);
    ResponseEntity<?> getUserContext();
    ResponseEntity<?> resetPasswordByCodeuser(String codeuser);
    List<UsersResponse> getAllUsers();
    ResponseEntity<?> updateUserRoleByCodeuser(String codeuser, Long roleId);



}