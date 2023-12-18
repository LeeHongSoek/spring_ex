package com.example.www.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.www.ClassUtils;

@RestController
public class HelloRestController
{
	private final Logger logger = LogManager.getLogger(getClass().getName());

    @GetMapping("/HelloRest")
    public String RestHello()
    {
    	logger.info("◆ 클래스: {} ", ClassUtils.getShtClassNm(getClass()));
    	
        return "Greetings from Spring Boot!";
    }

}
