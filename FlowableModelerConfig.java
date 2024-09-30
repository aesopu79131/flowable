package com.example.flowable.config;

import org.flowable.ui.modeler.conf.ModelerEngineServicesAutoConfiguration;
import org.flowable.ui.modeler.properties.FlowableModelerAppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(FlowableModelerAppProperties.class)
@Import(ModelerEngineServicesAutoConfiguration.class)
public class FlowableModelerConfig {
    
}
