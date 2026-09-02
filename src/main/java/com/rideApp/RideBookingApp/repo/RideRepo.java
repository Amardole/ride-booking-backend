package com.rideApp.RideBookingApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideApp.RideBookingApp.entity.Ride;
import com.rideApp.RideBookingApp.enums.RideStatus;

public interface RideRepo extends JpaRepository<Ride, Integer> {

	List<Ride> findByRidestatus(RideStatus rideStatus);
	
	List<Ride> findByDriver_id(int driver_id);
}
