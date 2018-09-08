package com.rueggerllc.transactions.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("chris.app")
public class MyConfigProperties {
	
	private String backendServiceURL;

	public String getBackendServiceURL() {
		return backendServiceURL;
	}

	public void setBackendServiceURL(String backendServiceURL) {
		this.backendServiceURL = backendServiceURL;
	}
	
	

}
