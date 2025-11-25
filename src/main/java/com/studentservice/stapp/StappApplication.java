package com.studentservice.stapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StappApplication {

	public static void main(String[] args) {
		SpringApplication.run(StappApplication.class, args);
		System.out.println("Tomcat running ");
	}

}
