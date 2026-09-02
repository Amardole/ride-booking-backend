package com.rideApp.RideBookingApp.exception;

public class VahicleNotFoundException extends RuntimeException {

    public VahicleNotFoundException(String message) {
        super(message);
    }
}