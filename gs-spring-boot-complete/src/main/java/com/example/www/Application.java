package com.example.www;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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

}
