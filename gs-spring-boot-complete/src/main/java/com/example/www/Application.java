package com.example.www;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//이 어노테이션을 사용하여 MyBatis Mapper 스캔 범위 설정
@SpringBootApplication
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx)
    {
        return args ->
        {
            logger.info("◆ 클래스: {} ", ClassUtils.getShtClassNm(getClass()));

            logger.info("◇ {} ", "com.example.www 패키지에 있는 빈들을 검사해 보겠습니다.");

            // com.example.www 패키지 아래의 모든 빈을 가져옵니다.
            Map<String, Object> beansInPackage = ctx.getBeansOfType(Object.class);

            for (Map.Entry<String, Object> entry : beansInPackage.entrySet())
            {
                String beanName = entry.getKey();
                Object beanInstance = entry.getValue();

                // 특정 패키지에 속한 빈들만 출력합니다.
                if (beanInstance.getClass().getPackage().getName().startsWith("com.example."))
                {
                    logger.info("◇ 빈: {} ", ClassUtils.getShtClassNm(beanInstance.getClass()) + " : " + beanName);
                }
            }
        };
    }

    
/*
 
# application.properties

# com.example.www 패키지에 속한 빈들의 이름을 설정
com.example.www.beans-to-inspect=bean1,bean2,bean3
    
    
    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx, @Value("${com.example.www.beans-to-inspect}") String[] beansToInspect) {
        return args -> {
            logger.info("◆ 클래스: {} ", ClassUtils.getShortClassName(getClass()));
            logger.info("◇ {} ", "com.example.www 패키지에 있는 빈들을 검사해 보겠습니다.");

            for (String beanName : beansToInspect) {
                Object beanInstance = ctx.getBean(beanName);
                logger.info("◇ 빈: {} ", ClassUtils.getShortClassName(beanInstance.getClass()) + " : " + beanName);
            }
        };
    }
    
CommandLineRunner를 사용하여 빈들을 검색하고 출력하는 로직은 특정 패키지나 
특정 조건을 만족하는 빈들을 동적으로 파악하고자 할 때 유용합니다. 
아래는 이를 응용할 수 있는 몇 가지 상황입니다:

특정 패키지 또는 모듈의 빈 검색: 

특정 패키지에 속한 빈들을 찾아서 특정한 작업을 수행하고자 할 때 유용합니다. 
예를 들어, 특정 패키지에 속한 빈들을 찾아서 자동으로 설정되어야 하는지 여부를 검사하거나, 
특정 패키지의 빈들을 로그에 출력하는 등의 작업에 활용할 수 있습니다.

동적인 빈 관리: 

런타임 시점에서 동적으로 빈을 추가하거나 관리해야 하는 상황에서 사용할 수 있습니다. 
특정 조건을 만족하는 빈들을 동적으로 추가하거나 삭제하는 작업에 활용할 수 있습니다.

사용자 지정 로직 적용: 

특정 빈들에 대해 사용자 지정 로직을 적용하고자 할 때 사용할 수 있습니다. 
예를 들어, 특정 패키지의 빈들 중에서 어떤 조건을 만족하는 빈들에 대해 특별한 로직을 적용하고자 할 때 활용할 수 있습니다.

디버깅 및 모니터링: 

런타임 시에 어떤 빈들이 활성화되고 있는지를 확인하고자 할 때 사용할 수 있습니다. 특히, 개발 및 디버깅 단계에서 빈들의 동적인 상태를 살펴보는 데 유용합니다.


이러한 경우들은 주로 개발자가 애플리케이션의 동적인 부분을 이해하고 디버깅하는 데 도움을 줍니다. 
그러나 일반적인 애플리케이션 로직에서는 이러한 동적인 빈 관리가 필요하지 않을 수 있습니다. 
주로 초기 설정이나 특정 모듈의 기능을 확장하고자 할 때 이러한 접근 방식을 사용할 수 있습니다.

*/
    
}
