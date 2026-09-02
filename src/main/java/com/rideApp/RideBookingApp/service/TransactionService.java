package com.rideApp.RideBookingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rideApp.RideBookingApp.dao.TransactionDao;
import com.rideApp.RideBookingApp.entity.Transaction;
import com.rideApp.RideBookingApp.enums.TransactionType;

@Service
public class TransactionService {

	@Autowired
	TransactionDao td;
	
	public Transaction getTransactionById(int id) {
		return td.getTransactionById(id);
	}
	
	public List<Transaction> getAllTransactions(){
		return td.getAllTransactions();
	}
	
	public List<Transaction> getTransactionByType(TransactionType transactionType){
		return td.getTransactionByType(transactionType);
	}
	
	public List<Transaction> getTransactionByUserid(int userid){
		return td.getTransactionByUserId(userid);
	}
}
