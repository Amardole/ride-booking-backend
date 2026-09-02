package com.rideApp.RideBookingApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rideApp.RideBookingApp.entity.Transaction;
import com.rideApp.RideBookingApp.enums.TransactionType;
import com.rideApp.RideBookingApp.exception.TransactionNotFoundException;
import com.rideApp.RideBookingApp.repo.TransactionRepo;

@Repository
public class TransactionDao {

	@Autowired
	TransactionRepo tr;
	
	public Transaction getTransactionById(int id) {
		return tr.findById(id).orElseThrow(()-> new TransactionNotFoundException("Id Not Present"));
	}
	
	public List<Transaction> getAllTransactions(){
		return tr.findAll();
	}
	
	public List<Transaction> getTransactionByType(TransactionType transactionType){
		
		List<Transaction> t = tr.findByTransactionType(transactionType);
		if (t.isEmpty()) {
		    throw new TransactionNotFoundException("No Transaction Is Found With Type " + transactionType );
		}
		return t;
	}
	
	public List<Transaction> getTransactionByUserId(int userid){
		
		List<Transaction> t = tr.findByUser_id(userid);
		if (t.isEmpty()) {
		    throw new TransactionNotFoundException("No Transaction Is Found With User " + userid);
		}
		return t;
	}
	
}
