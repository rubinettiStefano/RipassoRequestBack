package com.generation.ripassorequestback.controllers;

import com.generation.ripassorequestback.dtos.LoginDto;
import com.generation.ripassorequestback.dtos.RegisterDto;
import com.generation.ripassorequestback.dtos.UserOutputDto;
import com.generation.ripassorequestback.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public void register(@RequestBody RegisterDto dto, HttpServletResponse response)
    {
        String tokenUtente = userService.register(dto);

        Cookie cookie = new Cookie("token", tokenUtente);
        response.addCookie(cookie);
    }

    @PostMapping("login")
    public void login(@RequestBody LoginDto dto, HttpServletResponse response)
    {
        String tokenUtente = userService.login(dto);

        Cookie cookie = new Cookie("token", tokenUtente);
        response.addCookie(cookie);
    }

    @GetMapping("/userinformation")
    public UserOutputDto getUserInfo(@CookieValue String token)
    {
        return userService.readUserDto(token);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String gestisciTutto(Exception e)
    {
        return "Operazione fallita, ulteriori dettagli "+e.getMessage();
    }
}
