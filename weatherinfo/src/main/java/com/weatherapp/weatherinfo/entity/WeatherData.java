package com.weatherapp.weatherinfo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import com.weatherapp.weatherinfo.entity.WeatherDataKey;
import lombok.Data;

@Entity
@Data
public class WeatherData {
    @EmbeddedId
    private WeatherDataKey id;
    private Double temperature;
    private Double humidity;
    
    public WeatherData() {
		super();
	}
    
    public WeatherData(WeatherDataKey id, Double temperature, Double humidity) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.humidity = humidity;
	}
	
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