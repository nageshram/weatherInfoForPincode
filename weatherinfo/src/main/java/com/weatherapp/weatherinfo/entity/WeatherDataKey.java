package com.weatherapp.weatherinfo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class WeatherDataKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String pincode;
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate forDate;
    
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
	public WeatherDataKey() {
		//super();
	}
	public WeatherDataKey(String pincode, LocalDate forDate) {
		//super();
		this.pincode = pincode;
		this.forDate = forDate;
	}
	public WeatherDataKey(String pincode2, String string) {
		// TODO Auto-generated constructor stub
	}
}