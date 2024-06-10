package com.example.www.config;

/**
 * https://infinitecode.tistory.com/65
 * 
 * 스프링 버전이 3.x대로 넘어오면서 기존에 사용하던 Springfox가 호환이 되지않는 문제가 있습니다.
 * 해결을 위한 SpringDocs 사용법에 대한 가이드 포스팅
 * 
 */
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration    // 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("CodeArena Swagger")
                .description("CodeArena 유저 및 인증 , ps, 알림에 관한 REST API")
                .version("1.0.0");
    }
}