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
/*
 * NOTE classses names :
 * Data flow:
 * weather data request (with parameters pincode and fordate):
 * 
 * if the latitude and longitude info is not in our database :
 * 			we will get them using geocodindg api and catching the response using geocodingResponse and save it as PincodeLocation
 * 			after getting location data we will fetch the data from api and catch that response using weatherResponse.
 * 			we will save both weather data with weatherKey data and return the same to user as WeatherData 
 * 
 * else if location is present in our db we will fetch only weather data from api and return the response.
 */

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

     WeatherData fetchAndSaveWeather(String pincode, LocalDate date) {
        try {
            // Fetch location for the pincode, or fetch and save if not found
            PincodeLocation location = pincodeLocationRepo.findById(pincode)
                    .orElseGet(() -> fetchAndSaveLocation(pincode));
                 
            // Fetch weather data using the OpenWeather API
            WeatherResponse weather = restTemplate.getForObject(
                    "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={key}&units=metric",
                    WeatherResponse.class,
                    location.getLat(), location.getLon(), weatherConfig.getApiKey()
            );

            // Create and set WeatherData entity
            WeatherData data = new WeatherData();
            data.setId(new WeatherDataKey(pincode, date));  // Set composite key
            data.setTemperature(weather.getMain().getTemp());
            data.setHumidity(Double.valueOf(weather.getMain().getHumidity()));

            // Save WeatherData and return it
            return weatherDataRepo.save(data);

        } catch (Exception e) {
            // Log the error and throw a custom exception or return a fallback value
            // This can be replaced by specific exceptions like HttpClientErrorException for HTTP errors, etc.
            // You could also add specific handling for different error types.
            // For example, handle HTTP exceptions, API failures, etc.

            // Log the error (You should use a logging framework like SLF4J, but here it's just a print statement)
            System.err.println("Error occurred while fetching and saving weather data: " + e.getMessage());
            e.printStackTrace();  // Log the stack trace for debugging
            
            // Optionally, you can throw a custom exception or return a default/fallback value
            throw new RuntimeException("Failed to fetch and save weather data for pincode: " + pincode + " and date: " + date, e);
        }
    }
    private PincodeLocation fetchAndSaveLocation(String pincode) {
        String url = "http://api.openweathermap.org/geo/1.0/zip?zip={zip},{country}&appid={key}";
        
        try {
            
            // Call the OpenWeatherMap API
            GeocodingResponse response = restTemplate.getForObject(
                    url,
                    GeocodingResponse.class,
                    pincode, weatherConfig.getGeocoding().getCountry(), weatherConfig.getApiKey()
            );

            // Log the API response
            System.out.println("API Response: " + response);

            // Check if the API response is valid
            if (response == null) {
                throw new RuntimeException("Failed to fetch location data for pincode: " + pincode);
            }

            // Save the location data to the database
            PincodeLocation location = new PincodeLocation();
            location.setPincode(pincode);
            location.setLat(response.getLat());
            location.setLon(response.getLon());
            return pincodeLocationRepo.save(location);
        } catch (Exception e) {
            throw new RuntimeException("Error calling OpenWeatherMap Geocoding API: " + e.getMessage(), e);
        }
    }
}