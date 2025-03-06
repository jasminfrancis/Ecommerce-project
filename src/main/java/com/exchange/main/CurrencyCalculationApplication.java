package com.exchange.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.exchange"})

public class CurrencyCalculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyCalculationApplication.class, args);
	}
}
