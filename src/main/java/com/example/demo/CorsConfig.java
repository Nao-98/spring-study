package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // APIのすべてのURL（/**）に対して適用する
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000") 
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}