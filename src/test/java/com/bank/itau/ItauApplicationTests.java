package com.bank.itau;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ItauApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
class ItauApplicationTests {

	@Test
	void contextLoads() {
	}

}
