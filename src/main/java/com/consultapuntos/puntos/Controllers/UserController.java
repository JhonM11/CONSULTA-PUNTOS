package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CreateUserRequest;
import com.consultapuntos.puntos.Dto.ChangePasswordRequest;
import com.consultapuntos.puntos.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/puntos/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PatchMapping("/change-passwd/{codeuser}")
    public ResponseEntity<?> changePassword(
            @PathVariable String codeuser,
            @RequestBody ChangePasswordRequest request) {
        return userService.changePasswordByCodeuser(codeuser, request);
    }


    @PatchMapping("/inactivate/{codeuser}")
    public ResponseEntity<?> inactivateUser(@PathVariable String codeuser) {
        return userService.inactivateUser(codeuser);
    }

    @PatchMapping("/activate/{codeuser}")
    public ResponseEntity<?> activateUser(@PathVariable String codeuser) {
        return userService.activateUser(codeuser);
    }



}

