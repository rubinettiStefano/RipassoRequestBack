package com.generation.ripassorequestback.services;

import com.generation.ripassorequestback.dtos.LoginDto;
import com.generation.ripassorequestback.dtos.RegisterDto;
import com.generation.ripassorequestback.dtos.UserOutputDto;
import com.generation.ripassorequestback.exceptions.InvalidCredentials;
import com.generation.ripassorequestback.model.entities.User;
import com.generation.ripassorequestback.model.enums.Role;
import com.generation.ripassorequestback.model.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    @Autowired
    private UserRepository repo;

    public String register(RegisterDto registerDto)
    {


        if(!registerDto.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"))
            throw new InvalidCredentials("Password not valid");

        User user = new User();
        user.setUsername(registerDto.getUsername());
        String hash = DigestUtils.md5Hex(registerDto.getPassword());
        user.setPassword(hash);
        user.setEmail(registerDto.getEmail());
        user.setRole(Role.STANDARD);

        System.out.println(hash);
        //genero un token in automatico
        user.setToken(UUID.randomUUID().toString());

        repo.save(user);

        return user.getToken();
    }

    public String login(LoginDto dto)
    {
        String hash = DigestUtils.md5Hex(dto.getPassword());
        Optional<User> op = repo.findByUsernameAndPassword(dto.getUsername(), hash);

        if(op.isEmpty())
            throw new InvalidCredentials("Invalid username or password");

        return op.get().getToken();
    }

    public User findUserByToken(String token)
    {
        Optional<User> op = repo.findByToken(token);

        if(op.isEmpty())
            throw new InvalidCredentials("Invalid token");

        return op.get();
    }

    public UserOutputDto readUserDto(String token)
    {
        User u = findUserByToken(token);


       UserOutputDto dto = new UserOutputDto();
       dto.setUsername(u.getUsername());
       dto.setEmail(u.getEmail());
       dto.setRole(u.getRole());
       return dto;
    }
}
