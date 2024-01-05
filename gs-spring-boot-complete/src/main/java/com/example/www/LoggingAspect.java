package com.example.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorImpl.class);

    @Before("execution(* com.example.www.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint)
    {
        // JoinPoint를 이용하여 메서드 서명 출력
        //logger.info("{},{}", joinPoint.getTarget().toString(), joinPoint.getArgs().toString());
        //logger.info("웹반응   |   ▷ {} ", joinPoint.getSignature().toShortString());
        logger.info("웹반응   |   ▷ {} ", joinPoint.getSignature().toString());
        logger.info("--------------------------------------------------------------------------------------");
    }
}
