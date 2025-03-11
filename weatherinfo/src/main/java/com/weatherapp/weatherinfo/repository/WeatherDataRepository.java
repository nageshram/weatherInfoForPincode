package com.weatherapp.weatherinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherapp.weatherinfo.entity.WeatherData;
import com.weatherapp.weatherinfo.entity.WeatherDataKey;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, WeatherDataKey> {}