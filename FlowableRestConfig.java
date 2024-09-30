package com.example.flowable.config;

import org.flowable.rest.service.api.RestResponseFactory;
import org.flowable.spring.boot.RestApiAutoConfiguration;
import org.flowable.spring.boot.rest.EnableFlowableRest;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableFlowableRest
@AutoConfigureAfter(RestApiAutoConfiguration.class)
public class FlowableRestConfig {

    @Bean
    public RestResponseFactory restResponseFactory() {
        return new RestResponseFactory();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
