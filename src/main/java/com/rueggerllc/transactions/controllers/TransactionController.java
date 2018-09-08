package com.rueggerllc.transactions.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rueggerllc.transactions.beans.BackendTransaction;
import com.rueggerllc.transactions.beans.Transaction;
import com.rueggerllc.transactions.services.BackendServiceImpl;

@RestController
@RequestMapping("/")
public class TransactionController {
	
	private BackendServiceImpl backendService;
	
	@Autowired
	public TransactionController(BackendServiceImpl backendService) {
		this.backendService = backendService;
	}
	
	@GetMapping("/transactions")
	public Iterable<Transaction> getTransactions() {
		
		
		System.out.println("We are Inside getTransactions()");
		
		List<BackendTransaction> backendTransactions = backendService.getTransactions();
		for (BackendTransaction backendTransaction : backendTransactions) {
			System.out.println("Next Backend Transaction:" + backendTransaction.getTransactionID());
		}
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int i = 0; i < 3; i++) {
			Transaction transaction = new Transaction();
			transaction.setTransactionID("TransactionID_" + i);
			transaction.setAmount(new BigDecimal(42.35).add(new BigDecimal(i)));
			transaction.setDescription("TransactionDesc" + i);
			transaction.setTimestamp(getNow());
			transactions.add(transaction);
		}
		
		// Done
		return transactions;
		
	}
	
	private long getNow() {
		return System.currentTimeMillis();
	}
  

}
