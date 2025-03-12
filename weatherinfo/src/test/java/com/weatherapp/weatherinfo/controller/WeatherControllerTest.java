package com.weatherapp.weatherinfo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.weatherapp.weatherinfo.entity.WeatherData;
import com.weatherapp.weatherinfo.entity.WeatherDataKey;
import com.weatherapp.weatherinfo.service.WeatherService;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    private MockMvc mockMvc;
    private WeatherData mockWeatherData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();

        // Setup mock data for WeatherData
        mockWeatherData = new WeatherData();
        WeatherDataKey key = new WeatherDataKey();
        key.setPincode("560001");
        key.setForDate(LocalDate.parse("2023-01-01", DateTimeFormatter.ISO_LOCAL_DATE));  // Use a specific date for testing
        mockWeatherData.setId(key);
        mockWeatherData.setTemperature(25.0);
        mockWeatherData.setHumidity(60.0);
    }

    @Test
    void testGetWeather() throws Exception {
        // Arrange
        when(weatherService.getWeather("560001", LocalDate.parse("2023-01-01", DateTimeFormatter.ISO_LOCAL_DATE)))
            .thenReturn(mockWeatherData);
      

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather?pincode=560001&forDate=2023-01-01"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.pincode").value("560001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.forDate").value("2023-01-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(60.0));
    }
}
