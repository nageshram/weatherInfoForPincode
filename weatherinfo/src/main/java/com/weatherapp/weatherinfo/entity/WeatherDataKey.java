package com.weatherapp.weatherinfo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class WeatherDataKey implements Serializable {
    private String pincode;
    private LocalDate forDate;
	public WeatherDataKey(String pincode, LocalDate forDate) {
		super();
		this.pincode = pincode;
		this.forDate = forDate;
	}
	public WeatherDataKey(String pincode2, String string) {
		// TODO Auto-generated constructor stub
	}
}