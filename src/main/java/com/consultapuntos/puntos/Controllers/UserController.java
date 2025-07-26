package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CreateUserRequest;
import com.consultapuntos.puntos.Dto.ChangePasswordRequest;
import com.consultapuntos.puntos.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.consultapuntos.puntos.Security.Config.ApiRoutes.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(USERS_LIST)
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PostMapping(USERS_CREATE)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }


    @PatchMapping(USERS_UPDATE_ROLE)
    public ResponseEntity<?> updateUserRole(
            @RequestParam String codeuser,
            @RequestParam Long roleId) {
        return userService.updateUserRoleByCodeuser(codeuser, roleId);
    }



    @PatchMapping(USERS_CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(
            @PathVariable String codeuser,
            @RequestBody ChangePasswordRequest request) {
        return userService.changePasswordByCodeuser(codeuser, request);
    }


    @PatchMapping(USERS_INACTIVATE)
    public ResponseEntity<?> inactivateUser(@PathVariable String codeuser) {
        return userService.inactivateUser(codeuser);
    }


    @PatchMapping(USERS_ACTIVATE)
    public ResponseEntity<?> activateUser(@PathVariable String codeuser) {
        return userService.activateUser(codeuser);
    }


    @GetMapping(USERS_MY_SESSION)
    public ResponseEntity<?> getUserContext() {
        return userService.getUserContext();
    }


    @PatchMapping(USERS_RESET_PASSWORD)
    public ResponseEntity<?> resetPassword(@PathVariable String codeuser) {
        return userService.resetPasswordByCodeuser(codeuser);
    }





}

