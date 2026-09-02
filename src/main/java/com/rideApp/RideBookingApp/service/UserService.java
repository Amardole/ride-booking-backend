package com.rideApp.RideBookingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rideApp.RideBookingApp.dao.UserDao;
import com.rideApp.RideBookingApp.entity.User;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User addUser(User user) {
		return userDao.addUser(user);
	}
	
	public List<User> addBatch(List<User> users){
		return userDao.addBatch(users);
	}
	
	public List<User> findAllUsers(){
		return userDao.findAllUsers();
	}
	
	public User getUserById(int id) {
		return userDao.findUserById(id);
	}
	
	public User getUserByEmail(String email){
		return userDao.findByEmail(email);
	}
	
	public User deleteUserById(int id) {
		return userDao.deleteById(id);
	}
	
	public int updateUser(int id, String name, String email) {
	    return userDao.updateUser(name, email, id);
	}
	
	public int deleteByEmail(String email) {
	    return userDao.deleteByEmail(email);
	}
}
