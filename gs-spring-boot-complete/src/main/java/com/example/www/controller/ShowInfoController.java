package com.example.www.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.example.www.ClassUtils;
import com.example.www.vo.BeanInfo;

import jakarta.servlet.http.HttpServletRequest;

 

@Controller
public class ShowInfoController
{

    private final Logger logger = LogManager.getLogger(getClass().getName());
    private final RequestMappingHandlerMapping handlerMapping;

    public ShowInfoController(@Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping handlerMapping)
    {
        this.handlerMapping = handlerMapping;
    }


    @GetMapping("/showendpoints")
    public ModelAndView showEndpoints(Model model, HttpServletRequest request)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        // 동적으로 현재 세션에 걸려있는 도메인 주소 가져오기
        String baseDomain = request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("baseDomain", baseDomain);

        List<String[]> endpointList = new ArrayList<>();

        // handlerMapping을 사용하여 endpointList 채우기
        handlerMapping.getHandlerMethods().forEach((key, value) ->
        {
            String strKey = key.getPatternValues().toString().replaceAll("\\[|\\]", "");
            
            String strValue = value.getMethod().toString();
            String requestMethod = key.getMethodsCondition().getMethods().toString();
            
            MethodInfo parsedInfo = parseMethodSignature(strValue);
            
            if ("com.example.www.controller".equals(parsedInfo.packageName))
            {
                String[] endpointArray = { requestMethod
                                         , strKey
                                         //, parsedInfo.accessModifier
                                         //, parsedInfo.packageName
                                         , parsedInfo.className
                                         , parsedInfo.returnType
                                         , parsedInfo.methodName
                                         , parsedInfo.param
                                         };
                endpointList.add(endpointArray);
            }
        });

        Collections.sort(endpointList, Comparator.comparing(endpointArray -> endpointArray[1]));

        endpointList.forEach(endpointArray -> logger.info("◇ {} : {}, {}: {}", endpointArray[0], endpointArray[1], endpointArray[2]));

        // Add sorted entries to the model
        model.addAttribute("endpointList", endpointList);

        return new ModelAndView("showendpoints");
    }

    class MethodInfo
    {
        public String accessModifier;
        public String returnType;
        
        public String methodFullName;
        public String packageName;
        public String className;
        public String methodName;
        public String param;
    }

    private MethodInfo parseMethodSignature(String methodSignature)
    {
        MethodInfo methodInfo = new MethodInfo();

        String[] parts = methodSignature.split(" ");
        
        methodInfo.accessModifier = parts[0];
        methodInfo.returnType = parts[1];
        String methodName = parts[2];        

        int lastParenthesisIndex1 = methodName.lastIndexOf("(");
        int lastParenthesisIndex2 = methodName.lastIndexOf(")");
        int lastDotIndex = methodName.lastIndexOf(".", lastParenthesisIndex1);

        methodInfo.methodFullName = methodName;
        methodInfo.methodName = methodName.substring(lastDotIndex + 1, lastParenthesisIndex1);
        methodInfo.className = methodName.substring(methodName.lastIndexOf(".", lastDotIndex - 1) + 1, lastDotIndex);
        methodInfo.packageName = methodName.substring(0, methodName.lastIndexOf(methodInfo.className)-1);
        methodInfo.param = methodName.substring(lastParenthesisIndex1, lastParenthesisIndex2+1);

        return methodInfo;
    }


    
    @Autowired
    private BeansEndpoint beansEndpoint;

    @GetMapping("/showbeans")
    public String showBeans(Model model)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

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
                    String modifiedBeanClass = beanClass.replace(basePackage + ".", "");
                    BeanInfo beanInfo = new BeanInfo(context, beanName, modifiedBeanClass);
                    beanInfoList.add(beanInfo);
                }
            }
        }

        model.addAttribute("beanInfoList", beanInfoList);
        return "showbeans";
    }
}
