package com.weatherapp.weatherinfo.dtos;
import lombok.Data;


@Data
public class WeatherResponse {
    private Main main;
    
    /* This class is created to catch the response from api when we request weather(openweather API)
     *  so we created inner class here( response will come in this format)
     * that works like 
     * {
     *    "WeatherResponse:{
     *    		"main":{
     *    		"humidity":45.0,
     *    		"temperature:60
     *           }
     *    
     *    }
     * but those words main and weatherResponse wont appear in our real response but i mentioned here for demo 
     */
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