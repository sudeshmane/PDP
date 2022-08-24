package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableAuthorizationServer
@EnableResourceServer
public class LibraryApplication {
	public static void main(String [] args) {
		SpringApplication.run(LibraryApplication.class, args);
		
		/* 
		  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	
		  String encoded = encoder.encode("pqr");
		    System.out.println("pqr "+encoded);
		    encoded = encoder.encode("password2");
		    System.out.println(encoded);
		 */
	}
	
@Bean
@LoadBalanced
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
	
}
