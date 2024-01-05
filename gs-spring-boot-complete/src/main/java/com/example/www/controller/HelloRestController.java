package com.example.www.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController
{
    @GetMapping("/HelloRest")
    public String RestHello()
    {
        return "Greetings from Spring Boot!";
    }

}
