package com.rideApp.RideBookingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rideApp.RideBookingApp.dao.DriverDao;
import com.rideApp.RideBookingApp.dto.DriverNameEmailDto;
import com.rideApp.RideBookingApp.entity.Driver;
import com.rideApp.RideBookingApp.enums.DriverStatus;

@Service
public class DriverService {
	
	@Autowired
	DriverDao driverDao;

	public Driver saveDriver(Driver driver) {
		return driverDao.saveDriver(driver);
	}
	
	public List<Driver> getAllDriver(){
		return driverDao.findAllDriver();
	}
	
	public Driver getDriverById(int id) {
		return driverDao.findDriverById(id);
	}
	
	public List<Driver> getDriverByStatus(DriverStatus status){
		return driverDao.findDriverByStatus(status);
	}
	
	public Driver getDriverByEmail(String email) {
		return driverDao.findDriverByEmail(email);
	}
	
	public Driver deleteDriverById(int id) {
		return driverDao.deleteById(id);
	}
	
	public int deleteByEmail(String email) {
		return driverDao.deleteByEmail(email);
	}
	
	public Driver updateById(int id, Driver driver) {
		return driverDao.updateDriverById(id, driver);
	}
	
	public List<DriverNameEmailDto> getDriverNameAndEmail() {
	    return driverDao.getDriverNameAndEmail();
	}
	
	public int deleteDriverByStatus(DriverStatus status) {
	    return driverDao.deleteDriverByStatus(status);
	}
	

}