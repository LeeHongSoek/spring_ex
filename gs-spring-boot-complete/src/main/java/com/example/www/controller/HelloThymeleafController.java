package com.example.www.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.www.ClassUtils;

@Controller
public class HelloThymeleafController
{
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @GetMapping(value = "/HelloThymeleaf", produces = "text/html")
    public String hello(Model model)
    {
        logger.info("◇ 클래스:함수명 |  {} : {} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());
        
        // 모델에 데이터 추가
        model.addAttribute("message", "Thymeleaf!");

        return "hello";
    }

}
