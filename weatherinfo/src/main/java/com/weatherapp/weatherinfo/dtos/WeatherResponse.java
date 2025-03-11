package com.weatherapp.weatherinfo.dtos;

import lombok.Data;

@Data
public class WeatherResponse {
    private Main main;

    @Data
    public static class Main {
        private double temp;
        private int humidity;
        
		public double getTemp() {
			return temp;
		}
		public void setTemp(double temp) {
			this.temp = temp;
		}
		public int getHumidity() {
			return humidity;
		}
		public void setHumidity(int humidity) {
			this.humidity = humidity;
		}
    }

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
}