package com.exchange.main;



import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CurrencyCalculationApplicationTest {

	@Test
	void contextLoads(ApplicationContext context) {
		// Verify that the application context loads successfully
		assertThat(context).isNotNull();
	}

	@Test
	void mainMethodStartsApplication() {
		// Test the main method to ensure it starts the application without errors
		CurrencyCalculationApplication.main(new String[]{});
	}
}