package com.example.ShopLaptop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class ShopLaptopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopLaptopApplication.class, args);
	}

}
