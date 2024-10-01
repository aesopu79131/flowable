package com.example.flowable.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowableConfig {

    @Bean
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> customProcessEngineConfigurer() {
        return processEngineConfiguration -> {
            processEngineConfiguration.setTenantCheckEnabled(true);
            processEngineConfiguration.setFallbackToDefaultTenant(true);
        };
    }
}