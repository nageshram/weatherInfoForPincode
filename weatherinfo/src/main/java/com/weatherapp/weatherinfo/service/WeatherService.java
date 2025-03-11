package com.weatherapp.weatherinfo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.weatherapp.weatherinfo.config.WeatherConfig;
import com.weatherapp.weatherinfo.dtos.GeocodingResponse;
import com.weatherapp.weatherinfo.dtos.WeatherResponse;
import com.weatherapp.weatherinfo.entity.PincodeLocation;
import com.weatherapp.weatherinfo.entity.WeatherData;
import com.weatherapp.weatherinfo.entity.WeatherDataKey;
import com.weatherapp.weatherinfo.repository.PincodeLocationRepository;
import com.weatherapp.weatherinfo.repository.WeatherDataRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final PincodeLocationRepository pincodeLocationRepo;
    private final WeatherDataRepository weatherDataRepo;
    private  final RestTemplate restTemplate;
    private  final WeatherConfig weatherConfig; 
    
    public WeatherService(
            PincodeLocationRepository pincodeLocationRepo,
            WeatherDataRepository weatherDataRepo,
            RestTemplate restTemplate,
            WeatherConfig weatherConfig) {
        this.pincodeLocationRepo = pincodeLocationRepo;
        this.weatherDataRepo = weatherDataRepo;
        this.restTemplate = restTemplate;
        this.weatherConfig = weatherConfig;
    }

    public WeatherData getWeather(String pincode, LocalDate date) {
        WeatherDataKey key = new WeatherDataKey(pincode, date);
        return weatherDataRepo.findById(key)
                .orElseGet(() -> fetchAndSaveWeather(pincode, date));
    }

    private WeatherData fetchAndSaveWeather(String pincode, LocalDate date) {
        PincodeLocation location = pincodeLocationRepo.findById(pincode)
                .orElseGet(() -> fetchAndSaveLocation(pincode));
             
        WeatherResponse weather = restTemplate.getForObject(
                "https://api.openweathermap.org/data/1.0/weather?lat={lat}&lon={lon}&appid={key}&units=metric",
                WeatherResponse.class,
                location.getLat(), location.getLon(), weatherConfig.getApiKey() // Use config class
        );
        
        WeatherData data = new WeatherData();
        data.setId(new WeatherDataKey(pincode, date));
        data.setTemperature(weather.getMain().getTemp());
        data.setHumidity(Double.valueOf(weather.getMain().getHumidity()));
        return weatherDataRepo.save(data);
    }

    private PincodeLocation fetchAndSaveLocation(String pincode) {
        GeocodingResponse response = restTemplate.getForObject(
                "https://api.openweathermap.org/data/1.0/weather?lat={lat}&lon={lon}&appid={key}&units=metric",
                GeocodingResponse.class,
                pincode, weatherConfig.getGeocoding().getCountry(), weatherConfig.getApiKey() // Use config class
        );

        PincodeLocation location = new PincodeLocation();
        location.setPincode(pincode);
        location.setLat(response.getLat());
        location.setLon(response.getLon());
        return pincodeLocationRepo.save(location);
    }
}