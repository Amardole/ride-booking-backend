package com.rideApp.RideBookingApp.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rideApp.RideBookingApp.entity.User;
import com.rideApp.RideBookingApp.exception.UserNotFoundException;
import com.rideApp.RideBookingApp.repo.UserRepo;

import jakarta.transaction.Transactional;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepo userRepo;
	
	public User addUser(User user) {
		return userRepo.save(user);
	}
	
	public List<User> addBatch(List<User> users){
		return userRepo.saveAll(users);
	}
	
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	public User findUserById(int id) {
		return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found With Id "+id));
		  
	}
	
	public User findByEmail(String email) {
		return userRepo.getuserByEmail(email).orElseThrow(()-> new UserNotFoundException("User Not Found With Email "+email));
	}
	
	public User deleteById(int id) {
		User user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found With Id "+id));
		userRepo.deleteById(id);
		return user;
	}
	
	@Transactional
	public int updateUser(String name, String email, int id) {
	    User user = userRepo.findById(id).orElseThrow(() ->
	                new UserNotFoundException("User Not Found With Id " + id));
	    return userRepo.updateUser(id, name, email);
	}
	
	@Transactional
	public int deleteByEmail(String email) {
	    int result = userRepo.deleteByEmail(email);
	    if (result == 0) {
	        throw new UserNotFoundException("User Not Found With Email " + email);
	    }
	    return result;
	}

}
