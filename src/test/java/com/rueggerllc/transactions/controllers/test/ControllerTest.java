package com.rueggerllc.transactions.controllers.test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rueggerllc.transactions.controllers.TransactionController;
import com.rueggerllc.transactions.services.BackendServiceImpl;

@EnableConfigurationProperties
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
@AutoConfigureWireMock(port=8090)
@ActiveProfiles("it")
public class ControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private BackendServiceImpl service;
	
	@Autowired
	public TransactionController controller;
	
    @BeforeClass(dependsOnMethods={"springTestContextPrepareTestInstance"})
    public void setupParamValidation(){
        // Test class setup code with autowired classes goes here
    }
	
	@Test
	public void testController() throws Exception {
		
		System.out.println("testController BEGIN");
		stubFor(get(urlEqualTo("/transactions"))
			   .willReturn(aResponse().withStatus(200)
			   .withHeader("Content-Type","application/json")
			   .withBodyFile("dummyTransactions.json")));
			   
		controller.getTransactions();
		System.out.println("Test Passed!");
		
	}

}
