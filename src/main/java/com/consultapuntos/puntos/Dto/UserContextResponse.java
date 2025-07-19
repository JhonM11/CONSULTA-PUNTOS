package com.consultapuntos.puntos.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserContextResponse {
    private String username;
    private String name;
    private String lastname;
    private String mail;
    private String codeuser;
    private String role;
}