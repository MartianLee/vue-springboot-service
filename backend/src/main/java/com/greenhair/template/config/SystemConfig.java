package com.greenhair.template.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApiConfig.class)
class SystemConfig {

    @Bean 
    public ApiConfig apiConfig(){
        return new ApiConfig();
    }
}