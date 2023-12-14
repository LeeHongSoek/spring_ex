package com.example.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.beans.BeansEndpoint;
import org.springframework.boot.actuate.beans.BeansEndpoint.BeansDescriptor;
import org.springframework.boot.actuate.beans.BeansEndpoint.ContextBeansDescriptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.com.vo.BeanInfo;

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
