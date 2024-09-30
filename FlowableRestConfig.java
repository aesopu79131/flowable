package com.example.flowable.config;

import org.flowable.rest.service.api.RestResponseFactory;
import org.flowable.spring.boot.RestApiAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.FilterRegistrationBean;
import org.springframework.web.filter.UrlBasedCorsConfigurationSource;

@Configuration
@EnableFlowableRest
@AutoConfigureAfter(RestApiAutoConfiguration.class)
public class FlowableRestConfig {

    @Bean
    public RestResponseFactory restResponseFactory() {
        return new RestResponseFactory();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
