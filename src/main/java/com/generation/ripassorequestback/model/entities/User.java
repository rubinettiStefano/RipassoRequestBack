package com.generation.ripassorequestback.model.entities;

import com.generation.ripassorequestback.exceptions.InvalidCredentials;
import com.generation.ripassorequestback.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@Getter
@Setter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull @NotBlank @Column(unique = true)
    private String username;
    @NotNull @NotBlank @Column(unique = true)
    private String email;
    private String password;
    private String token;
    private Role role;

//    public void setPassword(String password)
//    {
//        if(!password.matches("(?=.*[a-z])"))
//            throw new InvalidCredentials("Passwords do not contains lower case letters!");
//
//        if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"))
//            throw new InvalidCredentials("Password not valid");
//
//        if(password.contains(username))
//            throw new InvalidCredentials("Passwords must not contains username ");
////        for(int i = 0; i < username.length()-4; i++)
////        {
////            String parte = username.substring(i, i+4);
////            if(password.contains(parte))
////                throw new InvalidCredentials("Passwords must not contains a part of the username");
////        }
//        this.password = password;
//    }
}
