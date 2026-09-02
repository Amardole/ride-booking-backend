package com.rideApp.RideBookingApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideApp.RideBookingApp.entity.Transaction;
import com.rideApp.RideBookingApp.enums.TransactionType;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByTransactionType(TransactionType transactionType);
	
	List<Transaction> findByUser_id(int userid);
}
