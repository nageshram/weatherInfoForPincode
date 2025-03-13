package com.weatherapp.weatherinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	//Manually created bean 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}