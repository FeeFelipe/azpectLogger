package io.azpect.logger.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Api {

	@Test
	public String error() {
		
		return "Error ";
	}
}