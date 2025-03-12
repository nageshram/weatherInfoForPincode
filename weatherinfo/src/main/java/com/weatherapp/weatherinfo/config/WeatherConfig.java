package com.weatherapp.weatherinfo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "openweather")
@Data
public class WeatherConfig {
	private String apiKey;
    private Geocoding geocoding;
    
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Geocoding getGeocoding() {
		return geocoding;
	}

	public void setGeocoding(Geocoding geocoding) {
		this.geocoding = geocoding;
	}


    @Data
    public static class Geocoding {
        private String country;

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
    }
}