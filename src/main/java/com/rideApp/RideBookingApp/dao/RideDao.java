package com.rideApp.RideBookingApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rideApp.RideBookingApp.entity.Driver;
import com.rideApp.RideBookingApp.entity.Ride;
import com.rideApp.RideBookingApp.entity.Transaction;
import com.rideApp.RideBookingApp.entity.User;
import com.rideApp.RideBookingApp.entity.Vahicle;
import com.rideApp.RideBookingApp.enums.DriverStatus;
import com.rideApp.RideBookingApp.enums.RideStatus;
import com.rideApp.RideBookingApp.enums.TransactionType;
import com.rideApp.RideBookingApp.enums.VahicleType;
import com.rideApp.RideBookingApp.exception.DriverNotAvaliableException;
import com.rideApp.RideBookingApp.exception.DriverNotFoundException;
import com.rideApp.RideBookingApp.exception.RideNotFoundException;
import com.rideApp.RideBookingApp.exception.UserNotFoundException;
import com.rideApp.RideBookingApp.exception.VahicleNotFoundException;
import com.rideApp.RideBookingApp.location.DistanceService;
import com.rideApp.RideBookingApp.repo.DriverRepo;
import com.rideApp.RideBookingApp.repo.RideRepo;
import com.rideApp.RideBookingApp.repo.TransactionRepo;
import com.rideApp.RideBookingApp.repo.UserRepo;

@Repository
public class RideDao {
	
	@Autowired
	private TransactionRepo tr;
	
	@Autowired
	private DriverRepo dr;
	
	@Autowired
	private DistanceService distanceService;
	
	@Autowired
	FareCalculator fc;
	
	@Autowired
	VahicleDao vd;
	
	@Autowired
	private UserRepo ur;
	
	@Autowired
	RideRepo riderepo;

	public Ride saveRide(int userid, String source, String destination, VahicleType vahicleType) {
		User u = ur.findById(userid).orElseThrow(()-> new UserNotFoundException("User not Found"));
		List<Vahicle> vahicles = vd.getVahiclesByType(vahicleType);	
		
		if(vahicles.isEmpty()) {
			throw new VahicleNotFoundException("No Vahicle Present");
		}		

		Driver driver=null;
		boolean driverFound = false;
		boolean busyDriverFound = false;
		
		for(Vahicle v: vahicles) {
			if(v.getDriver().getDriverStatus() == DriverStatus.AVAILABLE) {
				driver = v.getDriver();
				driverFound =true;
				break;
			}
			if(v.getDriver().getDriverStatus() == DriverStatus.BUSY) {
				busyDriverFound = true;
			}
			
		}
		
		//if driver busy + driverFound false - > driver not ava BUSY 
		if( !driverFound && busyDriverFound) throw new DriverNotAvaliableException("Ride Cancel Driver Is Busy");
		
		if(driver == null) throw new DriverNotFoundException("Driver Not Found");
		else {								
			System.out.println(driver.getDriverStatus());
			Ride ride = new Ride();
			if(driver.getDriverStatus().equals(DriverStatus.AVAILABLE)) {
				ride.setUser(u);
				ride.setSource(source);
				ride.setDestination(destination);
				ride.setDriver(driver);
				ride.setRidestatus(RideStatus.ONGOING);
				driver.setDriverStatus(DriverStatus.BUSY);				
				double distance = distanceService.calculateDistance(source, destination);
				distance = Math.round(distance * 100.0) / 100.0;
				ride.setFare(fc.calculateFare(vahicleType, distance));
				
				System.out.println(distance);
			}
			else {
				throw new DriverNotAvaliableException("Driver Not Available");
			}
			return riderepo.save(ride);
		}
	}
	
	// Complete Transaction 
	@Transactional
	public Ride completeRide(int rideid, TransactionType transactionType) {
		Ride r = riderepo.findById(rideid).orElseThrow(()-> new RideNotFoundException("Ride Not Found"));
		if (r.getRidestatus() != RideStatus.ONGOING) {
		      throw new RideNotFoundException("No Ride Is Ongoing With id "+r.getId());
		}
		Transaction t = new Transaction();	
		t.setTransactionType(transactionType);
		t.setUser(r.getUser());
		
		Driver d = dr.findById(r.getDriver().getId()).orElseThrow(()-> new DriverNotFoundException("Driver Not Found"));
		d.setDriverStatus(DriverStatus.AVAILABLE);
		r.setRidestatus(RideStatus.COMPLETED);
		
		riderepo.save(r);
		dr.save(d);
		tr.save(t);
		return r;
	}
	
	
	//get all Rides
	public List<Ride> getAllRides(){
		return riderepo.findAll();
	}
	
	public List<Ride> getByStatus(RideStatus rideStatus){
		List<Ride> r = riderepo.findByRidestatus(rideStatus);
		if(r.isEmpty()) throw new RideNotFoundException("No Ride With Status : "+rideStatus);
		return r;
	}
	
	public List<Ride> getByDriverId(int driverid){
		List<Ride> r = riderepo.findByDriver_id(driverid);
		if(r.isEmpty()) throw new RideNotFoundException("No Ride With Driver ID : "+driverid);
		return r;
	}
	

}
