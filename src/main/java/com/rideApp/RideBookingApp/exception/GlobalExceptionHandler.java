package com.rideApp.RideBookingApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rideApp.RideBookingApp.dto.ResponceStructure;
import com.rideApp.RideBookingApp.entity.Ride;
import com.rideApp.RideBookingApp.entity.Transaction;
import com.rideApp.RideBookingApp.entity.User;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception = UserNotFoundException.class)
	public ResponseEntity<ResponceStructure<User>> UserNotFoundException(UserNotFoundException usernotfoundexception){
		ResponceStructure<User> responce = new ResponceStructure<>();
		responce.setData(null);
		responce.setStatusCode(HttpStatus.NOT_FOUND.value());
		responce.setMessage(usernotfoundexception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responce);
	}
	
	@ExceptionHandler(exception = VahicleNotFoundException.class)
	public ResponseEntity<ResponceStructure<User>> VahicleNotFoundException(VahicleNotFoundException vahiclenotfoundexception){
		ResponceStructure<User> responce = new ResponceStructure<>();
		responce.setData(null);
		responce.setStatusCode(HttpStatus.NOT_FOUND.value());
		responce.setMessage(vahiclenotfoundexception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responce);
	}
	
	@ExceptionHandler(exception = DriverNotFoundException.class)
	public ResponseEntity<ResponceStructure<User>> DriverNotFoundException(DriverNotFoundException drivernotfoundexception){
		ResponceStructure<User> responce = new ResponceStructure<>();
		responce.setData(null);
		responce.setStatusCode(HttpStatus.NOT_FOUND.value());
		responce.setMessage(drivernotfoundexception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responce);
	}
	
	@ExceptionHandler(exception = DriverNotAvaliableException.class)
	public ResponseEntity<ResponceStructure<Ride>> driverNotAvaliableException(DriverNotAvaliableException driverNotAvaliableException){
		ResponceStructure<Ride> responce = new ResponceStructure<>();
		responce.setData(null);
		responce.setStatusCode(HttpStatus.NOT_FOUND.value());
		responce.setMessage(driverNotAvaliableException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responce);
	}
	
	@ExceptionHandler(exception = RideNotFoundException.class)
	public ResponseEntity<ResponceStructure<Ride>> RideNotFound(RideNotFoundException rideNotFoundException){
		ResponceStructure<Ride> responce = new ResponceStructure<>();
		responce.setData(null);
		responce.setMessage(rideNotFoundException.getMessage());
		responce.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responce);
	}
	
	@ExceptionHandler(exception = TransactionNotFoundException.class)
	public ResponseEntity<ResponceStructure<Transaction>> TransactionNotFound(TransactionNotFoundException transactionNotFoundException){
		ResponceStructure<Transaction> responce = new ResponceStructure<>();
		responce.setData(null);
		responce.setMessage(transactionNotFoundException.getMessage());
		responce.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responce);
	}

}
