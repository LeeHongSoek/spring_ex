package com.example.com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.beans.BeansEndpoint;
import org.springframework.boot.actuate.beans.BeansEndpoint.BeansDescriptor;
import org.springframework.boot.actuate.beans.BeansEndpoint.ContextBeansDescriptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.com.vo.BeanInfo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShowInfoController
{

    private final RequestMappingHandlerMapping handlerMapping;
    //private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final Logger logger = LogManager.getLogger(getClass().getName());


    public ShowInfoController(@Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping handlerMapping)
    {
        this.handlerMapping = handlerMapping;
    }

    @GetMapping("/showendpoints")
    public ModelAndView showEndpoints(Model model, HttpServletRequest request)
    {        
        logger.info("로그: {} ", getClass().getName());

        // 동적으로 현재 세션에 걸려있는 도메인 주소 가져오기
        String baseDomain = request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("baseDomain", baseDomain);

        Map<String, String> endpointMap = new HashMap<>();
        
        handlerMapping.getHandlerMethods().forEach((key, value) ->
        {
            String strKey = key.getPatternValues().toString().replaceAll("\\[|\\]", "");
            String strValue = value.getMethod().toString();
            
            endpointMap.put(strKey, strValue);
            
            logger.info("로그: {} : {}", strKey, strValue);
        });

        model.addAttribute("endpointMap", endpointMap);
        return new ModelAndView("showendpoints");
    }

    @Autowired
    private BeansEndpoint beansEndpoint;

    @GetMapping("/showbeans")
    public String showBeans(Model model)
    {
        BeansDescriptor beansDescriptor = beansEndpoint.beans();

        List<BeanInfo> beanInfoList = new ArrayList<>();

        String basePackage = this.getClass().getPackage().getName();

        for (Map.Entry<String, ContextBeansDescriptor> contextEntry : beansDescriptor.getContexts().entrySet())
        {
            String context = contextEntry.getKey();
            ContextBeansDescriptor contextBeansDescriptor = contextEntry.getValue();

            for (Map.Entry<String, BeansEndpoint.BeanDescriptor> beanEntry : contextBeansDescriptor.getBeans().entrySet())
            {
                String beanName = beanEntry.getKey();
                String beanClass = beanEntry.getValue().getType().getName();

                // 패키지가 현재 클래스가 속한 패키지로 시작하는 빈들만 추가
                if (beanClass.startsWith(basePackage))
                {
                    String modifiedBeanClass = beanClass.replace(basePackage+".", "");
                    BeanInfo beanInfo = new BeanInfo(context, beanName, modifiedBeanClass);
                    beanInfoList.add(beanInfo);
                }
            }
        }

        model.addAttribute("beanInfoList", beanInfoList);
        return "showbeans";
    }
}
