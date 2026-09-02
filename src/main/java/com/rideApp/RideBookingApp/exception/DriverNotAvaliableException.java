package com.rideApp.RideBookingApp.exception;

public class DriverNotAvaliableException extends RuntimeException {
	String message;
	public DriverNotAvaliableException(String message) {
		super(message);
	}
}
