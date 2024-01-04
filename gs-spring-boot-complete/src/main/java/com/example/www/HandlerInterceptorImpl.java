package com.example.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HandlerInterceptorImpl implements HandlerInterceptor
{
    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorImpl.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // 요청이 들어올 때 실행되는 부분
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // @RequestMapping 어노테이션이 있는지 확인
            if (handlerMethod.hasMethodAnnotation(org.springframework.web.bind.annotation.RequestMapping.class))
            {
                // 요청이 @RequestMapping에 의해 처리되는 경우에만 로그 출력
                logger.info("======================================================================================");                
                logger.info("웹요청        | ◀ [{}] {}", request.getMethod(), request.getRequestURI());
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        // 요청 처리가 끝나고 View가 렌더링 되기 전에 실행되는 부분
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        // 요청 처리가 완료된 후 실행되는 부분
    }
}
