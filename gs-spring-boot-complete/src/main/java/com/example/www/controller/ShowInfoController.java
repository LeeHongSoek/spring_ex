package com.example.www.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
/*
    private String formatHandlerMethod(String handlerMethod)
    {
        // 클래스명과 함수명 사이의 마지막 '.'를 ':', 괄호 내부의 '.'를 '/'로 대체
        // handlerMethod = handlerMethod.replaceAll("\\.(?!.*\\))", " : ").replaceAll("\\.(?=[^()]*\\))", " / ");
        return handlerMethod;
    }
  */  
    private String formatHandlerMethod(String handlerMethod) {
        // 1. 문장을 분리
        String[] parts = handlerMethod.split("\\(");
        String methodSignature = parts[0];
        String packageSignature = null;
        String clseeSignature = null;
        String parameters = parts[1].substring(0, parts[1].lastIndexOf(")"));
        String[] parameterArray = parameters.split(", ");

        // 2. 클래스명과 함수명 변환
        int lastDotIndex = methodSignature.lastIndexOf(".");
        if (lastDotIndex != -1) {
            methodSignature = methodSignature.substring(0, lastDotIndex) + " : " + methodSignature.substring(lastDotIndex + 1);
            
            lastDotIndex = methodSignature.substring(0, lastDotIndex).lastIndexOf(".");
            if (lastDotIndex != -1) {
                methodSignature = methodSignature.substring(0, lastDotIndex) + " : " + methodSignature.substring(lastDotIndex + 1);

                packageSignature = null;
                clseeSignature = null;
            }
        }

        // 3. 각 파라미터 변환
        for (int i = 0; i < parameterArray.length; i++) {
            int lastDotIndexParam = parameterArray[i].lastIndexOf(".");
            if (lastDotIndexParam != -1) {
                parameterArray[i] = parameterArray[i].substring(0, lastDotIndexParam) + " / " + parameterArray[i].substring(lastDotIndexParam + 1);
            }
        }

        // 4. 결과 문자열 조합
        String formattedMethod = methodSignature + "(" + String.join(", ", parameterArray) + ")";
        return formattedMethod;
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

            // Create an array for each endpoint
            String[] endpointArray =
            { strKey, requestMethod, formatHandlerMethod(strValue) };
            endpointList.add(endpointArray);
        });

        // Sort the list based on the endpoint pattern (strKey)
        Collections.sort(endpointList, Comparator.comparing(endpointArray -> endpointArray[0]));

        // Log and print the sorted entries
        endpointList.forEach(endpointArray -> logger.info("◇ {} : {}, {}: {}", endpointArray[0], endpointArray[1], endpointArray[2]));

        // Add sorted entries to the model
        model.addAttribute("endpointList", endpointList);

        return new ModelAndView("showendpoints");
    }

    @GetMapping("/showendpoints_")
    public ModelAndView showEndpoints_(Model model, HttpServletRequest request)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        // 동적으로 현재 세션에 걸려있는 도메인 주소 가져오기
        String baseDomain = request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("baseDomain", baseDomain);

        Map<String, List<String>> endpointMap = new HashMap<>();

        // handlerMapping을 사용하여 endpointMap 채우기
        handlerMapping.getHandlerMethods().forEach((key, value) ->
        {
            String strKey = key.getPatternValues().toString().replaceAll("\\[|\\]", "");
            String strValue = value.getMethod().toString();
            String requestMethod = key.getMethodsCondition().getMethods().toString();

            // Create a list for each endpoint containing the method and value
            List<String> methodList = endpointMap.computeIfAbsent(strKey, k -> new ArrayList<>());
            methodList.add(requestMethod + ": " + strValue);
        });

        // Transform the map into a list of entries
        List<Map.Entry<String, List<String>>> sortedEntries = new ArrayList<>(endpointMap.entrySet());

        // Sort the entries based on the endpoint key
        Collections.sort(sortedEntries, Comparator.comparing(Map.Entry::getKey));

        // Log and print the sorted entries
        sortedEntries.forEach(entry ->
        {
            String strKey = entry.getKey();
            List<String> methodList = entry.getValue();

            logger.info("◇ {} : {}", strKey, methodList);
        });

        // Add sorted entries to the model
        model.addAttribute("endpointMap", sortedEntries);

        return new ModelAndView("showendpoints");
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
