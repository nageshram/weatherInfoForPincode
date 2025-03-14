package com.weatherapp.weatherinfo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
 * Entity class for storing pincode location
 * This class is helpful for repository purpose
 * These are the main classes for operation of entire project
 */

@Entity
@Data
public class PincodeLocation {
    @Id
    private String pincode;
    private Double lat;
    private Double lon;
    
	public PincodeLocation() {
		super();
	}
	public PincodeLocation(String pincode, Double lat, Double lon) {
		super();
		this.pincode = pincode;
		this.lat = lat;
		this.lon = lon;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
    
}