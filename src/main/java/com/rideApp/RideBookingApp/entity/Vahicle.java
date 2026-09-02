package com.rideApp.RideBookingApp.entity;

import com.rideApp.RideBookingApp.enums.VahicleType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;

@Entity
public class Vahicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}", message = "Enter Valid Number")
	private String vahicle_no;
	
	@Enumerated(EnumType.STRING)
	private VahicleType vahicletype;
	
	@OneToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVahicle_no() {
		return vahicle_no;
	}

	public void setVahicle_no(String vahicle_no) {
		this.vahicle_no = vahicle_no;
	}

	public VahicleType getVahicletype() {
		return vahicletype;
	}

	public void setVahicletype(VahicleType vahicletype) {
		this.vahicletype = vahicletype;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	

}
