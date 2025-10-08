package com.generation.ripassorequestback;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ControllerEsempio
{
    @GetMapping("letturaDati")
    public BodyDto lettura(HttpServletResponse response)
    {
        BodyDto res = new BodyDto();
        res.setTesto("ciao");
        res.setNumero(12);

        Cookie cookie = new Cookie("paperino", "pippo");
        cookie.setMaxAge(3600); // opzionale, durata in secondi (es: 1 ora)
        response.addCookie(cookie);

        return res;
    }

    @GetMapping("stampa")
    public void stampa(@CookieValue String paperino)
    {
        System.out.println(paperino);
    }
}
