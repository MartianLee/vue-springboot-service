package com.greenhair.template.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
@EnableConfigurationProperties(ApiConfig.class)
class SystemConfig {

    @Bean 
    public ApiConfig apiConfig(){
        return new ApiConfig();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}