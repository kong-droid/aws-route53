package com.example.route53.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi(){
        val info = new Info()
            .title("AWS Route53")
            .version("v1.0")
            .description("Route53 Test");
        return new OpenAPI().info(info);
    }
}
