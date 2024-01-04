package com.example.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class _RootController
{

    @GetMapping("/")
    public String index()
    {
        return "_root";
    }

}
