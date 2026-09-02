package com.rideApp.RideBookingApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideApp.RideBookingApp.dto.ResponceStructure;
import com.rideApp.RideBookingApp.entity.User;
import com.rideApp.RideBookingApp.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponceStructure<User>> addUser(@RequestBody User user){
		User createdUser = userService.addUser(user);
		ResponceStructure<User> responce = new ResponceStructure<>();
		
		responce.setMessage("User Created Successfully...");
		responce.setStatusCode(HttpStatus.CREATED.value());
		responce.setData(createdUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responce);
	}
	
	@PostMapping("/addUserBatch")
	public ResponseEntity<ResponceStructure<List<User>>> addUserBatch(@RequestBody List<User> users){
		List<User> addedUser = userService.addBatch(users);
		ResponceStructure<List<User>> responce = new ResponceStructure<>();
		
		responce.setMessage("Users Added Successfully...");
		responce.setStatusCode(HttpStatus.OK.value());
		responce.setData(addedUser);
		
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	
	@GetMapping("/findAllUsers")
	public ResponseEntity<ResponceStructure<List<User>>> findAllUsers(){
		List<User> users = userService.findAllUsers();
		ResponceStructure<List<User>> responce = new ResponceStructure<>();
		
		responce.setMessage("All Users...");
		responce.setStatusCode(HttpStatus.OK.value());
		responce.setData(users);
		
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	
	@GetMapping("/findUserById/{id}")
	public ResponseEntity<ResponceStructure<User>> findById(@PathVariable int id){
		User user= userService.getUserById(id);
		ResponceStructure<User> responce = new ResponceStructure<>();
		
		responce.setData(user);
		responce.setMessage("User Found With Id "+id);
		responce.setStatusCode(HttpStatus.FOUND.value());
		
		return ResponseEntity.status(HttpStatus.FOUND).body(responce);
	}
	
	@GetMapping("/findUserByEmail/{email}")
	public ResponseEntity<ResponceStructure<User>> findById(@PathVariable String email){
		User user= userService.getUserByEmail(email);
		ResponceStructure<User> responce = new ResponceStructure<>();
		
		responce.setData(user);
		responce.setMessage("User Found With Id "+email);
		responce.setStatusCode(HttpStatus.FOUND.value());
		
		return ResponseEntity.status(HttpStatus.FOUND).body(responce);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<ResponceStructure<User>> deleteById(@PathVariable int id){
		User user = userService.deleteUserById(id);
		ResponceStructure<User> response = new ResponceStructure<>();
		response.setData(user);
		response.setMessage("Record Delete With Id :"+id);
		response.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	

	@PutMapping("/update/{id}/{name}/{email}")
	public ResponseEntity<ResponceStructure<Integer>> updateUser(
	        @PathVariable int id,
	        @PathVariable String name,
	        @PathVariable String email) {

	    int updatedUser = userService.updateUser(id, name, email);
	    ResponceStructure<Integer> response = new ResponceStructure<>();
	    response.setData(updatedUser);
	    response.setMessage("User Updated Successfully");
	    response.setStatusCode(HttpStatus.OK.value());

	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/deleteByEmail/{email}")
	public ResponseEntity<ResponceStructure<Integer>> deleteByEmail( @PathVariable String email) {
	    int deletedUser = userService.deleteByEmail(email);
	    ResponceStructure<Integer> response = new ResponceStructure<>();
	    response.setData(deletedUser);
	    response.setMessage("User Deleted Successfully");
	    response.setStatusCode(HttpStatus.OK.value());

	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
