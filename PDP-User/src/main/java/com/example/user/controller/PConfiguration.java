package com.example.user.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("server2")
public class PConfiguration {

    private String port2;

	public String getPort2() {
		return port2;
	}

	public void setPort2(String port2) {
		this.port2 = port2;
	}

	

  
}