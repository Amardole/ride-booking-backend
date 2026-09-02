package com.rideApp.RideBookingApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rideApp.RideBookingApp.dto.DriverNameEmailDto;
import com.rideApp.RideBookingApp.entity.Driver;
import com.rideApp.RideBookingApp.entity.User;
import com.rideApp.RideBookingApp.enums.DriverStatus;

public interface DriverRepo extends JpaRepository<Driver, Integer> {
	
	@Query("SELECT d FROM Driver d WHERE d.driverStatus = :driverStatus")
	Optional<List<Driver>> getDriverByStatus(@Param("driverStatus") DriverStatus driverStatus);

	@Query("SELECT d FROM Driver d WHERE d.email = :email")
	Optional<Driver> getDriverByEmail(@Param("email") String email);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Driver d WHERE d.email = :email")
	int deleteByEmail(@Param("email") String email);
	
	
	@Query("SELECT new com.rideApp.RideBookingApp.dto.DriverNameEmailDto(d.name, d.email) FROM Driver d")
	List<DriverNameEmailDto> getDriverNameAndEmail();
	
	@Modifying
	@Query("DELETE FROM Driver d WHERE d.driverStatus = :status")
	int deleteDriverByStatus(@Param("status") DriverStatus status);

	

}
