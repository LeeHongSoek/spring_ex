package com.example.www;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Before("execution(* com.example.www.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint)
    {
        // JoinPoint를 이용하여 메서드 서명 출력
        //logger.info("{},{}", joinPoint.getTarget().toString(), joinPoint.getArgs().toString());
        logger.info("joinPoint     | ▷  {} ", joinPoint.getSignature().toShortString());
        //logger.info("joinPoint     | ▷  {} ", joinPoint.getSignature().toString());
        logger.info("--------------------------------------------------------------------------------------");
    }
}
