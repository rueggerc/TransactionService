package com.rueggerllc.transactions.beans;

import java.util.List;

public class BackendTransactionResponse {
	
	private List<BackendTransaction> transactions;

	public List<BackendTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<BackendTransaction> transactions) {
		this.transactions = transactions;
	}
	
	

}
