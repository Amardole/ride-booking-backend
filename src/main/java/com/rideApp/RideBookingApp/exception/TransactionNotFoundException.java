package com.rideApp.RideBookingApp.exception;


public class TransactionNotFoundException extends RuntimeException {

	public TransactionNotFoundException(String message) {
		super(message);
	}
}
