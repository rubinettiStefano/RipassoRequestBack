package com.generation.ripassorequestback.dtos;

import com.generation.ripassorequestback.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutputDto
{
    private String username;
    private Role role;
    private String email;
}
