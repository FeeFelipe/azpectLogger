package io.azpect.logger.aspect;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspectTest {

	@Test
	public String error() {
		
		return "Error ";
	}
}