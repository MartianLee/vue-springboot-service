package com.greenhair.template.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
public class ApiConfig {
    
    private String url;
    private String contentsType;
    private String host;
    private String key;
    private String teamUrl;
    private String matchUrl;

}