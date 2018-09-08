package com.rueggerllc.transactions.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rueggerllc.transactions.beans.BackendTransaction;
import com.rueggerllc.transactions.beans.BackendTransactionResponse;
import com.rueggerllc.transactions.config.MyConfigProperties;

@Service
public class BackendServiceImpl {
	
	private MyConfigProperties app;
	
	@Autowired
	public BackendServiceImpl(MyConfigProperties app) {
		this.app = app;
	}
	
	public List<BackendTransaction> getTransactions() {
		
		String backendServiceURL = app.getBackendServiceURL();
		System.out.println("BackendServiceImpl Connecting to BackendService AT=" + backendServiceURL);
		
		RestTemplate restTemplate = new RestTemplate();
		
		// ResponseEntity<String> responseEntityJSON = restTemplate.exchange(backendServiceURL,HttpMethod.GET, getHeaders(), String.class);
		// System.out.println("GOT JSON=\n" + responseEntityJSON.getBody());
		
		// Response Object
		ResponseEntity<BackendTransactionResponse> responseEntity = restTemplate.exchange(backendServiceURL,HttpMethod.GET,getHeaders(),BackendTransactionResponse.class);
		BackendTransactionResponse response = responseEntity.getBody();
		List<BackendTransaction> backendTransactions = response.getTransactions();
		return backendTransactions;
		
		
	}
	
	private static HttpEntity<?> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	


}
