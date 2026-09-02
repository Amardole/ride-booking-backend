package com.rideApp.RideBookingApp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rideApp.RideBookingApp.enums.DriverStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;

@Entity
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	
	@Email(message = "Enter Valid Email")
	private String email;
	private long contact_no;
	
	@Enumerated(EnumType.STRING)
	private DriverStatus driverStatus;
	
	@JsonIgnore
	@OneToOne(mappedBy = "driver", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Vahicle vahicle;
	
	@JsonIgnore
	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ride> ride;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact_no() {
		return contact_no;
	}

	public void setContact_no(long contact_no) {
		this.contact_no = contact_no;
	}

	public DriverStatus getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(DriverStatus driverStatus) {
		this.driverStatus = driverStatus;
	}

	public Vahicle getVahicle() {
		return vahicle;
	}

	public void setVahicle(Vahicle vahicle) {
		this.vahicle = vahicle;
	}

	public List<Ride> getRide() {
		return ride;
	}

	public void setRide(List<Ride> ride) {
		this.ride = ride;
	}
	
	

}
