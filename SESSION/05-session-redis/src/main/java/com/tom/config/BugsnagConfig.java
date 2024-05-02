package com.tom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.bugsnag.Bugsnag;
import com.bugsnag.BugsnagSpringConfiguration;

@Configuration
@Import(BugsnagSpringConfiguration.class)
public class BugsnagConfig {
	
    @Bean
    public Bugsnag bugsnag() {
        return new Bugsnag("adb72c09d3a8d3a1519cb1c53592adbd");
    }
}