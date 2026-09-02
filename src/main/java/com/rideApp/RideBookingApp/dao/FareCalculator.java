package com.rideApp.RideBookingApp.dao;

import org.springframework.stereotype.Component;

import com.rideApp.RideBookingApp.enums.VahicleType;

@Component
public class FareCalculator {
	
	public double calculateFare(VahicleType vehicleType, double distance) {

	    double baseFare;
	    double perKm;

	    switch (vehicleType) {

	        case VahicleType.BIKE:
	            baseFare = 15;
	            perKm = 6;
	            break;

	        case VahicleType.AUTO:
	            baseFare = 25;
	            perKm = 10;
	            break;

	        case VahicleType.MINI:
	            baseFare = 30;
	            perKm = 13;
	            break;

	        case VahicleType.SEDAN:
	            baseFare = 40;
	            perKm = 18;
	            break;

	        case VahicleType.XUV:
	            baseFare = 60;
	            perKm = 22;
	            break;

	        default:
	            throw new IllegalArgumentException("Invalid vehicle type");
	    }

	    return Math.round(baseFare + (distance * perKm));
	}

}
