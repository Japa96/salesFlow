package com.SalesFlowSA.SalesFlow.util.Cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63342")  // ou "*"
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE")
                .allowedHeaders("Content-Type")
                .allowCredentials(true);
    }
}

