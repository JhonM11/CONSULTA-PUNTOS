package com.consultapuntos.puntos.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String username;
    private String mail;
    private String name;
    private String lastname;
    private String role;
}
