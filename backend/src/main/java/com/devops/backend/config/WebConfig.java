package com.devops.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS on all endpoints
                .allowedOrigins("http://localhost:3000") // Specify your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed HTTP methods
                .allowedHeaders("*") // Allow all headers
                .exposedHeaders("Authorization") // Specify any headers to expose to the client
                .allowCredentials(true) // Allow cookies to be included in CORS requests
                .maxAge(3600); // Set max age for pre-flight requests in seconds
    }
}
