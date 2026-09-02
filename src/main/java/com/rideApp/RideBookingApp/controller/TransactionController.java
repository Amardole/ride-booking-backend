package com.rideApp.RideBookingApp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rideApp.RideBookingApp.dto.ResponceStructure;
import com.rideApp.RideBookingApp.entity.Transaction;
import com.rideApp.RideBookingApp.enums.TransactionType;
import com.rideApp.RideBookingApp.service.TransactionService;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
	
	@Autowired
	TransactionService ts;
	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<ResponceStructure<Transaction>> getById(@PathVariable int id){ 
		ResponceStructure<Transaction> responce = new ResponceStructure<>();
		responce.setData(ts.getTransactionById(id));
		responce.setMessage("Transaction Found");
		responce.setStatusCode(HttpStatus.FOUND.value());
		return ResponseEntity.status(HttpStatus.FOUND).body(responce);
	}
	
	@GetMapping("/AllTransactions")
	public ResponseEntity<ResponceStructure<List<Transaction>>> getAllTransaction(){
		ResponceStructure<List<Transaction>> responce =new ResponceStructure<>();
		responce.setData(ts.getAllTransactions());
		responce.setMessage("Total Transactions...");
		responce.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	
	@GetMapping("/getByType")
	public ResponseEntity<ResponceStructure<List<Transaction>>> getTransactionByType(@RequestParam TransactionType transactionType){
		ResponceStructure<List<Transaction>> responce =new ResponceStructure<>();
		responce.setData(ts.getTransactionByType(transactionType));
		responce.setMessage("Total Transactions With "+transactionType);
		responce.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	
	@GetMapping("/getByUserId/{userid}")
	public ResponseEntity<ResponceStructure<List<Transaction>>> getTransactionByUserId(@PathVariable int userid){
		ResponceStructure<List<Transaction>> responce =new ResponceStructure<>();
		responce.setData(ts.getTransactionByUserid(userid));
		responce.setMessage("Total Transactions With userid "+userid);
		responce.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(responce);
	}
	

}
