package com.weatherapp.weatherinfo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.weatherinfo.entity.WeatherData;
import com.weatherapp.weatherinfo.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
	private final WeatherService weatherService;

    
    public WeatherController(WeatherService weatherService) {
		super();
		this.weatherService = weatherService;
	}

  // our get requests will contact the below code for responses
	@GetMapping
    public ResponseEntity<WeatherData> getWeather(
            @RequestParam String pincode,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) String forDate) {
		
	    //parsing the date in string format to Date format	
		LocalDate date = LocalDate.parse(forDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return ResponseEntity.ok(weatherService.getWeather(pincode, date));
    }
}