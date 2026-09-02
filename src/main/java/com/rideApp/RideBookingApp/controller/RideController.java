package com.rideApp.RideBookingApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rideApp.RideBookingApp.dto.ResponceStructure;
import com.rideApp.RideBookingApp.entity.Ride;
import com.rideApp.RideBookingApp.enums.RideStatus;
import com.rideApp.RideBookingApp.enums.TransactionType;
import com.rideApp.RideBookingApp.enums.VahicleType;
import com.rideApp.RideBookingApp.service.RideService;

@RestController
@RequestMapping("/Ride")
public class RideController {
	
	@Autowired
	private RideService rideservice;
	
	
	@PostMapping("/addRide/userId/{userid}")
	public ResponseEntity<ResponceStructure<Ride>> saveRide(@PathVariable int userid, @RequestParam String source, String destination, VahicleType vahicleType){
		ResponceStructure<Ride> responce = new ResponceStructure<>();
		responce.setData(rideservice.saveRide(userid,source,destination,vahicleType));
		responce.setMessage("Ride Added Successfully..");
		responce.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(responce);
	}
	
	@PostMapping("/completeRide/rideid/{rideid}")
	public ResponseEntity<ResponceStructure<Ride>> completeRide(@PathVariable int rideid, @RequestParam TransactionType transactionType){
		ResponceStructure<Ride> responce = new ResponceStructure<>();
		responce.setData(rideservice.CompleteRide(rideid, transactionType));
		responce.setMessage("Ride Completed Successfully...");
		responce.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	
	@GetMapping("/getAllRides")
	public ResponseEntity<ResponceStructure<List<Ride>>> getAllRides(){
		ResponceStructure<List<Ride>> responce = new ResponceStructure<>();
		responce.setData(rideservice.getAllRides());
		responce.setMessage("Total Rides...");
		responce.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	
	@GetMapping("/getByStatus/{rideStatus}")
	public ResponseEntity<ResponceStructure<List<Ride>>> getRideByStatus(@PathVariable RideStatus rideStatus){
		ResponceStructure<List<Ride>> responce = new ResponceStructure<>();
		responce.setData(rideservice.getRideByStatus(rideStatus));
		responce.setMessage("Total Rides with Status "+rideStatus);
		responce.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	
	@GetMapping("/getByDriver/{driverid}")
	public ResponseEntity<ResponceStructure<List<Ride>>> getRideByDriverid(@PathVariable int driverid){
		ResponceStructure<List<Ride>> responce = new ResponceStructure<>();
		responce.setData(rideservice.getRideByDriverId(driverid));
		responce.setMessage("Total Rides with Driver "+driverid);
		responce.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}

}
