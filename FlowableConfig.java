package com.example.flowable.config;

import org.flowable.ui.idm.conf.IdmEngineServicesAutoConfiguration;
import org.flowable.ui.modeler.conf.ModelerEngineServicesAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    IdmEngineServicesAutoConfiguration.class,
    ModelerEngineServicesAutoConfiguration.class
})
public class FlowableConfig {

    @Bean
    public IdmEngineConfiguration idmEngineConfiguration() {
        return new SpringIdmEngineConfiguration();
    }

    @Bean
    public IdmIdentityService idmIdentityService(IdmEngineConfiguration configuration) {
        return configuration.getIdmIdentityService();
    }
}