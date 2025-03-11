package com.weatherapp.weatherinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.weatherapp.weatherinfo.config.WeatherConfig;


@SpringBootApplication
@EnableConfigurationProperties(WeatherConfig.class) // Enable configuration properties
public class WeatherInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherInfoApplication.class, args);
    }
}
