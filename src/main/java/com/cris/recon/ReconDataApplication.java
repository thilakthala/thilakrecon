package com.cris.recon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ReconDataApplication {

	public static void main(String[] args) {
		
		System.out.println("In main");
		SpringApplication.run(ReconDataApplication.class, args);;
	}

}
