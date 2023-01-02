package com.jbk.RestDemo;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RestDemoApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void grettings () throws URISyntaxException {
		
		System.out.println("Test Stared");
		RestTemplate rst = new RestTemplate();
		String url = "http://localhost:8080/getMesBaseOnTIme/Kartik";
		URI uri = new URI(url);
		ResponseEntity<String> responseEntity = rst.getForEntity(uri, String.class);
		Assertions.assertEquals( 200, responseEntity.getStatusCodeValue() );
		System.out.println("Test End");
		
	}

}
