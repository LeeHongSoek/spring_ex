package com.example.www;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature; // 여기에 추가
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;

@Aspect
@Component
public class LoggingAspect
{

    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorImpl.class);

    @Before("execution(* com.example.www.controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint)
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 메소드에 선언된 모든 어노테이션을 가져오기
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations)
        {
            // 어노테이션 이름과 값을 로깅
            logger.info("어노테이션|  ▶ {} : {}", annotation.annotationType().getSimpleName(), getAnnotationValue(annotation));            
        }
        
        // JoinPoint를 이용하여 메서드 서명 출력
        //logger.info("{},{}", joinPoint.getTarget().toString(), joinPoint.getArgs().toString());
        //logger.info("웹반응   |   ▷ {} ", joinPoint.getSignature().toShortString());
        logger.info("웹반응    |  ▷ {} ", joinPoint.getSignature().toString());
        logger.info("--------------------------------------------------------------------------------------");
    }

    @Before("execution(* com.example.www.mapper.*.*(..))")
    public void logBeforeMapper(JoinPoint joinPoint)
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 메소드에 선언된 모든 어노테이션을 가져오기
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations)
        {
            // 어노테이션 이름과 값을 로깅
            logger.info("mapper    | {} : {}", annotation.annotationType().getSimpleName(), getAnnotationValue(annotation));
            logger.info("--------------------------------------------------------------------------------------");
        }
    }

    // 어노테이션의 값을 가져오는 도우미 메소드
    private String getAnnotationValue(Annotation annotation)
    {
        // 어노테이션에서 필요한 정보를 추출하는 로직을 추가
        try
        {
            Method valueMethod = annotation.annotationType().getMethod("value");

            // 배열인 경우 배열 내용을 문자열로 변환
            Object value = valueMethod.invoke(annotation);
            if (value != null && value.getClass().isArray())
            {
                StringBuilder arrayAsString = new StringBuilder("[");
                int length = Array.getLength(value);
                for (int i = 0; i < length; i++)
                {
                    arrayAsString.append(Array.get(value, i));
                    if (i < length - 1)
                    {
                        arrayAsString.append(", ");
                    }
                }
                arrayAsString.append("]");
                return arrayAsString.toString();
            } else
            {
                return String.valueOf(value);
            }
        } catch (Exception e)
        {
            logger.error("어노테이션 값을 가져오는 도중 오류 발생: {}", e.getMessage());
            return "";
        }
    }
}
