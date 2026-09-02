package com.rideApp.RideBookingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rideApp.RideBookingApp.dao.RideDao;
import com.rideApp.RideBookingApp.entity.Ride;
import com.rideApp.RideBookingApp.enums.RideStatus;
import com.rideApp.RideBookingApp.enums.TransactionType;
import com.rideApp.RideBookingApp.enums.VahicleType;
import com.rideApp.RideBookingApp.location.DistanceService;

@Service
public class RideService {
	
	@Autowired
	RideDao ridedao;
	
	
	public Ride saveRide(int userid,String source, String destination, VahicleType vahicleType) {
		return ridedao.saveRide(userid,source,destination,vahicleType);
	}
	
	public Ride CompleteRide(int rideid, TransactionType transactionType) {
		return ridedao.completeRide(rideid, transactionType);
	}
	
	public List<Ride> getAllRides(){
		return ridedao.getAllRides();
	}
	
	public List<Ride> getRideByStatus(RideStatus rideStatus){
		return ridedao.getByStatus(rideStatus);
	}
	
	public List<Ride> getRideByDriverId(int driverid){
		return ridedao.getByDriverId(driverid);
	}
	
}
