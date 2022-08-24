package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserApplication {
	public static void main(String [] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
@Bean
@LoadBalanced
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
	
}
