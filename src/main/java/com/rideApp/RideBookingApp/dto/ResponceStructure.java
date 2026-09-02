package com.rideApp.RideBookingApp.dto;

public class ResponceStructure<T> {

	private String Message;
	private int statusCode;
	private T data;
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	 
	 
	
}
