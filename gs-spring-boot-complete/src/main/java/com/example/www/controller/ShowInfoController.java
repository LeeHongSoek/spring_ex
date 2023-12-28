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

    @GetMapping("/showendpoints")
    public ModelAndView showEndpoints(Model model, HttpServletRequest request)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        // 동적으로 현재 세션에 걸려있는 도메인 주소 가져오기
        String baseDomain = request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("baseDomain", baseDomain);

        Map<String, String> endpointMap = new HashMap<>();

        // handlerMapping을 사용하여 endpointMap 채우기
        handlerMapping.getHandlerMethods().forEach((key, value) ->
        {
            String strKey = key.getPatternValues().toString().replaceAll("\\[|\\]", "");
            String strValue = value.getMethod().toString();

            endpointMap.put(strKey, strValue);
        });

        // Map의 엔트리를 리스트로 변환하여 정렬 가능하도록 함
        List<Map.Entry<String, String>> sortedEntries = new ArrayList<>(endpointMap.entrySet());

        // 키(strKey)를 기준으로 정렬
        Collections.sort(sortedEntries, Comparator.comparing(Map.Entry::getKey));

        // 정렬된 엔트리 출력
        sortedEntries.forEach(entry ->
        {
            String strKey = entry.getKey();
            String strValue = entry.getValue();

            logger.info("◇ {} : {}", strKey, strValue);
        });

        // 정렬된 엔트리를 모델에 추가
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
