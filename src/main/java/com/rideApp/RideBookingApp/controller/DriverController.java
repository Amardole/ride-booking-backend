package com.rideApp.RideBookingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rideApp.RideBookingApp.dto.DriverNameEmailDto;
import com.rideApp.RideBookingApp.dto.ResponceStructure;
import com.rideApp.RideBookingApp.entity.Driver;
import com.rideApp.RideBookingApp.enums.DriverStatus;
import com.rideApp.RideBookingApp.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;
    
    
    @PostMapping("/add")
    public ResponseEntity<ResponceStructure<Driver>> saveDriver(@RequestBody Driver driver){
    	ResponceStructure<Driver> responce = new ResponceStructure<>();
    	Driver addedDriver = driverService.saveDriver(driver);
    	responce.setData(addedDriver);
    	responce.setMessage("Driver Added Successfully...");
    	responce.setStatusCode(HttpStatus.CREATED.value());
    	return ResponseEntity.status(HttpStatus.CREATED).body(responce);
    }
    
    @GetMapping("/getAllDriver")
    public ResponseEntity<ResponceStructure<List<Driver>>> getAllDriver(){
    	ResponceStructure<List<Driver>> responce = new ResponceStructure<>();
    	List<Driver> drivers = driverService.getAllDriver();
    	responce.setData(drivers);
    	responce.setMessage("All Driver...");
    	responce.setStatusCode(HttpStatus.OK.value());
    	return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
    @GetMapping("/getDriver/{id}")
    public ResponseEntity<ResponceStructure<Driver>> getDriverById(@PathVariable int id){
    	ResponceStructure<Driver> responce = new ResponceStructure<>();
    	responce.setData(driverService.getDriverById(id));
    	responce.setMessage("Driver Found");
    	responce.setStatusCode(HttpStatus.FOUND.value());
    	return ResponseEntity.status(HttpStatus.FOUND).body(responce);
    }
    
    @GetMapping("/getDriverByStatus")
    public ResponseEntity<ResponceStructure<List<Driver>>> getDriverByStatus(@RequestParam DriverStatus status){
    	ResponceStructure<List<Driver>> responce = new ResponceStructure<>();
    	responce.setMessage("Driver Found With Status "+status);
    	responce.setData(driverService.getDriverByStatus(status));
    	responce.setStatusCode(HttpStatus.OK.value());
    	return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
    @GetMapping("/getDriverByEmail/{email}")
    public ResponseEntity<ResponceStructure<Driver>> getDriverByEmail(@PathVariable String email){
    	ResponceStructure<Driver> responce = new ResponceStructure<>();
    	responce.setMessage("Driver Found With Email "+email);
    	responce.setStatusCode(HttpStatus.FOUND.value());
    	responce.setData(driverService.getDriverByEmail(email));
    	return ResponseEntity.status(HttpStatus.FOUND).body(responce);
    }
    
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponceStructure<Driver>> deleteDriverById(@PathVariable int id){
    	ResponceStructure<Driver> responce = new ResponceStructure<>();
    	responce.setData(driverService.deleteDriverById(id));
    	responce.setMessage("Driver Deleted With ID "+id);
    	responce.setStatusCode(HttpStatus.OK.value());
    	return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<ResponceStructure<Integer>> deleteByEmail(@PathVariable String email){
    	ResponceStructure<Integer> responce = new ResponceStructure<>();
    	responce.setData(driverService.deleteByEmail(email));
    	responce.setStatusCode(HttpStatus.OK.value());
    	responce.setMessage("Driver Deleted");
    	return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
    @PutMapping("/UpdateById/{id}")
    public ResponseEntity<ResponceStructure<Driver>> updateDriverById(@PathVariable int id, @RequestBody Driver driver){
    	ResponceStructure<Driver> responce = new ResponceStructure<>();
    	Driver updated = driverService.updateById(id, driver);
    	responce.setData(updated);
    	responce.setMessage("Updated Info");
    	responce.setStatusCode(HttpStatus.OK.value());
    	return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
    @GetMapping("/getNameAndEmail")
    public ResponseEntity<ResponceStructure<List<DriverNameEmailDto>>> getDriverNameAndEmail() {
        ResponceStructure<List<DriverNameEmailDto>> responce = new ResponceStructure<>();
        responce.setData(driverService.getDriverNameAndEmail());
        responce.setMessage("Driver Name and Email Fetched Successfully");
        responce.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
    @DeleteMapping("/deleteByStatus")
    public ResponseEntity<ResponceStructure<Integer>> deleteDriverByStatus(@RequestParam DriverStatus status) {
        int result = driverService.deleteDriverByStatus(status);
        ResponceStructure<Integer> responce = new ResponceStructure<>();
        responce.setData(result);
        responce.setMessage("Drivers Deleted Successfully");
        responce.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(responce);
    }
    
}