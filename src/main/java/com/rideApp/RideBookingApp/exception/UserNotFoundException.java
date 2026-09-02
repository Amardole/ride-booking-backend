package com.rideApp.RideBookingApp.exception;

public class UserNotFoundException extends RuntimeException{
	String message;
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
