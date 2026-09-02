package com.rideApp.RideBookingApp.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rideApp.RideBookingApp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	

	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> getuserByEmail(@Param("email") String email);
	
	
	@Modifying
	@Query("UPDATE User u SET u.name = :name, u.email = :email WHERE u.id = :id")
	int updateUser(@Param("id") int id,
	               @Param("name") String name,
	               @Param("email") String email);
	
	@Modifying
	@Query("DELETE FROM User u WHERE u.email = :email")
	int deleteByEmail(@Param("email") String email);

}
