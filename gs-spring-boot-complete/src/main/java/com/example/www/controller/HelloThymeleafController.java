package com.example.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloThymeleafController
{

    @GetMapping(value = "/HelloThymeleaf", produces = "text/html")
    public String hello(Model model)
    {
        // 모델에 데이터 추가
        model.addAttribute("message", "Thymeleaf!");

        return "hello";
    }

}
