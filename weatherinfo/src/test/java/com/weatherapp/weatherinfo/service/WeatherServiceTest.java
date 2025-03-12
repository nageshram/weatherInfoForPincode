package com.weatherapp.weatherinfo.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.weatherapp.weatherinfo.config.WeatherConfig;
import com.weatherapp.weatherinfo.dtos.WeatherResponse;
import com.weatherapp.weatherinfo.dtos.WeatherResponse.Main;
import com.weatherapp.weatherinfo.entity.PincodeLocation;
import com.weatherapp.weatherinfo.entity.WeatherData;
import com.weatherapp.weatherinfo.entity.WeatherDataKey;
import com.weatherapp.weatherinfo.repository.PincodeLocationRepository;
import com.weatherapp.weatherinfo.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.Optional;

public class WeatherServiceTest {

    @Mock
    private PincodeLocationRepository pincodeLocationRepo;

    @Mock
    private WeatherDataRepository weatherDataRepo;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherConfig weatherConfig;

    @InjectMocks
    private WeatherService weatherService;

    private PincodeLocation pincodeLocation;
    private WeatherData weatherData;
    private WeatherResponse weatherResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup mock data
        pincodeLocation = new PincodeLocation();
        pincodeLocation.setPincode("560013");
        pincodeLocation.setLat(12.34);
        pincodeLocation.setLon(56.78);
        
        weatherData = new WeatherData();
        weatherData.setId(new WeatherDataKey("560013", LocalDate.now()));
        weatherData.setTemperature(25.0);
        weatherData.setHumidity(60);
        
        weatherResponse = new WeatherResponse();
        Main main = new Main();
        main.setTemp(25.0);
        main.setHumidity(60);
        weatherResponse.setMain(main);
        weatherResponse.getMain().setTemp(25);
        weatherResponse.getMain().setHumidity(60);
    }

    @Test
    void testGetWeather_WhenDataExists() {
        // Arrange
        when(weatherDataRepo.findById(any(WeatherDataKey.class))).thenReturn(Optional.of(weatherData));

        // Act
        WeatherData result = weatherService.getWeather("560013", LocalDate.now());

        // Assert
        assertNotNull(result);
        assertEquals(25.0, result.getTemperature());
        assertEquals(60, result.getHumidity());
    }

    @Test
    void testGetWeather_WhenDataDoesNotExist() {
        // Arrange
        when(weatherDataRepo.findById(any(WeatherDataKey.class))).thenReturn(Optional.empty());
        when(pincodeLocationRepo.findById(anyString())).thenReturn(Optional.of(pincodeLocation));
        when(restTemplate.getForObject(anyString(), eq(WeatherResponse.class), any(), any(), any())).thenReturn(weatherResponse);
        when(weatherDataRepo.save(any(WeatherData.class))).thenReturn(weatherData);

        // Act
        WeatherData result = weatherService.getWeather("560013", LocalDate.now());

        // Assert
        assertNotNull(result);
        assertEquals(25.0, result.getTemperature());
        assertEquals(60.0, result.getHumidity());
    }

    @Test
    void testFetchAndSaveWeather() {
        // Arrange
        when(pincodeLocationRepo.findById("560013")).thenReturn(Optional.of(pincodeLocation));
        when(restTemplate.getForObject(anyString(), eq(WeatherResponse.class), any(), any(), any())).thenReturn(weatherResponse);
        when(weatherDataRepo.save(any(WeatherData.class))).thenReturn(weatherData);

        // Act
        WeatherData result = weatherService.fetchAndSaveWeather("560013", LocalDate.now());

        // Assert
        assertNotNull(result);
        assertEquals(25.0, result.getTemperature());
        assertEquals(60.0, result.getHumidity());
    }
}

