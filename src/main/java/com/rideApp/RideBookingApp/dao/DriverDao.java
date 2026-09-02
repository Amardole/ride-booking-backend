package com.rideApp.RideBookingApp.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rideApp.RideBookingApp.dto.DriverNameEmailDto;
import com.rideApp.RideBookingApp.entity.Driver;
import com.rideApp.RideBookingApp.enums.DriverStatus;
import com.rideApp.RideBookingApp.exception.DriverNotFoundException;
import com.rideApp.RideBookingApp.repo.DriverRepo;
import com.rideApp.RideBookingApp.repo.VahicleRepo;

import jakarta.transaction.Transactional;


@Repository
public class DriverDao {

	@Autowired
	DriverRepo driverRepo;
	
	@Autowired
	VahicleRepo vahicleRepo;
	
   public Driver saveDriver(Driver driver) {
	  return  driverRepo.save(driver);
   }
   
   public List<Driver> findAllDriver(){
	   return driverRepo.findAll();
   }
   
   public Driver findDriverById(int id) {
	   return driverRepo.findById(id).orElseThrow(()-> new DriverNotFoundException("Driver Not Present With Id "+id));  
   }
   
   public List<Driver> findDriverByStatus(DriverStatus status){
	   return driverRepo.getDriverByStatus(status).orElseThrow(()-> new DriverNotFoundException("Driver Not Found With Status "+status));
   }
   
   public Driver findDriverByEmail(String email) {
	   return driverRepo.getDriverByEmail(email).orElseThrow(()-> new DriverNotFoundException("Driver Not Present With Email "+email));
   }
   
   public Driver updateDriverById(int id, Driver driver) {
	  Driver d = driverRepo.findById(id).orElseThrow(()-> new DriverNotFoundException("Driver Not Found with Id "+id));
	  return driverRepo.save(driver);
   }
   
   public Driver deleteById(int id) {
	   Driver d= driverRepo.findById(id).orElseThrow(()-> new DriverNotFoundException("Driver Not Found with Id "+id));
	   driverRepo.deleteById(id);
	   return d;
   }
   
   
   @Transactional
   public int deleteByEmail(String email) {
	   int result = driverRepo.deleteByEmail(email);
	   if(result == 0) {
		   throw new DriverNotFoundException("Driver Not Found");
	   }
	   else return result;
   }
   

   public List<DriverNameEmailDto> getDriverNameAndEmail() {
	    return driverRepo.getDriverNameAndEmail();
	}
   
   
   @Transactional
   public int deleteDriverByStatus(DriverStatus status) {
       vahicleRepo.removeDriverFromVahicles(status.name());
       int result = driverRepo.deleteDriverByStatus(status);
       if (result == 0) {
           throw new DriverNotFoundException("No Driver Found With Status " + status);}
       return result;
   }
   
}