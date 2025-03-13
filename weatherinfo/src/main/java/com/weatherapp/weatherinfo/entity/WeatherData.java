package com.weatherapp.weatherinfo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weatherapp.weatherinfo.entity.WeatherDataKey;
import lombok.Data;

/**
 * This is the main class for storing weather data in repository as well as all operations
 * our application responses is made using this class
 * Weather key is an object to fetch the request parameters and used to store the the same inside weather data
 */


@Entity
@Data
public class WeatherData {
    @EmbeddedId
    @JsonProperty("id") 
    private WeatherDataKey id;
    private Double temperature;
    private Double humidity;
    
    public WeatherData() {
		//super();
	}
    
    public WeatherData(WeatherDataKey id, Double temperature, Double humidity) {
		this.id = id;
		this.temperature = temperature;
		this.humidity = humidity;
	}

    @JsonProperty("id") 
	public WeatherDataKey getId() {
		return id;
	}
	public void setId(WeatherDataKey id) {
		this.id = id;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
    
}