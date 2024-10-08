package com.example.route53;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsRoute53Application {

	public static void main(String[] args) {
		SpringApplication.run(AwsRoute53Application.class, args);
		System.setProperty("com.amazonaws.sdk.disabledEc2Metadata", "true");
	}

}
