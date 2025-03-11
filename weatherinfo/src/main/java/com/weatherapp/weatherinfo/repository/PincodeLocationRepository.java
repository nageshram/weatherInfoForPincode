package com.weatherapp.weatherinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherapp.weatherinfo.entity.PincodeLocation;
@Repository
public interface PincodeLocationRepository extends JpaRepository<PincodeLocation, String> {}