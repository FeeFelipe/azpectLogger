package io.azpect.logger.aspect;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspectTest {

	@Test
	public void error() {
		boolean b = true;
		assertTrue(b);
	}
}