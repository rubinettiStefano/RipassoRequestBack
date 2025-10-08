package com.generation.ripassorequestback.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto
{
    private String username;
    private String password;
    private String email;
}
