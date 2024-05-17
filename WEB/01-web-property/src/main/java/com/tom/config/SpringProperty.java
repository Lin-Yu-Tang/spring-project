package com.tom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "spring")
public class SpringProperty {

    private Profiles profiles;

    @Data
    public static class Profiles {
        private String active;  // mode
    }

}