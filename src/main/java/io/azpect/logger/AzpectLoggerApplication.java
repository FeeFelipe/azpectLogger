package io.azpect.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AzpectLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzpectLoggerApplication.class, args);
	}

}
