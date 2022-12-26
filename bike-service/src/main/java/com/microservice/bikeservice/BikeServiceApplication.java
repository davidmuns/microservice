package com.microservice.bikeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BikeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BikeServiceApplication.class, args);
	}

}
