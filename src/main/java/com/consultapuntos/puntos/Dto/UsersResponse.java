package com.consultapuntos.puntos.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersResponse {
    private String username;
    private String name;
    private String lastname;
    private String mail;
    private String codeuser;
    private String role;
    private String state;
}