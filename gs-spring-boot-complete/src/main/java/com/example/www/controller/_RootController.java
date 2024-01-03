package com.example.www.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.www.ClassUtils;

@Controller
public class _RootController
{

    private final Logger logger = LogManager.getLogger(getClass().getName());

    @GetMapping("/")
    public String index()
    {
        logger.info("▷ 클래스:함수명 |  {} : {} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());        

        return "_root";
    }

}
