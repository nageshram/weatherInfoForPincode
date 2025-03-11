package com.weatherapp.weatherinfo.dtos;

import lombok.Data;

@Data
public class GeocodingResponse {
    private String zip;
    private double lat;
    private double lon;
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
}