package com.example.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class _RootController
{

    @GetMapping("/")
    public String index()
    {
        return "Root!!<a href='/showendpoints'>/showendpoints</a>";
    }

}
