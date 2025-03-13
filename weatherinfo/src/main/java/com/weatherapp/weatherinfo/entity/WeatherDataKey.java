package com.weatherapp.weatherinfo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Embeddable;
import lombok.Data;

/*
 * This is capable to store user submitted parameter such as for date and pincode
 * while submitting the request to our application
 * using this class  we are fetching weather data and pincode location(not completely but used as an object while fetching) 
 */

@Embeddable
@Data
public class WeatherDataKey implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private String pincode;
	
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate forDate;
    
	public WeatherDataKey() {
		
	}
	
	public WeatherDataKey(String pincode, LocalDate forDate) {
		this.pincode = pincode;
		this.forDate = forDate;
	}
	
	public WeatherDataKey(String pincode2, String string) {
	}
	
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@JsonFormat(pattern="yyyy-MM-dd")
	public LocalDate getForDate() {
		return forDate;
	}
	public void setForDate(LocalDate forDate) {
		this.forDate = forDate;
	}
	
}